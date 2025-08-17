import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db"; // For MySQL
    // private static final String URL = "jdbc:postgresql://localhost:5432/employee_db"; // For PostgreSQL
    private static final String USER = "root"; // or postgres
    private static final String PASSWORD = "Sakshi@310";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
