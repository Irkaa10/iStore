package Client.Components.Frames.Whitelist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WhitelistFrame extends JFrame {

    private DefaultTableModel whitelistTableModel;
    private JTable whitelistTable;

    public WhitelistFrame(DefaultTableModel whitelistTableModel) {
        this.whitelistTableModel = whitelistTableModel;

        // Fill the table with sample data
        fillTableWithData();

        initUI();
    }

    private void fillTableWithData() {
        // Sample data for the whitelist table
        Object[][] data = {
                {1, "john@example.com"},
                {2, "alice@example.com"},
                {3, "bob@example.com"},
                // Add more rows as needed
        };

        // Columns for the whitelist table
        String[] columns = {"id", "email"};

        // Set the data and columns to the table model
        whitelistTableModel.setDataVector(data, columns);
    }

    private void initUI() {
        setTitle("Whitelist Table");
        setLayout(new BorderLayout());

        whitelistTable = new JTable(whitelistTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        JScrollPane scrollPane = new JScrollPane(whitelistTable);
        add(scrollPane, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
