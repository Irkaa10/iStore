package Client.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {

    public LoginPage() {
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform login logic
                // For simplicity, assume login is successful
                JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");

                // Open the Client.Components.DashboardFrame after successful login
                DashboardFrame dashboardFrame = new DashboardFrame();
                dashboardFrame.setVisible(true);
                ((JFrame) SwingUtilities.getRoot(LoginPage.this)).dispose();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);
    }
}
