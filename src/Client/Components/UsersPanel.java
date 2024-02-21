package Client.Components;

import server.UserButton;

import javax.management.relation.Role;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.interfaces.EdECKey;

public class UsersPanel extends JPanel {

    private DefaultTableModel currentTableModel;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField pseudoField;
    private JTextField RoleField;
    private JButton IDButton;
    private JTextField IDField;

    public UsersPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        Object[][] initialUserData = {};
        Object[] userColumnNames = {"id", "Store_id", "Email","Pseudo","Role"};

        currentTableModel = new DefaultTableModel(initialUserData, userColumnNames);

        JTable table = new JTable(currentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Add User");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 2;
                panel.add(new JLabel("Pseudo:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pseudoField = new JTextField(20);
                panel.add(pseudoField, gbc);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 3;
                panel.add(new JLabel("Role:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 3;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                RoleField = new JTextField(20);
                panel.add(RoleField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 4;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Call the UserButton method with the entered values
                        UserButton userButton = new UserButton(
                                emailField,
                                passwordField,
                                pseudoField,
                                RoleField,
                                null,
                                UsersPanel.this,
                                UsersPanel.this,
                                UserButton.OperationType.CREATE
                        );
                        userButton.actionPerformed(e);

                        inputFrame.dispose();
                    }
                });
                // Set layout for input frame
                inputFrame.setLayout(new BorderLayout());
                inputFrame.add(panel, BorderLayout.CENTER);

                // Display the input frame
                inputFrame.setVisible(true);
            }
        });
        buttonPanel.add(addUserButton);

        JButton editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Setup the new parameter");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(new JLabel("Pseudo:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pseudoField = new JTextField(20);
                panel.add(pseudoField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a new JFrame for user input
                        JFrame inputFrame = new JFrame("Select the id of the account you want to apply the change");
                        inputFrame.setSize(900, 600);
                        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        // Select the row you want to update by choising the ID
                        //Open a Jframe with a space for choising graphical
                        JPanel ID = new JPanel(new GridBagLayout());
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        ID.add(new JLabel("Select the ID of the account: "), gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        IDField = new JTextField(20);
                        ID.add(IDField, gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 1;
                        gbc.anchor = GridBagConstraints.WEST;
                        IDButton = new JButton("Confirm");
                        ID.add(IDButton, gbc);
                        IDButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Call the UserButton method with the entered values
                                UserButton userButton = new UserButton(
                                        emailField,
                                        null,
                                        pseudoField,
                                        null,
                                        IDField,
                                        UsersPanel.this,
                                        UsersPanel.this,
                                        UserButton.OperationType.UPDATE
                                );
                                userButton.actionPerformed(e);

                                inputFrame.dispose();
                            }
                        });
                        // Set layout for input frame
                        inputFrame.setLayout(new BorderLayout());
                        inputFrame.add(ID, BorderLayout.CENTER);

                        // Display the input frame
                        inputFrame.setVisible(true);

                    }
                });
                // Set layout for input frame
                inputFrame.setLayout(new BorderLayout());
                inputFrame.add(panel, BorderLayout.CENTER);

                // Display the input frame
                inputFrame.setVisible(true);
            }
        });
        buttonPanel.add(editUserButton);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Delete User");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(new JLabel("Pseudo:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pseudoField = new JTextField(20);
                panel.add(pseudoField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Call the UserButton method with the entered values
                        UserButton userButton = new UserButton(
                                emailField,
                                null,
                                pseudoField,
                                null,
                                null,
                                UsersPanel.this,
                                UsersPanel.this,
                                UserButton.OperationType.DELETE
                        );
                        userButton.actionPerformed(e);

                        inputFrame.dispose();
                    }
                });
                // Set layout for input frame
                inputFrame.setLayout(new BorderLayout());
                inputFrame.add(panel, BorderLayout.CENTER);

                // Display the input frame
                inputFrame.setVisible(true);
            }
        });
        buttonPanel.add(deleteUserButton);

        JButton ReadUserButton = new JButton("Read User");
        ReadUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Read User");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(new JLabel("Pseudo:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                pseudoField = new JTextField(20);
                panel.add(pseudoField, gbc);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 2;
                panel.add(new JLabel("Role:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                RoleField = new JTextField(20);
                panel.add(RoleField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 3;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Call the UserButton method with the entered values
                        UserButton userButton = new UserButton(
                                emailField,
                                null,
                                pseudoField,
                                RoleField,
                                null,
                                UsersPanel.this,
                                UsersPanel.this,
                                UserButton.OperationType.READ
                        );
                        userButton.actionPerformed(e);

                        inputFrame.dispose();
                    }
                });
                // Set layout for input frame
                inputFrame.setLayout(new BorderLayout());
                inputFrame.add(panel, BorderLayout.CENTER);

                // Display the input frame
                inputFrame.setVisible(true);
            }
        });
        buttonPanel.add(ReadUserButton);

        add(buttonPanel, BorderLayout.WEST);
    }
    // You can add more methods as needed

    public DefaultTableModel getCurrentTableModel() {
        return currentTableModel;
    }
}