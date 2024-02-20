package Client.Components.Frames.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserFrame extends JFrame {
    private DefaultTableModel currentTableModel;
    private JTable table;
    private int rowIndex;

    public EditUserFrame(DefaultTableModel currentTableModel, JTable table, int rowIndex) {
        this.currentTableModel = currentTableModel;
        this.table = table;
        this.rowIndex = rowIndex;

        initUI();
    }

    private void initUI() {
        setTitle("Edit User");
        setLayout(new GridLayout(4, 2, 10, 10));

        JTextField nameInput = new JTextField(10);
        JTextField emailInput = new JTextField(10);
        JButton confirmButton = new JButton("Update User");

        // Populate text fields with existing values from the selected row
        nameInput.setText(currentTableModel.getValueAt(rowIndex, 0).toString());
        emailInput.setText(currentTableModel.getValueAt(rowIndex, 1).toString());

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract updated values from text fields
                    String newName = nameInput.getText();
                    String newEmail = emailInput.getText();

                    // Update values in the table
                    currentTableModel.setValueAt(newName, rowIndex, 0);
                    currentTableModel.setValueAt(newEmail, rowIndex, 1);

                    // Close the frame after updating the user
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditUserFrame.this, "Please enter valid values for name and Email.");
                }
            }
        });

        add(new JLabel("Name: "));
        add(nameInput);
        add(new JLabel("Email: "));
        add(emailInput);
        add(new JLabel(""));
        add(confirmButton);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
