package Client.Components;

import server.ItemsInventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryPanel extends JPanel {
    private DefaultTableModel currentTableModel;
    private JTextField ItemName;
    private JTextField ItemID;
    private JTextField StoreID;
    private JTextField InventoryField;
    private JTextField PriceField;
    private JTextField QuantityField;
    private JButton ConfirmButton;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Object[][] initialInventoryData = {};
        Object[] inventoryColumnNames = {"item_id", "inventory_id", "name", "price", "quantity_in_stock"};

        currentTableModel = new DefaultTableModel(initialInventoryData, inventoryColumnNames);

        JTable table = new JTable(currentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addProductButton = new JButton("Add Product");

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Choose the inventory");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Inventory ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                InventoryField = new JTextField(20);
                panel.add(InventoryField, gbc);

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
                        JFrame inputFrame = new JFrame("Select the parameter of your item");
                        inputFrame.setSize(900, 600);
                        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        // Select the row you want to update by choising the ID
                        //Open a Jframe with a space for choising graphical
                        JPanel ID = new JPanel(new GridBagLayout());
                        GridBagConstraints gbc = new GridBagConstraints();
                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        ID.add(new JLabel("Name of the item: "), gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        ItemName = new JTextField(20);
                        ID.add(ItemName, gbc);

                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 1;
                        ID.add(new JLabel("Price of the item: "), gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 1;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        PriceField = new JTextField(20);
                        ID.add(PriceField, gbc);

                        gbc.insets = new Insets(5, 5, 5, 5);
                        gbc.gridx = 0;
                        gbc.gridy = 2;
                        ID.add(new JLabel("Quantity of the item : "), gbc);
                        gbc.gridx = 1;
                        gbc.gridy = 2;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        QuantityField = new JTextField(20);
                        ID.add(QuantityField, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 3;
                        gbc.anchor = GridBagConstraints.WEST;
                        ConfirmButton = new JButton("Confirm");
                        ID.add(ConfirmButton, gbc);
                        ConfirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Call the UserButton method with the entered values
                                ItemsInventory ItemsButton = new ItemsInventory(
                                        InventoryField,
                                        ItemName,
                                        null,
                                        PriceField,
                                        QuantityField,
                                        InventoryPanel.this,
                                        InventoryPanel.this,
                                        ItemsInventory.OperationType.CREATE_ITEM
                                );
                                ItemsButton.actionPerformed(e);

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
        buttonPanel.add(addProductButton);

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Choose the item you want to delete");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Item ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                ItemID = new JTextField(20);
                panel.add(ItemID, gbc);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(new JLabel("Inventory ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                InventoryField = new JTextField(20);
                panel.add(InventoryField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ItemsInventory ItemsButton = new ItemsInventory(
                                InventoryField,
                                null,
                                ItemID,
                                null,
                                null,
                                InventoryPanel.this,
                                InventoryPanel.this,
                                ItemsInventory.OperationType.DELETE_ITEM
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
        buttonPanel.add(deleteProductButton);

        JButton editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Choose the item you want to Edit");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Item ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                ItemID = new JTextField(20);
                panel.add(ItemID, gbc);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 2;
                panel.add(new JLabel("Inventory ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                ItemID = new JTextField(20);
                panel.add(ItemID, gbc);

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
                        JFrame inputFrame = new JFrame("Select the changement you want to do ");
                        inputFrame.setSize(900, 600);
                        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        JPanel ID = new JPanel(new GridBagLayout());
                        GridBagConstraints gdc = new GridBagConstraints();
                        gdc.insets = new Insets(5, 5, 5, 5);

                        // Add components to the panel
                        gdc.gridx = 0;
                        gdc.gridy = 0;
                        ID.add(new JLabel("Item Name changement :"), gdc);

                        gdc.gridx = 1;
                        gdc.gridy = 0;
                        gdc.fill = GridBagConstraints.HORIZONTAL;
                        ItemName = new JTextField(20);
                        ID.add(ItemName, gdc);

                        // Add components to the panel
                        gdc.gridx = 0;
                        gdc.gridy = 1;
                        ID.add(new JLabel("Price changement :"), gdc);

                        gdc.gridx = 1;
                        gdc.gridy = 1;
                        gdc.fill = GridBagConstraints.HORIZONTAL;
                        PriceField = new JTextField(20);
                        ID.add(PriceField, gdc);

                        // Add components to the panel
                        gdc.gridx = 0;
                        gdc.gridy = 2;
                        ID.add(new JLabel("Quantity changement :"), gdc);

                        gdc.gridx = 1;
                        gdc.gridy = 2;
                        gdc.fill = GridBagConstraints.HORIZONTAL;
                        QuantityField = new JTextField(20);
                        ID.add(QuantityField, gdc);

                        gdc.gridx = 1;
                        gdc.gridy = 3;
                        gdc.anchor = GridBagConstraints.WEST;

                        JButton confirmButton = new JButton("Confirm");
                        ID.add(confirmButton, gdc);
                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Call the UserButton method with the entered values
                                ItemsInventory ItemsButton = new ItemsInventory(
                                        InventoryField,
                                        ItemName,
                                        ItemID,
                                        PriceField,
                                        QuantityField,
                                        InventoryPanel.this,
                                        InventoryPanel.this,
                                        ItemsInventory.OperationType.EDIT_ITEM
                                );
                                ItemsButton.actionPerformed(e);

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
        buttonPanel.add(editProductButton);

        JButton ReadInventoryButton = new JButton("Read Inventory");
        ReadInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for user input
                JFrame inputFrame = new JFrame("Choose the inventory");
                inputFrame.setSize(900, 600);
                inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create a panel with a GridBagLayout for better layout control
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                // Add components to the panel
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(new JLabel("Inventory ID:"), gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                InventoryField = new JTextField(20);
                panel.add(InventoryField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.WEST;

                // Create a button to confirm the input
                JButton confirmButton = new JButton("Confirm");
                panel.add(confirmButton, gbc);
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ItemsInventory ItemsButton = new ItemsInventory(
                                InventoryField,
                                null,
                                null,
                                null,
                                null,
                                InventoryPanel.this,
                                InventoryPanel.this,
                                ItemsInventory.OperationType.READ_INVENTORY
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

    public DefaultTableModel getCurrentTableModel() {
        return currentTableModel;
    }
}