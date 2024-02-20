package server;

import Client.Components.DashboardFrame;
import Client.Components.PasswordUtils;
import Client.Components.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Objects;

public class UserButton implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField pseudoField;
    private JTextField roleField;
    private JPanel panel;
    private UsersPanel usersPanel;
    private OperationType operationType;

    // Enumeration for different operations
    public enum OperationType {
        CREATE, READ, DELETE, UPDATE
    }

    public UserButton(JTextField emailField, JPasswordField passwordField, JTextField pseudoField, JTextField roleField, JPanel panel, UsersPanel usersPanel,OperationType operationType) {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.pseudoField = pseudoField;
        this.roleField = roleField;
        this.panel = panel;
        this.usersPanel = usersPanel;
        this.operationType = operationType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = null, pseudo = null, password = null, role = null;
        String role_session = DashboardFrame.Get_role();
        String user_session = DashboardFrame.Get_username();
        Boolean NotFound = false;
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

                switch (operationType) {
                    case CREATE:
                        // We can create an account, but we need to verify if the email is whitelisted
                        String create_user;
                        boolean isEmailWhitelisted = false;
                        if (!("".equals(email) || "".equals(pseudo) || "".equals(role))) {
                            // TODO: Add the condition to verify if the email is inside the whitelist table
                            String email_whitelist = "SELECT email from whitelisted_email WHERE email = ?";
                            try (PreparedStatement pstmt = conn.prepareStatement(email_whitelist)){
                                try (ResultSet resultSet = pstmt.executeQuery()){
                                    if (resultSet.next()) {
                                        isEmailWhitelisted = true;
                                    }
                                }
                            }
                            if (isEmailWhitelisted) {
                                // Generate a random salt
                                SecureRandom random = new SecureRandom();
                                byte[] salt = new byte[16];
                                random.nextBytes(salt);

                                // Hash the password with the salt
                                String hashedPassword = PasswordUtils.hashPassword(new String(passwordField.getPassword()), salt);

                                // Insertion des données dans la table "user"
                                create_user = "INSERT INTO user (email, pseudo, password, role, salt) VALUES (?, ?, ?, ?, ?)";
                                try (PreparedStatement pstmt = conn.prepareStatement(create_user)) {
                                    pstmt.setString(1, email);
                                    pstmt.setString(2, pseudo);
                                    pstmt.setString(3, hashedPassword);
                                    pstmt.setString(4, role);
                                    pstmt.setString(5, Base64.getEncoder().encodeToString(salt));
                                    pstmt.executeUpdate();
                                    conn.commit();
                                } catch (SQLException ex) {
                                    try {
                                        conn.rollback();
                                    } catch (SQLException rollbackEx) {
                                        throw new RuntimeException(rollbackEx);
                                    }
                                    throw new RuntimeException(ex);
                                }
                            } else {
                                // Handle the case where the email is not whitelisted
                                JOptionPane.showMessageDialog(panel, "Email is not whitelisted.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case READ:
                        // Read all users if parameters are null or empty
                        String read_user;
                        if ("".equals(email) && "".equals(pseudo) && "".equals(role) || (email == null && pseudo == null && role == null)) {
                            // Read all users
                            read_user = "SELECT email, pseudo, role FROM user";
                        } else {
                            // Read specific user
                            read_user = "SELECT email, pseudo, role FROM user WHERE email = ? OR pseudo = ? OR role = ?";
                        }

                        try (PreparedStatement statement = conn.prepareStatement(read_user)) {
                            if (!("".equals(email) && "".equals(pseudo) && "".equals(role) || (email == null && pseudo == null && role == null))) {
                                statement.setString(1, email);
                                statement.setString(2, pseudo);
                                statement.setString(3, role);
                            }

                            try (ResultSet resultSet = statement.executeQuery()) {
                                // Clear existing rows in the table
                                usersPanel.getCurrentTableModel().setRowCount(0);

                                while (resultSet.next()) {
                                    String userEmail = resultSet.getString("email");
                                    String userPseudo = resultSet.getString("pseudo");
                                    String userRole = resultSet.getString("role");

                                    // Create an array with the data
                                    Object[] rowData = {userEmail, userPseudo, userRole};

                                    // Add the data to the table model
                                    usersPanel.getCurrentTableModel().addRow(rowData);
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case DELETE:
                        String delete_user;
                        // We can only delete ourself but if we are admin we can delete everybody
                        if (Objects.equals(role, "Admin")) {

                        }
                        else if (Objects.equals(role, "User")) {
                            if ()
                        }
                        break;
                    case UPDATE:
                        // We can only update ourself but if we are admin we can delete everybody
                        break;
                    default:
                        // noting by default
                        break;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }
}
