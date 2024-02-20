package Client.Components;

import server.SignupListener;

import javax.swing.*;
import java.awt.*;

public class SignupScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField PseudoField;
    private JButton signupButton;

    public SignupScreen() {
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a GridBagLayout for better layout control
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the panel: email
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);

        // password
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // pseudo
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Pseudo:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PseudoField = new JTextField(20);
        panel.add(PseudoField, gbc);

        // signup button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        signupButton = new JButton("Signup");

        // signup button function
        // Inside SignupScreen class
        SignupListener signupListener = new SignupListener(emailField, passwordField, PseudoField, this);
        signupButton.addActionListener(signupListener);


        panel.add(signupButton, gbc);

        // Add an empty border for better spacing
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupScreen::new);
    }
}
