//package server;
//
//import Client.Components.*;
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Base64;
//
//public class LoginListener implements ActionListener {
//    private JTextField emailField;
//    private JPasswordField passwordField;
//    private JFrame frame;
//
//    public LoginListener(JTextField emailField, JPasswordField passwordField, JFrame frame) {
//        this.emailField = emailField;
//        this.passwordField = passwordField;
//        this.frame = frame;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String email, pseudo = null, password, HashpassInsideDB = null, role_session = null;
//        Boolean NotFound = false;
//        String BDD = "java";
//        String url = "jdbc:mysql://localhost:3306/" + BDD;
//        String psd = "root";
//        String passwd = "";
//
//        try {
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//            } catch (ClassNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
//
//            try (Connection conn = DriverManager.getConnection(url, psd, passwd)) {
//                conn.setAutoCommit(false);
//                System.out.println("Connecté !");
//
//                if ("".equals(emailField.getText()) || "".equals(passwordField.getText())) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Email or Password Empty !", "Error", JOptionPane.ERROR_MESSAGE);
//                } else {
//                    email = emailField.getText();
//                    password = passwordField.getText();
//
//                    String verify_user = "SELECT * FROM user WHERE email = ?";
//                    try (PreparedStatement statement = conn.prepareStatement(verify_user)) {
//                        statement.setString(1, email);
//                        try (ResultSet resultSet = statement.executeQuery()) {
//                            byte[] salt = new byte[0];
//                            while (resultSet.next()) {
//                                HashpassInsideDB = resultSet.getString("password");
//                                pseudo = resultSet.getString("pseudo");
//                                role_session = resultSet.getString("role");
//                                salt = Base64.getDecoder().decode(resultSet.getBytes("salt"));
//                                NotFound = true;
//                            }
//                            //verify if the hashpassword is equal to the password
//                            if (NotFound == true && verifyPassword(password, HashpassInsideDB, salt)) {
//                                JOptionPane.showMessageDialog(new JFrame(), "Success ! Welcome Back " + pseudo);
//                                //open a new Frame
//                                frame.dispose();
//                                DashboardFrame.role_set(role_session);
//                                DashboardFrame.username_set(pseudo);
//                                DashboardFrame dashboardFrame = new DashboardFrame();
//                                dashboardFrame.setVisible(true);
//                            } else {
//                                JOptionPane.showMessageDialog(new JFrame(), "Error : Password Incorrect !", "Error", JOptionPane.ERROR_MESSAGE);
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (SQLException exception) {
//            throw new RuntimeException(exception);
//        }
//    }
//    // Méthode pour vérifier le mot de passe
//    private boolean verifyPassword (String enteredPassword, String storedHash,byte[] salt){
//        // Hasher le mot de passe entré avec le sel
//        String enteredPasswordHash = PasswordUtils.hashPassword(enteredPassword, salt);
//
//        // Comparer le hash stocké avec le hash du mot de passe entré
//        return enteredPasswordHash.equals(storedHash);
//    }
//}
