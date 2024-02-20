package Client.Components.Frames.Users;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserFrame extends JFrame {
    private DefaultTableModel currentTableModel;
    private JTable table;

    public AddUserFrame(DefaultTableModel currentTableModel, JTable table) {
        this.currentTableModel = currentTableModel;
        this.table = table;

        initUI();
    }

    private void initUI() {
        setTitle("Add User");
        setLayout(new GridLayout(4, 2, 10, 10));

        JTextField nameInput = new JTextField(10);
        JTextField emailInput = new JTextField(10);  // Changed variable name to emailInput
        JButton confirmButton = new JButton("Add User");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract values from text fields
                    String name = nameInput.getText();
                    String email = emailInput.getText();  // Corrected variable name to emailInput

                    // Add new row to the table
                    Object[] newRow = {name, email};
                    currentTableModel.addRow(newRow);

                    // Close the frame after adding the user
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddUserFrame.this, "Please enter valid values for name and Email.");
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
