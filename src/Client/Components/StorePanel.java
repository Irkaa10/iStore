package Client.Components;

import server.ItemsInventory;
import server.SettingsInventory;
import server.StoreInventory;
import server.UserButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StorePanel extends JPanel {
    private static DefaultTableModel currentTableModel;
    private JTextField StoreName;
    private JTextField StoreID;
    private JTextField USERID;
    private JButton ConfirmButton;

    public StorePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        Object[][] initialStoreData = {};
        Object[] storeColumnNames = {"store_id", "store_name"};

        currentTableModel = new DefaultTableModel(initialStoreData, storeColumnNames);

        JTable table = new JTable(currentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton createStoreButton = new JButton("Create Store");
        createStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Name the store");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Name Store:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                StoreName = new JTextField(20);
                panel.add(StoreName, gbc);

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
                        StoreInventory createStoreButton = new StoreInventory(
                                null,
                                StoreName,
                                null,
                                StorePanel.this,
                                StorePanel.this,
                                StoreInventory.OperationType.CREATE_STORE
                        );
                        createStoreButton.actionPerformed(e);

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
        buttonPanel.add(createStoreButton);

        JButton editStoreButton = new JButton("Edit Store");
        editStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Give the Store ID");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Store ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                StoreName = new JTextField(20);
                panel.add(StoreName, gbc);

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
                        JFrame inputFrame = new JFrame("Select the parameters");
                        inputFrame.setSize(900, 600);
                        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        JPanel ID = new JPanel(new GridBagLayout());
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        ID.add(new JLabel("New Store Name : "), gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        StoreName = new JTextField(20);

                        ID.add(StoreName, gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 1;
                        gbc.anchor = GridBagConstraints.WEST;
                        ConfirmButton = new JButton("Confirm");
                        ID.add(ConfirmButton, gbc);
                        ConfirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Call the UserButton method with the entered values
                                StoreInventory userButton = new StoreInventory(
                                        null,
                                        StoreName,
                                        null,
                                        StorePanel.this,
                                        StorePanel.this,
                                        StoreInventory.OperationType.EDIT_STORE
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
        buttonPanel.add(editStoreButton);

        JButton addToStoreButton = new JButton("Add Employee");
        addToStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Give the User Information");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Store ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                StoreID = new JTextField(20);
                panel.add(StoreID, gbc);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(new JLabel("USER ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                USERID = new JTextField(20);
                panel.add(USERID, gbc);

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
                        StoreInventory createStoreButton = new StoreInventory(
                                USERID,
                                null,
                                StoreID,
                                StorePanel.this,
                                StorePanel.this,
                                StoreInventory.OperationType.ADD_EMPLOYEE
                        );
                        createStoreButton.actionPerformed(e);

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
        buttonPanel.add(addToStoreButton);

        JButton ReadInventoryButton = new JButton("Read Stores");
        ReadInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Choose the Store");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Store ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                StoreID = new JTextField(20);
                panel.add(StoreID, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        StoreInventory ItemsButton = new StoreInventory(
                                null,
                                null,
                                StoreID,
                                StorePanel.this,
                                StorePanel.this,
                                StoreInventory.OperationType.READ_STORE
                        );
                        ItemsButton.actionPerformed(e);

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
        buttonPanel.add(ReadInventoryButton);

        add(buttonPanel, BorderLayout.WEST);
    }

    public static DefaultTableModel getCurrentTableModel() {
        return currentTableModel;
    }
}