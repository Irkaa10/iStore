
package Client.Components.Frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int removedStock = Integer.parseInt(stockInput.getText());
                    int currentStock = Integer.parseInt(currentTableModel.getValueAt(selectedRow, 1).toString());
                    int newStock = currentStock - removedStock;
                    currentTableModel.setValueAt(newStock, selectedRow, 1);

                    dispose(); // Close the frame after updating
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ReduceStockFrame.this, "Please enter a valid positive integer for stock.");
                }
            }
        });

        add(new JLabel("Enter removed stock for " + currentTableModel.getValueAt(selectedRow, 0) + ": "));
        add(stockInput);
        add(confirmButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
