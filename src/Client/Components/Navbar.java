package Client.Components;

import Client.Components.UsersPanel;
import Client.Components.InventoryPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar {
    private static JPanel contentPanel; // Reference to the main content panel

    public static JPanel createNavbar(JFrame mainFrame, JPanel mainContentPanel) {
        contentPanel = mainContentPanel; // Set the reference to the main content panel

        JPanel navbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Create buttons for Home, Users, and Inventory
        JButton usersButton = new JButton("Users");
        JButton inventoryButton = new JButton("Inventory");
        JButton settingsButton = new JButton("Settings");

        // Add buttons to the navbarPanel
        navbarPanel.add(usersButton);
        navbarPanel.add(inventoryButton);
        navbarPanel.add(settingsButton);

        // Add ActionListener to the Users button
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to UserMngmtScreen
                contentPanel.removeAll();
                contentPanel.add(new UsersPanel(), BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        // Add ActionListener to the Inventory button
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to InventoryScreen
                contentPanel.removeAll();
                contentPanel.add(new InventoryPanel(), BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        // Add ActionListener to the Settings button (You can implement this based on your requirements)

        return navbarPanel;
    }
}
