package Client.Components.Frames.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteStoreFrame extends JFrame {
    private DefaultTableModel storeTableModel;
    private JTable storeTable;
    private int rowIndex;

    public DeleteStoreFrame(DefaultTableModel storeTableModel, JTable storeTable, int rowIndex) {
        this.storeTableModel = storeTableModel;
        this.storeTable = storeTable;
        this.rowIndex = rowIndex;

        initUI();
    }

    private void initUI() {
        setTitle("Delete Store");
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel confirmationLabel = new JLabel("Are you sure you want to delete this store?");
        JButton deleteButton = new JButton("Delete Store");
        JButton cancelButton = new JButton("Cancel");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete the store from the table
                storeTableModel.removeRow(rowIndex);

                // Close the frame after deleting the store
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the frame without deleting the store
                dispose();
            }
        });

        add(confirmationLabel);
        add(deleteButton);
        add(cancelButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
