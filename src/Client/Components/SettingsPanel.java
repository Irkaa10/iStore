package Client.Components;

import server.SettingsInventory;
import server.UserButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    private JTextField EmailChoice;
    private JButton EmailButton;

    public SettingsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton AddUserButton = new JButton("Add to Whitelist");
        AddUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Add User");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel emailPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gdc = new GridBagConstraints();
                gdc.insets = new Insets(5, 5, 5, 5);
                gdc.gridx = 0;
                gdc.gridy = 0;
                emailPanel.add(new JLabel("Select the Email of the account: "), gdc);
                gdc.gridx = 1;
                gdc.gridy = 0;
                gdc.fill = GridBagConstraints.HORIZONTAL;
                EmailChoice = new JTextField(20);
                emailPanel.add(EmailChoice, gdc);
                gdc.gridx = 1;
                gdc.gridy = 1;
                gdc.anchor = GridBagConstraints.WEST;
                EmailButton = new JButton("Confirm");
                emailPanel.add(EmailButton, gdc);
                EmailButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implement logic to add product
                        // Call the UserButton method with the entered values
                        SettingsInventory SettingsButton = new SettingsInventory(
                                EmailChoice,
                                SettingsPanel.this,
                                SettingsPanel.this,
                                SettingsInventory.OperationType.WHITELIST_ADD
                        );
                        SettingsButton.actionPerformed(e);
                        inputFrame.dispose();
                    }
                });
                // Set layout for input frame
                inputFrame.setLayout(new BorderLayout());
                inputFrame.add(emailPanel, BorderLayout.CENTER);

                // Display the input frame
                inputFrame.setVisible(true);
            }
        });
        buttonPanel.add(AddUserButton);

        add(buttonPanel, BorderLayout.WEST);
    }

}
