package Client.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanel extends JPanel {

    private DefaultTableModel currentTableModel;

    public UsersPanel() {
        setLayout(new BorderLayout());

        Object[][] initialUserData = {
                {"John Doe", "john.doe@example.com"},
                {"Jane Smith", "jane.smith@example.com"}
        };
        Object[] userColumnNames = {"Name", "Email"};

        currentTableModel = new DefaultTableModel(initialUserData, userColumnNames);

        JLabel label = new JLabel("Users Panel");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        JTable table = new JTable(currentTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] newUser = {"New User", "new.user@example.com"};
                currentTableModel.addRow(newUser);

                JOptionPane.showMessageDialog(null, "User added!");
            }
        });
        add(addUserButton, BorderLayout.NORTH);
    }

    // You can add more methods as needed
}
