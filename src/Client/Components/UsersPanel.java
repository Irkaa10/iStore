package Client.Components;

import Client.Components.Frames.Users.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanel extends JPanel {

    private DefaultTableModel currentTableModel;

    public UsersPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        Object[][] initialUserData = {
                {"John Doe", "john.doe@example.com"},
                {"Jane Smith", "jane.smith@example.com"}
        };
        Object[] userColumnNames = {"Name", "Email"};

        currentTableModel = new DefaultTableModel(initialUserData, userColumnNames);

        JTable table = new JTable(currentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserFrame(currentTableModel, table);
            }
        });
        buttonPanel.add(addUserButton);

        JButton editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    new EditUserFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(UsersPanel.this, "Please select an item to edit it.");
                }
            }
        });
        buttonPanel.add(editUserButton);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    new DeleteUserFrame(currentTableModel, table, selectedRow);
                } else {
                    JOptionPane.showMessageDialog(UsersPanel.this, "Please select a user to delete it.");
                }
            }
        });
        buttonPanel.add(deleteUserButton);

        add(buttonPanel, BorderLayout.WEST);
    }

    // You can add more methods as needed
}
