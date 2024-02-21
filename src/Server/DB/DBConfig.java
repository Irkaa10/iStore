package Server.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code DBConfig} class handles the database connection using JDBC.
 * It provides methods to obtain and close a database connection.
 */
public class DBConfig {

    // JDBC driver class name for MySQL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // JDBC URL, username, and password for the MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:8889/iStore_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Connection object to manage the database connection
    private static Connection connection = null;

    // Static block to load the JDBC driver when the class is loaded
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your project.");
        }
    }

    // Private constructor to prevent instantiation
    private DBConfig() {
    }

    /**
     * Gets a database connection. If a connection does not exist, it creates a new one.
     *
     * @return The database connection.
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Establishing the connection
                connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                System.err.println("Connection failed. Error: " + e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
