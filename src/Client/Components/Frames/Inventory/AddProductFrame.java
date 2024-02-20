package Client.Components.Frames.Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductFrame extends JFrame {

    private DefaultTableModel currentTableModel;
    private JTable table;

    public AddProductFrame(DefaultTableModel currentTableModel, JTable table) {
        this.currentTableModel = currentTableModel;
        this.table = table;

        initUI();
    }

    private void initUI() {
        setTitle("Add Product");
        setLayout(new GridLayout(4, 2, 10, 10));

        JTextField nameInput = new JTextField(10);
        JTextField stockInput = new JTextField(10);
        JTextField priceInput = new JTextField(10);
        JButton confirmButton = new JButton("Add Product");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract values from text fields
                    String name = nameInput.getText();
                    int stock = Integer.parseInt(stockInput.getText());
                    double price = Double.parseDouble(priceInput.getText());

                    // Add new row to the table
                    Object[] newRow = {name, stock, price};
                    currentTableModel.addRow(newRow);

                    // Close the frame after adding the product
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddProductFrame.this, "Please enter valid values for stock and price.");
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
