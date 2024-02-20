package server;

import Client.Components.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class SignupListener implements ActionListener {
        private JTextField emailField;
        private JPasswordField passwordField;
        private JTextField PseudoField;
        private JFrame frame;

    public SignupListener(JTextField emailField, JPasswordField passwordField, JTextField PseudoField, JFrame frame) {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.PseudoField = PseudoField;
        this.frame = frame;
    }

    @Override
        public void actionPerformed (ActionEvent e){
            String pseudo, email, password, role = null;
            String BDD = "java";
            String url = "jdbc:mysql://localhost:3306/" + BDD;
            String psd = "root";
            String passwd = "";

            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                try (Connection conn = DriverManager.getConnection(url, psd, passwd)) {
                    conn.setAutoCommit(false);
                    System.out.println("Connecté !");

                    // Vérifier le rôle en fonction du nombre de lignes dans la table "user"
                    String verify_role = "SELECT COUNT(*) FROM user";
                    try (PreparedStatement statement = conn.prepareStatement(verify_role)) {
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                int nombreDeLignes = resultSet.getInt(1);
                                role = (nombreDeLignes == 0) ? "Admin" : "User";
                            }
                        }
                    }

                    // Vérifier que l'email est whitelist
                    String verify_email = "SELECT email FROM whitelisted_email WHERE email = ?";
                    try (PreparedStatement statement = conn.prepareStatement(verify_email)) {
                        statement.setString(1, emailField.getText());
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                if ("".equals(emailField.getText()) || "".equals(passwordField.getText()) || "".equals(PseudoField.getText())) {
                                    JOptionPane.showMessageDialog(new JFrame(), "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    email = emailField.getText();
                                    pseudo = PseudoField.getText();
                                    password = passwordField.getText();

                                    // Generate a random salt
                                    SecureRandom random = new SecureRandom();
                                    byte[] salt = new byte[16];
                                    random.nextBytes(salt);

                                    // Hash the password with the salt
                                    String hashedPassword = PasswordUtils.hashPassword(password, salt);

                                    // Insertion des données dans la table "user"
                                    String sql = "INSERT INTO user (email, pseudo, password, role, salt) VALUES (?, ?, ?, ?, ?)";
                                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                                        pstmt.setString(1, email);
                                        pstmt.setString(2, pseudo);
                                        pstmt.setString(3, hashedPassword);
                                        pstmt.setString(4, role);
                                        pstmt.setString(5, Base64.getEncoder().encodeToString(salt));
                                        pstmt.executeUpdate();
                                    }
                                    conn.commit();
                                    emailField.setText("");
                                    passwordField.setText("");
                                    PseudoField.setText("");
                                    JOptionPane.showMessageDialog(null, "Account Created Successfully !");
                                    frame.dispose();
                                    LoginScreen loginScreen = new LoginScreen();

                                }
                            } else {
                                // Check if no users are present in the whitelisted_email table
                                String countUsersQuery = "SELECT COUNT(*) FROM whitelisted_email";
                                try (PreparedStatement countStatement = conn.prepareStatement(countUsersQuery);
                                     ResultSet countResultSet = countStatement.executeQuery()) {
                                    if (countResultSet.next() && countResultSet.getInt(1) == 0) {
                                        String sql = "INSERT INTO whitelisted_email (email) VALUES (?)";
                                        try (PreparedStatement state = conn.prepareStatement(sql)) {
                                            state.setString(1, emailField.getText());
                                            state.executeUpdate();
                                        }
                                    } else {
                                        // The email is not whitelisted
                                        JOptionPane.showMessageDialog(new JFrame(), "Email is not whitelisted!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    } catch (SQLException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
