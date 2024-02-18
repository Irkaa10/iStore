package Client.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryScreen extends JPanel {

    public static InventoryScreen createInventoryScreen() {
        return new InventoryScreen();
    }

    public InventoryScreen() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Add padding

        // Create a JTable with non-modifiable data
        String[] columnNames = {"Name", "ID", "Price", "Quantity"};
        Object[][] rowData = {
                {"Item A", "1", "19.99", "55"},
                {"Item B", "2", "9.99", "10"}
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
