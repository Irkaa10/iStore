package Client.Components;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventoryScreen {
    public InventoryScreen() {
        // Create a JFrame
        JFrame frame = new JFrame("Inventory Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a JPanel with BorderLayout for the main structure
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

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
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the mainPanel to the frame
        frame.add(mainPanel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
