package Client.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserMngmtScreen extends JPanel {

    public static UserMngmtScreen createUserMngmtScreen() {
        return new UserMngmtScreen();
    }

    private UserMngmtScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Add padding

        // Create a JTable with non-modifiable data
        String[] columnNames = {"Email", "ID", "Role"};
        Object[][] rowData = {
                {"user1@example.com", "1", "Admin"},
                {"user2@example.com", "2", "User"}
                // Add more rows as needed
        };

        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        JTable userTable = new JTable(tableModel);
        userTable.setFillsViewportHeight(true);

        // Add the JTable to a JScrollPane for scrollability
        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
