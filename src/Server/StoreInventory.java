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

public class StoreInventory implements ActionListener {
    private JTextField StoreName;
    private JTextField StoreID;
    private JTextField UserID;
    private JPanel panel;
    private StorePanel StorePanel;
    private OperationType operationType;
    public enum OperationType {
        CREATE_STORE,EDIT_STORE,READ_STORE,ADD_EMPLOYEE
    }

    public StoreInventory(JTextField UserID, JTextField storeName, JTextField storeID, JPanel panel, StorePanel StorePanel, OperationType operationType) {
        this.UserID = UserID;
        this.StoreName = storeName;
        this.StoreID = storeID;
        this.panel = panel;
        this.StorePanel = StorePanel;
        this.operationType = operationType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userID, storeName, storeID;
        String ID_session = DashboardFrame.Get_ID();
        String role_session = DashboardFrame.Get_role();
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
                    case CREATE_STORE:
                        String create_store = "INSERT INTO store (store_name) VALUES (?)";
                        String create_inventory = "INSERT INTO inventory (store_id) VALUES (?)";

                        String store_session_name = StoreName.getText();

                        if (Objects.equals(role_session, "Admin")) {
                            try {
                                conn.setAutoCommit(false);  // Start a transaction
                                try (
                                        PreparedStatement state = conn.prepareStatement(create_store, Statement.RETURN_GENERATED_KEYS)) {
                                    state.setString(1, store_session_name);
                                    state.executeUpdate();
                                    // Retrieve the generated store_id
                                    ResultSet generatedKeys = state.getGeneratedKeys();
                                    int store_id = -1;
                                    if (generatedKeys.next()) {
                                        store_id = generatedKeys.getInt(1);
                                    } else {
                                        throw new SQLException("Failed to retrieve store_id.");
                                    }

                                    // Create the inventory using the obtained store_id
                                    try (PreparedStatement inventoryStatement = conn.prepareStatement(create_inventory)) {
                                        inventoryStatement.setInt(1, store_id);
                                        inventoryStatement.executeUpdate();
                                    }
                                }
                                conn.commit();  // Commit the transaction if everything is successful
                            } catch (
                                    SQLException ex) {
                                try {
                                    conn.rollback();  // Rollback the transaction if an exception occurs
                                } catch (SQLException rollbackEx) {
                                    // Handle rollback exception
                                }
                                throw new RuntimeException(ex);
                            } finally {
                                try {
                                    conn.setAutoCommit(true);  // Set AutoCommit back to true
                                } catch (SQLException setAutoCommitEx) {
                                    // Handle setAutoCommit exception
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "You can't create a store if you are not an Administrator.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case EDIT_STORE:
                        storeName = StoreName.getText();
                        storeID = StoreID.getText();

                        String fetchedStoreIDByUserID = null;
                        String fetchedStoreId = null;

                        String FindStoreIDByUserID = "SELECT store_id FROM user WHERE user_id = ?";
                        String StoreId = "SELECT store_id FROM store WHERE store_id = ?";

                        try (PreparedStatement state = conn.prepareStatement(FindStoreIDByUserID)) {
                            state.setString(1, ID_session);
                            ResultSet rs = state.executeQuery();
                            if (rs.next()) {
                                fetchedStoreIDByUserID = rs.getString("store_id");
                                // Use fetchedStoreIDByUserID as needed
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        // We can only update the store when we are inside the store (USER)

                        // For fetching store_id from store
                        try (PreparedStatement state = conn.prepareStatement(StoreId)) {
                            state.setString(1, storeID);
                            ResultSet rs = state.executeQuery();
                            if (rs.next()) {
                                fetchedStoreId = rs.getString("store_id");
                                System.out.println(fetchedStoreId);
                                // Use fetchedStoreId as needed
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        // We can update all the parameter
                        String edit_store;

                        if ((Objects.equals(role_session, "Admin")) || ((Objects.equals(fetchedStoreIDByUserID, fetchedStoreId)))) {
                            edit_store = "UPDATE store SET store_name = ? WHERE store_id = ?";
                            try (PreparedStatement state = conn.prepareStatement(edit_store)) {
                                state.setString(1, storeName);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "You can't update this if you are not an Administrator or it is not your store", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case READ_STORE:
                        //CAN READ ALL THE STORES
                        storeID = StoreID.getText();
                        String read_store;
                        if ("".equals(storeID) || (storeID == null)) {
                            // Read all store
                            read_store = "SELECT * FROM store";
                        } else {
                            // Read specific store
                            read_store = "SELECT * FROM store WHERE store_id = ?";
                        }
                        // We can read all the things when we want
                        try (PreparedStatement state = conn.prepareStatement(read_store)) {
                            if (!("".equals(storeID) || (storeID == null))) {
                                state.setString(1, storeID);
                            }

                            try (ResultSet resultSet = state.executeQuery()) {
                                // Clear existing rows in the table
                                StorePanel.getCurrentTableModel().setRowCount(0);

                                while (resultSet.next()) {
                                    String store_id = resultSet.getString("store_id");
                                    String store_name = resultSet.getString("store_name");

                                    // Create an array with the data
                                    Object[] rowData = {store_id, store_name};

                                    // Add the data to the table model
                                    StorePanel.getCurrentTableModel().addRow(rowData);
                                }
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case ADD_EMPLOYEE:
                        storeID = StoreID.getText();
                        userID = UserID.getText();

                        String get_user_info;
                        if ((Objects.equals(role_session, "Admin")) || (!("".equals(storeID) || (userID == null)))) {
                            get_user_info = "UPDATE user SET store_id = ? WHERE user_id = ?";
                            try (PreparedStatement state = conn.prepareStatement(get_user_info)) {
                                state.setString(1, storeID);
                                state.setString(2, userID);
                                state.executeUpdate();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, "Filled the parameters", "Error", JOptionPane.ERROR_MESSAGE);
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

