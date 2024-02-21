package Server.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * The DatabaseUtils class provides utility methods for interacting with a database.
 * It includes methods for fetching data from a specified table.
 */
public class DatabaseUtils {

    /**
     * Fetches data from the specified database table.
     *
     * @param tableName The name of the table from which to fetch data.
     * @return An array of arrays containing the fetched data. Each row represents a record
     *         with columns corresponding to ProductName, Stock, and Price.
     *         Returns an empty array if an error occurs during the fetch operation.
     */
    public static Object[][] fetchData(String tableName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get a database connection
            connection = DBConfig.getConnection();

            // SQL query to select all data from the specified table
            String query = "SELECT * FROM " + tableName;
            preparedStatement = connection.prepareStatement(query);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set and populate the data array
            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getString("ProductName"));
                row.add(resultSet.getInt("Stock"));
                row.add(resultSet.getDouble("Price"));
                data.add(row);
            }

            // Convert Vector<Vector<Object>> to Object[][]
            Object[][] dataArray = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                dataArray[i] = data.get(i).toArray();
            }

            return dataArray;

        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[0][0]; // Return empty array in case of an error
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
