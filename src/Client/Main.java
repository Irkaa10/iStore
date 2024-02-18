package Client;

import Client.Components.InventoryScreen;
import Client.Components.UserMngmtScreen;
import Client.Components.Navbar;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create a panel for the main content with BorderLayout
        JPanel mainContentPanel = new JPanel(new BorderLayout());

        // Create the Navbar and add it to the main frame
        JPanel navbar = Navbar.createNavbar(this, mainContentPanel);

        // Add the navbar to the main frame
        add(navbar, BorderLayout.PAGE_START);

        // Add the main content panel to the main frame
        add(mainContentPanel, BorderLayout.CENTER);

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
