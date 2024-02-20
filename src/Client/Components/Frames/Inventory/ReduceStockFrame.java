package Client.Components.Frames.Inventory;

import Server.Controllers.ReduceStockController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReduceStockFrame extends JFrame {

    private DefaultTableModel currentTableModel;
    private JTable table;
    private int selectedRow;

    public ReduceStockFrame(DefaultTableModel currentTableModel, JTable table, int selectedRow) {
        this.currentTableModel = currentTableModel;
        this.table = table;
        this.selectedRow = selectedRow;

        initUI();
    }

    private void initUI() {
        setTitle("Reduce Stock");
        setLayout(new FlowLayout());

        JTextField stockInput = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");

        ReduceStockController controller = new ReduceStockController(this, stockInput);

        // Triggers the controller
        confirmButton.addActionListener(controller);

        add(new JLabel("Enter removed stock for " + currentTableModel.getValueAt(selectedRow, 0) + ": "));
        add(stockInput);
        add(confirmButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public DefaultTableModel getCurrentTableModel() {
        return currentTableModel;
    }

    public JTable getTable() {
        return table;
    }

    public int getSelectedRow() {
        return selectedRow;
    }
}
