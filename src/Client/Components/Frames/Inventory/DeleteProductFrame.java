package Client.Components.Frames.Inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductFrame extends JFrame {

    private DefaultTableModel currentTableModel;
    private JTable table;
    private int selectedRow;

    public DeleteProductFrame(DefaultTableModel currentTableModel, JTable table, int selectedRow) {
        this.currentTableModel = currentTableModel;
        this.table = table;
        this.selectedRow = selectedRow;

        initUI();
    }

    private void initUI() {
        setTitle("Delete Product");
        setLayout(new FlowLayout());

        JLabel confirmationLabel = new JLabel("Are you sure you want to delete this product?");
        JButton confirmButton = new JButton("Yes, Delete");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the selected row from the table's model
                currentTableModel.removeRow(selectedRow);

                // Close the frame after deleting the product
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the frame without deleting the product
                dispose();
            }
        });

        add(confirmationLabel);
        add(confirmButton);
        add(cancelButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
