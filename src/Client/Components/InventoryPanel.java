package Client.Components;

import Client.Components.Frames.Inventory.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.DB.DatabaseUtils;

public class InventoryPanel extends JPanel {

    private DefaultTableModel currentTableModel;

    public InventoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Object[][] initialInventoryData = DatabaseUtils.fetchData("Inventory");
        Object[] inventoryColumnNames = {"Name", "Stock", "Price"};

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
                new AddProductFrame(currentTableModel, table);
            }
        });
        buttonPanel.add(addProductButton);

        JButton editProductButton = new JButton("Edit Product");
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    new EditProductFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(InventoryPanel.this, "Please select an item to edit it.");
                }
            }
        });
        buttonPanel.add(editProductButton);

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    new DeleteProductFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(InventoryPanel.this, "Please select an item to delete it.");
                }
            }
        });
        buttonPanel.add(deleteProductButton);

        add(buttonPanel, BorderLayout.WEST);

    }
}
