package Client.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SettingsPanel extends JPanel {


    public SettingsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton addUserButton = new JButton("Show Whitelist");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE SQL ADD USER
            }
        });
        buttonPanel.add(addUserButton);

        JButton editUserButton = new JButton("Add to Whitelist");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE FOR EDITING USER
            }
        });
        buttonPanel.add(editUserButton);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE FOR DELETING USER
            }
        });
        buttonPanel.add(deleteUserButton);

        JButton createStoreButton = new JButton("Create store");
        buttonPanel.add(createStoreButton);

        add(buttonPanel, BorderLayout.WEST);
    }

}
