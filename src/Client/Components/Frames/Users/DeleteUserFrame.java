package Client.Components.Frames.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserFrame extends JFrame {
    private DefaultTableModel currentTableModel;
    private JTable table;
    private int rowIndex;

    public DeleteUserFrame(DefaultTableModel currentTableModel, JTable table, int rowIndex) {
        this.currentTableModel = currentTableModel;
        this.table = table;
        this.rowIndex = rowIndex;

        initUI();
    }

    private void initUI() {
        setTitle("Delete User");
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel confirmationLabel = new JLabel("Are you sure you want to delete this user?");
        JButton deleteButton = new JButton("Delete User");
        JButton cancelButton = new JButton("Cancel");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete the user from the table
                currentTableModel.removeRow(rowIndex);

                // Close the frame after deleting the user
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the frame without deleting the user
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
