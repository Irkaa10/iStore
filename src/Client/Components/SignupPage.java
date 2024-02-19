package Client.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage extends JPanel {

    public SignupPage() {
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton signupButton = new JButton("Signup");

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform signup logic
                // For simplicity, assume signup is successful
                JOptionPane.showMessageDialog(SignupPage.this, "Signup successful!");

                // Open the Client.Components.DashboardFrame after successful signup
                DashboardFrame dashboardFrame = new DashboardFrame();
                dashboardFrame.setVisible(true);
                ((JFrame) SwingUtilities.getRoot(SignupPage.this)).dispose();
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
        add(signupButton, gbc);
    }
}
