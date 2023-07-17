import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Hello_123";
    private static final String DATABASE_NAME = "customer_db";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL + DATABASE_NAME);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER_CLASS_NAME);

        config.setMaximumPoolSize(10);

        dataSource = new HikariDataSource(config);
    }

    public CustomerCRUD(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        createDatabase();
        createTable();
    }

    private void createDatabase() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(sql);
            System.out.println("Database created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
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

    public void saveCustomer(Customer customer) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO customers (id, name, email, address, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE customers SET name = ?, email = ?, address = ?, phone = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM customers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM customers";
            PreparedStatement statement = connection.prepareStatement(query);
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

    public Customer getCustomerById(int id) {
        Customer customer = null;
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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

    public List<Customer> getCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM customers WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");

                Customer customer = new Customer(id, customerName, email, address, phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
