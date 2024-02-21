package Client.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DashboardFrame extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private static String role_session;
    private static String ID_session;
    private static String email_session;
    private static String username;
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
        cardPanel.add(new StorePanel(), "Store");
        cardPanel.add(new UsersPanel(), "Users");
        cardPanel.add(new SettingsPanel(), "Settings");

        add(cardPanel);

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Inventory");
            }
        });

        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cardLayout.show(cardPanel, "Store");}});

        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Users");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(Objects.equals("Admin", role_session))) {
                    JOptionPane.showMessageDialog(DashboardFrame.this, "You are not an Admin !", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    cardLayout.show(cardPanel, "Settings");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
    public static void role_set(String name){
        DashboardFrame.role_session = name;
    }
    public static void email_set(String name){
        DashboardFrame.email_session = name;
    }
    public static void username_set(String name){
        DashboardFrame.username = name;
    }
    public static void ID_set(String name){
        DashboardFrame.ID_session = name;
    }
    public static String Get_role(){
        return role_session;
    }
    public static String Get_ID(){
        return ID_session;
    }
    public static String Get_username(){
        return username;
    }
    public static String Get_email(){
        return email_session;
    }
}
