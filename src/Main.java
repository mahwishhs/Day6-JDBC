import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerCRUD customerCRUD = new CustomerCRUD();

        Customer customer1 = new Customer(1, "Mahwish", "mahwish@gmail.com", "123 Lahore", "03014311805");
        Customer customer2 = new Customer(2, "Maheen", "maheen@gmail.com", "345 Karachi", "03014311111");
        Customer customer3 = new Customer(3, "Hamza", "hamza@gmail.com", "123 Islamabad", "03014311225");
        Customer customer4 = new Customer(4, "Khizar", "khizar@gmail.com", "456 Multan", "03014311444");
        Customer customer5 = new Customer(5, "Sara", "sara@gmail.com", "789 Rawalpindi", "03014311666");
        Customer customer6 = new Customer(6, "Ahmed", "ahmed@gmail.com", "567 Faisalabad", "03014311999");

        customerCRUD.saveCustomer(customer1);
        customerCRUD.saveCustomer(customer2);
        customerCRUD.saveCustomer(customer3);
        customerCRUD.saveCustomer(customer4);
        customerCRUD.saveCustomer(customer5);
        customerCRUD.saveCustomer(customer6);
        System.out.println("Customer saved successfully.");

        try (Connection MySQLConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_db", "root", "Hello_123")) {
            // Verify database connection
            if (MySQLConn != null) {
                System.out.println("Connected to the database successfully.");
            } else {
                System.out.println("Failed to connect to the database.");
                return;
            }

            // Select the database
            String selectDBQuery = "USE customer_db";
            Statement selectDBStatement = MySQLConn.createStatement();
            selectDBStatement.executeUpdate(selectDBQuery);
            System.out.println("Selected the 'customer_db' database.");

             Perform operations on the database

            // Update a customer record
            Customer updatedCustomer = new Customer(1, "Mahwish", "mahwish123new@gmail.com", "456 Elm St", "03014311805");
            customerCRUD.updateCustomer(updatedCustomer);
            System.out.println("Customer updated successfully.");
            System.out.println("------------");
            // Delete a customer
            int customerIdToDelete = 2;
            customerCRUD.deleteCustomer(customerIdToDelete);
            System.out.println("Customer deleted successfully.");
            System.out.println("------------");
            // Retrieve all customers
            List<Customer> allCustomers = customerCRUD.getAllCustomers();
            System.out.println("All Customers:");
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }

            System.out.println("------------");
            int findCustomomerid = 4;
            Customer infoCust = customerCRUD.getCustomerById(findCustomomerid);
            if (infoCust != null) {
                System.out.println("Customer Found: " + infoCust);
            } else {
                System.out.println("ERROR 404!! Customer not found.");
            }

            System.out.println("------------");
            String nameToSearch = "Mahwish";
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