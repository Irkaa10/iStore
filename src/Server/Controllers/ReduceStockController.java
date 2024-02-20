package Server.Controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Client.Components.Frames.Inventory.ReduceStockFrame;

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

            // Print the SQL query to the console
            printUpdateQuery(productName, removedStock);

            // Update the table model (commented for testing purposes)
            /*
            int currentStock = Integer.parseInt(frame.getCurrentTableModel().getValueAt(selectedRow, 1).toString());
            int newStock = currentStock - removedStock;
            frame.getCurrentTableModel().setValueAt(newStock, selectedRow, 1);
            */

            frame.dispose(); // Close the frame after updating
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid positive integer for stock.");
        }
    }

    private void printUpdateQuery(String productName, int removedStock) {
        String query = "UPDATE YourStockTable SET stock_quantity = stock_quantity - ? WHERE product_name = ?";
        System.out.println("Query to be executed: " + query);
        System.out.println("Parameters: " + removedStock + ", " + productName);
    }
}
