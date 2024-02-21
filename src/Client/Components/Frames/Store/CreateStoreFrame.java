package Client.Components.Frames.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStoreFrame extends JFrame {
    private DefaultTableModel storeTableModel;
    private JTable storeTable;
    private JTextField storeNameField;

    public CreateStoreFrame(DefaultTableModel storeTableModel, JTable storeTable) {
        this.storeTableModel = storeTableModel;
        this.storeTable = storeTable;

        initUI();
    }

    private void initUI() {
        setTitle("Create Store");
        setLayout(new GridLayout(3, 2, 10, 10));

        storeNameField = new JTextField(10);
        JButton createButton = new JButton("Create Store");
        JButton cancelButton = new JButton("Cancel");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createStore();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(new JLabel("Store Name: "));
        add(storeNameField);
        add(new JLabel(""));
        add(createButton);
        add(new JLabel(""));
        add(cancelButton);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void createStore() {
        String storeName = storeNameField.getText().trim();
        if (!storeName.isEmpty()) {
            // Add the new store to the table model
            Object[] newStore = {storeTableModel.getRowCount() + 1, storeName};
            storeTableModel.addRow(newStore);
            // Clear the text field
            storeNameField.setText("");
            // Close the frame
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a store name.");
        }
    }
}
