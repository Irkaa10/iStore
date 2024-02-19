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
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JButton inventoryButton = new JButton("Inventory");
        JButton usersButton = new JButton("Users");
        JButton storeButton = new JButton("Store");
        JButton settingsButton = new JButton("Settings");

        menuBar.add(inventoryButton);
        menuBar.add(usersButton);
        menuBar.add(storeButton);
        menuBar.add(settingsButton);
        setJMenuBar(menuBar);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(new InventoryPanel(), "Inventory");
        cardPanel.add(new UsersPanel(), "Users");

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
    }
}
