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

    public void updateCustomer(Customer customer) {
        try (Connection conn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD)) {
            String query = "UPDATE customers SET name = ?, email = ?, address = ?, phone = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.setInt(5, customer.getId());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCustomer(int id) {
        try (Connection conn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD)) {
            String query = "DELETE FROM customers WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM customers";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                Customer customer = new Customer(id, name, email, address, phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    //GETTING CUSTOMER BY ID
    public Customer getCustomerById(int id) {
        Customer customer = null;
        try (Connection conn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                customer = new Customer(customerId, name, email, address, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

//    public List<Customer> getCustomersByName(String name) {
//        List<Customer> customers = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(URL + DATABASE_NAME, USERNAME, PASSWORD)) {
//            String query = "SELECT * FROM customers WHERE name LIKE ?";
//            PreparedStatement statement = conn.prepareStatement(query);
//            statement.setString(1, "%" + name + "%");
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String customerName = resultSet.getString("name");
//                String email = resultSet.getString("email");
//                String address = resultSet.getString("address");
//                String phone = resultSet.getString("phone");
//
//                Customer customer = new Customer(id, customerName, email, address, phone);
//                customers.add(customer);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customers;
//    }

}