package Client.Components.Frames.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditStoreFrame extends JFrame {
    private DefaultTableModel storeTableModel;
    private JTable storeTable;
    private int rowIndex;

    public EditStoreFrame(DefaultTableModel storeTableModel, JTable storeTable, int rowIndex) {
        this.storeTableModel = storeTableModel;
        this.storeTable = storeTable;
        this.rowIndex = rowIndex;

        initUI();
    }

    private void initUI() {
        setTitle("Edit Store");
        setLayout(new GridLayout(3, 2, 10, 10));

        JTextField storeNameInput = new JTextField(10);
        JButton confirmButton = new JButton("Update Store");

        // Populate the text field with the existing value from the selected row
        storeNameInput.setText(storeTableModel.getValueAt(rowIndex, 1).toString());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract the updated value from the text field
                    String newStoreName = storeNameInput.getText();

                    // Update the value in the table
                    storeTableModel.setValueAt(newStoreName, rowIndex, 1);

                    // Close the frame after updating the store
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditStoreFrame.this, "Please enter a valid value for the store name.");
                }
            }
        });

        add(new JLabel("Store Name: "));
        add(storeNameInput);
        add(new JLabel(""));
        add(confirmButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
