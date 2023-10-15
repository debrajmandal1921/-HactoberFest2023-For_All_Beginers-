import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class DatabaseQueryOptimization {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "yourusername";
            String password = "yourpassword";

            // Establish a database connection
            connection = DriverManager.getConnection(url, username, password);

            // Define a prepared statement with parameter binding
            String query = "SELECT * FROM mytable WHERE name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "John");

            // Execute the query
            long startTime = System.nanoTime();
            resultSet = preparedStatement.executeQuery();
            long endTime = System.nanoTime();

            // Process the results (not shown in this simple example)
            while (resultSet.next()) {
                // Process each row
            }

            // Calculate and print query execution time
            long durationInMilliseconds = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
            System.out.println("Query executed in " + durationInMilliseconds + " ms");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
