package Client.Components.Frames.Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductFrame extends JFrame {

    private DefaultTableModel currentTableModel;
    private JTable table;
    private int selectedRow;

    public EditProductFrame(DefaultTableModel currentTableModel, JTable table, int selectedRow) {
        this.currentTableModel = currentTableModel;
        this.table = table;
        this.selectedRow = selectedRow;

        initUI();
    }

    private void initUI() {
        setTitle("Edit Product");
        setLayout(new GridLayout(4, 2, 10, 10));

        // Get current values from the selected row
        String currentName = currentTableModel.getValueAt(selectedRow, 0).toString();
        int currentStock = Integer.parseInt(currentTableModel.getValueAt(selectedRow, 1).toString());
        double currentPrice = Double.parseDouble(currentTableModel.getValueAt(selectedRow, 2).toString());

        JTextField nameInput = new JTextField(currentName, 10);
        JTextField stockInput = new JTextField(String.valueOf(currentStock), 10);
        JTextField priceInput = new JTextField(String.valueOf(currentPrice), 10);
        JButton confirmButton = new JButton("Edit Product");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract values from text fields
                    String newName = nameInput.getText();
                    int newStock = Integer.parseInt(stockInput.getText());
                    double newPrice = Double.parseDouble(priceInput.getText());

                    // Update values in the table's model
                    currentTableModel.setValueAt(newName, selectedRow, 0);
                    currentTableModel.setValueAt(newStock, selectedRow, 1);
                    currentTableModel.setValueAt(newPrice, selectedRow, 2);

                    // Close the frame after editing the product
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditProductFrame.this, "Please enter valid values for stock and price.");
                }
            }
        });

        add(new JLabel("Name: "));
        add(nameInput);
        add(new JLabel("Stock: "));
        add(stockInput);
        add(new JLabel("Price: "));
        add(priceInput);
        add(new JLabel(""));
        add(confirmButton);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
