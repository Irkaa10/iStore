package Client.Components;

import server.LoginListener;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton LoginButton;

    public LoginScreen() {
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a GridBagLayout for better layout control
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        LoginButton = new JButton("Login");

        LoginListener LoginListener = new LoginListener(emailField, passwordField, this);
        //refer to the file Client/LoginListener
        LoginButton.addActionListener(LoginListener);

        panel.add(LoginButton, gbc);

        // Add an empty border for better spacing
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
