package Client.Components;

import Client.Components.Frames.Store.CreateStoreFrame;
import Client.Components.Frames.Store.DeleteStoreFrame;
import Client.Components.Frames.Store.EditStoreFrame;
import Client.Components.Frames.Users.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.DB.DatabaseUtils;

public class StorePanel extends JPanel {

    private DefaultTableModel currentTableModel;

    public StorePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        Object[][] initialStoreData = {
                {1, "Store A"},
                {2, "Store B"},
                {3, "Store C"}
        };
        Object[] storeColumnNames = {"id", "name"};

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
                new CreateStoreFrame(currentTableModel, table);
            }
        });
        buttonPanel.add(createStoreButton);

        JButton editStoreButton = new JButton("Edit Store");
        editStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    new EditStoreFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(StorePanel.this, "Please select a store to edit it.");
                }
            }
        });
        buttonPanel.add(editStoreButton);

        JButton deleteStoreButton = new JButton("Delete Store");
        deleteStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    new DeleteStoreFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(StorePanel.this, "Please select a store to delete it.");
                }
            }
        });
        buttonPanel.add(deleteStoreButton);

        JButton addToStoreButton = new JButton("Add employee to");
        addToStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                } else {
                    JOptionPane.showMessageDialog(StorePanel.this, "Please select a store.");
                }
            }
        });
        buttonPanel.add(addToStoreButton);

        add(buttonPanel, BorderLayout.WEST);
    }
}
