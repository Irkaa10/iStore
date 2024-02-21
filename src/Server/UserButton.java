package server;

import Client.Components.DashboardFrame;
import Client.Components.PasswordUtils;
import Client.Components.UsersPanel;

import javax.swing.*;
import java.awt.*;
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
    private UsersPanel UsersPanel;
    private OperationType operationType;
    private JTextField IDField;

    // Enumeration for different operations
    public enum OperationType {
        CREATE, READ, DELETE, UPDATE
    }

    public UserButton(JTextField emailField, JPasswordField passwordField, JTextField pseudoField, JTextField roleField, JTextField IDfield, JPanel panel, UsersPanel usersPanel, OperationType operationType) {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.pseudoField = pseudoField;
        this.roleField = roleField;
        this.IDField = IDfield;
        this.panel = panel;
        this.UsersPanel = usersPanel;
        this.operationType = operationType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email, pseudo, role;
        String role_session = DashboardFrame.Get_role();
        String user_session = DashboardFrame.Get_username();
        String email_session = DashboardFrame.Get_email();
        String ID_session = DashboardFrame.Get_ID();
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
                        email = emailField.getText();
                        pseudo = pseudoField.getText();
                        role = roleField.getText();
                        // We can create an account, but we need to verify if the email is whitelisted
                        String create_user;
                        boolean isEmailWhitelisted = false;
                        if (!("".equals(email) || "".equals(pseudo) || "".equals(role))) {
                            // TODO: Add the condition to verify if the email is inside the whitelist table
                            String email_whitelist = "SELECT email from whitelisted_email WHERE email = ?";
                            try (PreparedStatement pstmt = conn.prepareStatement(email_whitelist)) {
                                pstmt.setString(1, email);
                                try (ResultSet resultSet = pstmt.executeQuery()) {
                                    if (resultSet.next()) {
                                        isEmailWhitelisted = true;
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
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
                        else {
                            JOptionPane.showMessageDialog(panel, "Every TextBox need to be full.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case READ:
                        email = emailField.getText();
                        pseudo = pseudoField.getText();
                        role = roleField.getText();
                        // Read all users if parameters are null or empty
                        String read_user;
                        if ("".equals(email) && "".equals(pseudo) && "".equals(role) || (email == null && pseudo == null && role == null)) {
                            // Read all users
                            read_user = "SELECT * FROM user";
                        } else {
                            // Read specific user
                            read_user = "SELECT * FROM user WHERE email = ? OR pseudo = ? OR role = ?";
                        }

                        try (PreparedStatement statement = conn.prepareStatement(read_user)) {
                            if (!("".equals(email) && "".equals(pseudo) && "".equals(role) || (email == null && pseudo == null && role == null))) {
                                statement.setString(1, email);
                                statement.setString(2, pseudo);
                                statement.setString(3, role);
                            }

                            try (ResultSet resultSet = statement.executeQuery()) {
                                // Clear existing rows in the table
                                UsersPanel.getCurrentTableModel().setRowCount(0);

                                while (resultSet.next()) {
                                    String Userid = resultSet.getString("id");
                                    String Storeid = resultSet.getString("store_id");
                                    String userEmail = resultSet.getString("email");
                                    String userPseudo = resultSet.getString("pseudo");
                                    String userRole = resultSet.getString("role");

                                    // Create an array with the data
                                    Object[] rowData = {Userid,Storeid,userEmail, userPseudo, userRole};

                                    // Add the data to the table model
                                    UsersPanel.getCurrentTableModel().addRow(rowData);
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case DELETE:
                        email = emailField.getText();
                        pseudo = pseudoField.getText();
                        String delete_user;
                        // We can only delete ourself but if we are admin we can delete everybody
                        if (Objects.equals(role_session, "Admin")) {
                            delete_user = "DELETE FROM user WHERE email = ? AND pseudo = ?";
                            try (PreparedStatement state = conn.prepareStatement(delete_user)) {
                                state.setString(1, email);
                                state.setString(2, pseudo);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (Objects.equals(role_session, "User")) {
                            if (Objects.equals(pseudo, user_session) && Objects.equals(email, email_session)) {
                                delete_user = "DELETE FROM user WHERE email = ? AND pseudo = ?";
                                try (PreparedStatement state = conn.prepareStatement(delete_user)) {
                                    state.setString(1, email);
                                    state.setString(2, pseudo);
                                    state.executeUpdate();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(panel, "You can't delete the account of other people.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case UPDATE:
                        email = emailField.getText();
                        pseudo = pseudoField.getText();
                        // We can only update ourself but if we are admin we can update everybody
                        String update_user;

                        String ID = IDField.getText();
                        System.out.println(ID);
                        System.out.println(ID_session);
                        if (Objects.equals(role_session, "Admin") || (Objects.equals(ID, ID_session))) {
                            update_user = "UPDATE user SET email = ?, pseudo = ? WHERE ID = ?";
                            try (PreparedStatement state = conn.prepareStatement(update_user)) {
                                state.setString(1, email);
                                state.setString(2, pseudo);
                                state.setString(3, ID); //or ID_session here we consider they are the same
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "You can't update the account of other people.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

