import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/customer_db");
        config.setUsername("root");
        config.setPassword("Hello_123");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        HikariDataSource dataSource = new HikariDataSource(config);

        CustomerCRUD customerCRUD = new CustomerCRUD(dataSource);

        Customer customer1 = new Customer(1, "Mahwish", "mahwish@gmail.com", "123 Lahore", "03014311805");
        Customer customer2 = new Customer(2, "Maheen", "maheen@gmail.com", "345 Karachi", "03014311111");
        Customer customer3 = new Customer(3, "Hamza", "hamza@gmail.com", "123 Islamabad", "03014311225");
        Customer customer4 = new Customer(4, "Khizar", "khizar@gmail.com", "456 Multan", "03014311444");
        Customer customer5= new Customer(5, "Sara", "sara@gmail.com", "789 Rawalpindi", "03014311666");
        Customer customer6 = new Customer(6, "Ahmed", "ahmed@gmail.com", "567 Faisalabad", "03014311999");

        Customer customer7 = new Customer(7, "Ahad", "ahad@gmail.com", "234 Peshawar", "03014311333");
        Customer customer8 = new Customer(8, "Fatima", "fatima@gmail.com", "678 Quetta", "03014311555");
        Customer customer9 = new Customer(9, "Usman", "usman@gmail.com", "901 Hyderabad", "03014311777");
        Customer customer10 = new Customer(10, "Alisbha", "Alisbha@gmail.com", "123 Sukkur", "03014311911");
        Customer customer11 = new Customer(11, "Sami", "sami@gmail.com", "567 Gujranwala", "03014311222");

        customerCRUD.saveCustomer(customer1);
        customerCRUD.saveCustomer(customer2);
        customerCRUD.saveCustomer(customer3);
        customerCRUD.saveCustomer(customer4);
        customerCRUD.saveCustomer(customer5);
        customerCRUD.saveCustomer(customer6);
        customerCRUD.saveCustomer(customer7);
        customerCRUD.saveCustomer(customer8);
        customerCRUD.saveCustomer(customer9);
        customerCRUD.saveCustomer(customer10);
        customerCRUD.saveCustomer(customer11);

        System.out.println("Customer saved successfully.");

        try (Connection connection = dataSource.getConnection()) {
            // Verify database connection
            if (connection != null) {
                System.out.println("Connected to the database successfully.");
            } else {
                System.out.println("Failed to connect to the database.");
                return;
            }

            // Select the database
            String selectDBQuery = "USE customer_db";
            Statement selectDBStatement = connection.createStatement();
            selectDBStatement.executeUpdate(selectDBQuery);
            System.out.println("Selected the 'customer_db' database.");

            // Perform operations on the database

            // Update a customer record
            Customer updatedCustomer = new Customer(3, "Ahmed", "hehejoke@gmail.com", "456 Elm St", "03014311805");
            customerCRUD.updateCustomer(updatedCustomer);
            System.out.println("Customer updated successfully.");
            System.out.println("------------");


            // Delete a customer
            int customerIdToDelete = 14;
            customerCRUD.deleteCustomer(customerIdToDelete);
            System.out.println("------------");


            // Retrieve all customers
            List<Customer> allCustomers = customerCRUD.getAllCustomers();
            System.out.println("All Customers:");
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }

            System.out.println("------------");
            int findCustomerId = 4;
            Customer infoCust = customerCRUD.getCustomerById(findCustomerId);
            if (infoCust != null) {
                System.out.println("Customer Found: " + infoCust);
            } else {
                System.out.println("ERROR 404!! Customer not found.");
            }

            System.out.println("------------");
            String nameToSearch = "Sami";
            List<Customer> customersByName = customerCRUD.getCustomersByName(nameToSearch);
            System.out.println("Customers by Name:");
            for (Customer customer : customersByName) {
                System.out.println(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
