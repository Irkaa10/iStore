package Client.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryPanel extends JPanel {

    private DefaultTableModel currentTableModel;

    public InventoryPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Inventory Panel");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to add product
                JOptionPane.showMessageDialog(null, "Product added!");
            }
        });
        add(addProductButton, BorderLayout.NORTH);
    }

    // You can add more methods as needed
}
