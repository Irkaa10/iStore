package server;

import Client.Components.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class UserButton implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField pseudoField;
    private JTextField roleField;
    private JFrame frame;

    public UserButton(JTextField emailField, JPasswordField passwordField, JTextField pseudoField, JTextField roleField, JFrame frame) {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.pseudoField = pseudoField;
        this.roleField = roleField;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email, pseudo = null, password;
        Boolean NotFound = false;
        String BDD = "java";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String psd = "root";
        String passwd = "";

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try (Connection conn = DriverManager.getConnection(url, psd, passwd)) {
                conn.setAutoCommit(false);
                System.out.println("Connecté !");
                }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
