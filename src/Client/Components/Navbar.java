package Client.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Navbar {
    public static JPanel createNavbar(JFrame mainFrame) {
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
                // You need to implement the logic to switch screens
                // For now, let's assume you have a method in the main frame to switch content
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(UserMngmtScreen.createUserMngmtScreen());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        // Add ActionListener to the Inventory button
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to InventoryScreen
                // You need to implement the logic to switch screens
                // For now, let's assume you have a method in the main frame to switch content
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(InventoryScreen.createInventoryScreen());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        // Add ActionListener to the Settings button (You can implement this based on your requirements)

        return navbarPanel;
    }
}
