package Client.Components;

import Client.Components.Frames.IncreaseStockFrame;
import Client.Components.Frames.ReduceStockFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryPanel extends JPanel {

    private DefaultTableModel currentTableModel;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Object[][] initialInventoryData = {
                {"Item A", "10", "19.99"},
                {"Item B", "45", "9.99"},
        };
        Object[] inventoryColumnNames = {"Name","Stock", "Price"};

        currentTableModel = new DefaultTableModel(initialInventoryData, inventoryColumnNames);

        JTable table = new JTable(currentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton increaseStockButton = new JButton("Increase stock");
        increaseStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    new IncreaseStockFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(InventoryPanel.this, "Please select an item to increase stock.");
                }
            }
        });
        buttonPanel.add(increaseStockButton);

        JButton reduceStockButton = new JButton("Reduce stock");
        reduceStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    new ReduceStockFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(InventoryPanel.this, "Please select an item to reduce stock.");
                }
            }
        });
        buttonPanel.add(reduceStockButton);

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE SQL ADD USER
            }
        });
        buttonPanel.add(addProductButton);

        JButton editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE FOR EDITING USER
            }
        });
        buttonPanel.add(editProductButton);

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE FOR DELETING USER
            }
        });
        buttonPanel.add(deleteProductButton);

        add(buttonPanel, BorderLayout.WEST);
    }

    // You can add more methods as needed
}
