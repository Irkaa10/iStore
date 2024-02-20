package Client.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public DashboardFrame() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JButton inventoryButton = new JButton("Inventory");
        JButton storeButton = new JButton("Store");
        JButton usersButton = new JButton("Users");
        JButton settingsButton = new JButton("Settings");

        menuBar.add(inventoryButton);
        menuBar.add(storeButton);
        menuBar.add(usersButton);
        menuBar.add(settingsButton);
        setJMenuBar(menuBar);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(new InventoryPanel(), "Inventory");
        cardPanel.add(new UsersPanel(), "Users");
        cardPanel.add(new SettingsPanel(), "Settings");

        add(cardPanel);

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Inventory");
            }
        });

        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Users");
            }
        });

        cardLayout.show(cardPanel, "Inventory");

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Settings");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
}
