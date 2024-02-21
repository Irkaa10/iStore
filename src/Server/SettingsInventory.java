package server;

import Client.Components.DashboardFrame;
import Client.Components.PasswordUtils;
import Client.Components.SettingsPanel;
import Client.Components.UsersPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Objects;

public class SettingsInventory implements ActionListener {
    private JTextField emailField;
    private JPanel panel;
    private SettingsPanel SettingsPanel;
    private OperationType operationType;

    // Enumeration for different operations
    public enum OperationType {
        WHITELIST_ADD
    }

    public SettingsInventory(JTextField emailField,JPanel panel, SettingsPanel settingsPanel, OperationType operationType) {
        this.emailField = emailField;
        this.panel = panel;
        this.SettingsPanel = settingsPanel;
        this.operationType = operationType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email, pseudo, storeName, storeID;
        String role_session = DashboardFrame.Get_role();
        String user_session = DashboardFrame.Get_username();
        String email_session = DashboardFrame.Get_email();
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
                    case WHITELIST_ADD:
                        String whitelist_user;
                        // Select the row you want to whitelist by choising the email
                        //Open a Jframe with a space for choising graphical
                        String Email_session = emailField.getText();
                        //whitelist an email
                        if (Objects.equals(role_session, "Admin")) {
                            whitelist_user = "INSERT INTO whitelisted_email (email) VALUES (?)";
                            try (PreparedStatement state = conn.prepareStatement(whitelist_user)) {
                                state.setString(1, Email_session);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "You can't whitelist this email, you are not Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
        }
    }
}