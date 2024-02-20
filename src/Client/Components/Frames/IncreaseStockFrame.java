package Client.Components.Frames;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncreaseStockFrame extends JFrame {

    private DefaultTableModel currentTableModel;
    private JTable table;
    private int selectedRow;

    public IncreaseStockFrame(DefaultTableModel currentTableModel, JTable table, int selectedRow) {
        this.currentTableModel = currentTableModel;
        this.table = table;
        this.selectedRow = selectedRow;

        initUI();
    }

    private void initUI() {
        setTitle("Increase Stock");
        setLayout(new FlowLayout());

        JTextField stockInput = new JTextField(10);
        JButton confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int additionalStock = Integer.parseInt(stockInput.getText());
                    int currentStock = Integer.parseInt(currentTableModel.getValueAt(selectedRow, 1).toString());
                    int newStock = currentStock + additionalStock;
                    currentTableModel.setValueAt(newStock, selectedRow, 1);

                    dispose(); // Close the frame after updating
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(IncreaseStockFrame.this, "Please enter a valid positive integer for stock.");
                }
            }
        });

        add(new JLabel("Enter additional stock for " + currentTableModel.getValueAt(selectedRow, 0) + ": "));
        add(stockInput);
        add(confirmButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
