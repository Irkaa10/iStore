package Server.Controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Client.Components.Frames.Inventory.ReduceStockFrame;
import Server.DB.DBConfig;

public class ReduceStockController implements ActionListener {

    private ReduceStockFrame frame;
    private JTextField stockInput;

    public ReduceStockController(ReduceStockFrame frame, JTextField stockInput) {
        this.frame = frame;
        this.stockInput = stockInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int removedStock = Integer.parseInt(stockInput.getText());
            int selectedRow = frame.getSelectedRow();
            String productName = frame.getCurrentTableModel().getValueAt(selectedRow, 0).toString();

            // Execute the SQL update query to reduce the stock in the database
            updateStockInDatabase(productName, removedStock);

            frame.dispose(); // Close the frame after updating
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid positive integer for stock.");
        }
    }

    private void updateStockInDatabase(String productName, int removedStock) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get a database connection
            connection = DBConfig.getConnection();

            // SQL query to update the stock in the 'YourStockTable' table
            String query = "UPDATE Inventory SET Stock = Stock - ? WHERE ProductName = ?";
            preparedStatement = connection.prepareStatement(query);

            // Set parameters for the query
            preparedStatement.setInt(1, removedStock);
            preparedStatement.setString(2, productName);

            // Execute the update query
            preparedStatement.executeUpdate();

            System.out.println("Stock reduced successfully in the database.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to update stock in the database.");
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
