import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hello_123";
    private static final String DATABASE_NAME = "customer_db";

    public CustomerCRUD() {
        createDatabase();
        createTable();
    }

    private void createDatabase() {
        try (Connection MySQLConn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = MySQLConn.createStatement  ()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(sql);
            System.out.println("Database created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (Connection MySQLConn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD);
             Statement statement = MySQLConn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "email VARCHAR(255)," +
                    "address VARCHAR(255)," +
                    "phone VARCHAR(11)" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveCustomer(Customer customer) {
        try (Connection conn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD)) {
            String query = "INSERT INTO customers (id, name, email, address, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.setString(5, customer.getPhone());
            statement.executeUpdate();
            System.out.println("Customer saved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}