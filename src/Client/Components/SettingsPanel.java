package Client.Components;

import Client.Components.Frames.Whitelist.WhitelistFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SettingsPanel extends JPanel {

    private DefaultTableModel whitelistTableModel;

    public SettingsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margins

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton showWhitelistButton = new JButton("Show Whitelist");
        showWhitelistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WhitelistFrame(whitelistTableModel);
            }
        });
        buttonPanel.add(showWhitelistButton);

        JButton editUserButton = new JButton("Add to Whitelist");
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: CODE FOR EDITING USER
            }
        });
        buttonPanel.add(editUserButton);

        add(buttonPanel, BorderLayout.WEST);
    }
}
