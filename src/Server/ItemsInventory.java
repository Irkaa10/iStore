package server;

import Client.Components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;
import java.util.Objects;

public class ItemsInventory implements ActionListener {
    private JTextField InventoryField;
    private JTextField ItemName;
    private JTextField ItemID;
    private JTextField PriceField;
    private JTextField QuantityField;
    private JPanel panel;
    private InventoryPanel InventoryPanel;
    private OperationType operationType;

    // Enumeration for different operations
    public enum OperationType {
        CREATE_ITEM, EDIT_ITEM, DELETE_ITEM, READ_INVENTORY
    }

    public ItemsInventory(JTextField InventoryField, JTextField ItemName, JTextField ItemID, JTextField PriceField, JTextField QuantityField, JPanel panel, InventoryPanel InventoryPanel, OperationType operationType) {
        this.InventoryField = InventoryField;
        this.ItemName = ItemName;
        this.ItemID = ItemID;
        this.PriceField = PriceField;
        this.QuantityField = QuantityField;
        this.panel = panel;
        this.InventoryPanel = InventoryPanel;
        this.operationType = operationType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String itemname, pricenb, quantitynb, inventoryID, itemID, storeID;
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
                    case CREATE_ITEM:
                        String create_item;
                        //CREATE AN ITEM BY TAKING ALL THE PARAMETERS PUT INSIDE THE FIELD
                        inventoryID = InventoryField.getText();
                        itemname = ItemName.getText();
                        quantitynb = QuantityField.getText();
                        pricenb = PriceField.getText();

                        if ((Objects.equals(role_session, "Admin")) && ((!("".equals(itemname)) )|| (!("".equals(pricenb))))) {
                            create_item = "INSERT INTO items (inventory_id, name, price, quantity_in_stock) VALUES (?,?,?,?)";
                            try (PreparedStatement state = conn.prepareStatement(create_item)) {
                                state.setString(1, inventoryID);
                                state.setString(2, itemname);
                                state.setString(3, quantitynb);
                                state.setString(4, pricenb);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "You can't create an item because you are not an Administrator OR you didn't pass all the arguments.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case DELETE_ITEM:
                        String delete_item;
                        //DELETE AN ITEM BY TAKING THE ID OF THE ITEM AND THE INVENTORY ID HE IS STOCK ON PUT INSIDE THE FIELD
                        inventoryID = InventoryField.getText();
                        itemID = ItemID.getText();

                        if (Objects.equals(role_session, "Admin") && ((!("".equals(inventoryID)) ) || (!("".equals(itemID))))) {
                            delete_item = "DELETE FROM items WHERE item_id = ? AND inventory_id = ?";
                            try (PreparedStatement state = conn.prepareStatement(delete_item)) {
                                state.setString(1, itemID);
                                state.setString(2, inventoryID);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "You can't delete an item because you are not an Administrator OR you didn't pass all the arguments.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case EDIT_ITEM:
                        itemname = ItemName.getText();
                        inventoryID = InventoryField.getText();
                        pricenb = PriceField.getText();
                        quantitynb = QuantityField.getText();
                        itemID = ItemID.getText();

                        String fetchedStoreInventoryID = null;
                        String fetchedStoreIdItem = null;

                        String FindStoreIdByInventoryTab = "SELECT store_id FROM inventory WHERE inventory_id = ?";
                        String StoreIdUser = "SELECT store_id FROM user WHERE pseudo = ? AND email = ? ";

                        try (PreparedStatement state = conn.prepareStatement(FindStoreIdByInventoryTab)) {
                            state.setString(1, inventoryID);
                            ResultSet rs = state.executeQuery(); // Correct method for SELECT
                            if (rs.next()) {
                                fetchedStoreInventoryID = rs.getString("store_id");
                                System.out.println(fetchedStoreInventoryID);
                                // Use fetchedStoreIdUser as needed
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        // We can only update the quantity when it is an item inside the store we are identify (USER)

                        // For fetching store_id from items
                        try (PreparedStatement state = conn.prepareStatement(StoreIdUser)) {
                            state.setString(1, itemname);
                            state.setString(2, itemID);
                            ResultSet rs = state.executeQuery(); // Correct method for SELECT
                            if (rs.next()) {
                                fetchedStoreIdItem = rs.getString("store_id");
                                System.out.println(fetchedStoreIdItem);
                                // Use fetchedStoreIdItem as needed
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        // We can update all the parameter
                        String edit_item;

                        if (Objects.equals(role_session, "Admin")) {
                            edit_item = "UPDATE items SET name = ?, price = ?, quantity_in_stock = ? WHERE item_id = ?";
                            try (PreparedStatement state = conn.prepareStatement(edit_item)) {
                                state.setString(1, itemname);
                                state.setString(2, pricenb);
                                state.setString(3, quantitynb); //or item_id here we consider they are the same
                                state.setString(4, itemID); //or item_id here we consider they are the same
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else if ((Objects.equals(fetchedStoreInventoryID, fetchedStoreIdItem)) && (("".equals(itemname)) && ("".equals(pricenb)))){
                            edit_item = "UPDATE items SET quantity_in_stock = ? WHERE item_id = ?";
                            try (PreparedStatement state = conn.prepareStatement(edit_item)) {
                                state.setString(3, quantitynb);
                                state.setString(4, itemID);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "You can't update this if you are not an Administrator (name, price), try by letting the name and price blank", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case READ_INVENTORY:
                        //CAN READ ALL THE ITEMS INSIDE A INVENTORY
                        inventoryID = InventoryField.getText();
                        String read_item;

                        if ("".equals(inventoryID) || (inventoryID == null)) {
                            // Read all store
                            read_item = "SELECT * FROM items";
                        } else {
                            // Read specific store
                            read_item = "SELECT * FROM items WHERE inventory_id = ?";
                        }

                        // We can only update the quantity when it is an item inside the store we are identify (USER)
                        try (PreparedStatement state = conn.prepareStatement(read_item)) {
                            if (!("".equals(inventoryID) || (inventoryID == null))) {
                                state.setString(1, inventoryID);
                            }

                            try (ResultSet resultSet = state.executeQuery()) {
                                // Clear existing rows in the table
                                InventoryPanel.getCurrentTableModel().setRowCount(0);

                                while (resultSet.next()) {
                                    String item_id = resultSet.getString("item_id");
                                    String inventory_id = resultSet.getString("inventory_id");
                                    String name = resultSet.getString("name");
                                    String price = resultSet.getString("price");
                                    String quantity_in_stock = resultSet.getString("quantity_in_stock");

                                    // Create an array with the data
                                    Object[] rowData = {item_id, inventory_id, name,price,quantity_in_stock};

                                    // Add the data to the table model
                                    InventoryPanel.getCurrentTableModel().addRow(rowData);
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
