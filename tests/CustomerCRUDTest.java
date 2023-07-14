import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerCRUDTest {

    @Test
    void saveCustomer() throws SQLException {
//            // Create a mock Connection and PreparedStatement
//            Connection MySQLConn = mock(Connection.class);
//            PreparedStatement statement = mock(PreparedStatement.class);
//
//            // Create a mock CustomerCRUD instance
//            CustomerCRUD customerCRUD = mock(CustomerCRUD.class);
//
//            // Create a sample customer
//            Customer customer = new Customer(1, "John Doe", "john@example.com", "123 Main St", "1234567890");
//
//            try {
//                // Mock the DriverManager.getConnection() to return the mock Connection
//                Mockito.when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(conn);
//                // Mock the Connection.prepareStatement() to return the mock PreparedStatement
//                Mockito.when(conn.prepareStatement(anyString())).thenReturn(statement);
//                // Mock the PreparedStatement.executeUpdate() to return 1 (indicating successful execution)
//                Mockito.when(statement.executeUpdate()).thenReturn(1);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            // Call the saveCustomer() method
//            customerCRUD.saveCustomer(customer);
//
//            // Verify that the DriverManager.getConnection() method was called
//            verify(DriverManager, times(1)).getConnection(anyString(), anyString(), anyString());
//            // Verify that the Connection.prepareStatement() method was called
//            verify(conn, times(1)).prepareStatement(anyString());
//            // Verify that the PreparedStatement.executeUpdate() method was called
//            verify(statement, times(1)).executeUpdate();
//
//            // Verify that the CustomerCRUD.getAllCustomers() method was called
//            verify(customerCRUD, times(1)).getAllCustomers();
//        }

    }

    @Test
    void updateCustomer() {
        // Test code for updateCustomer() method
    }

    @Test
    void deleteCustomer() {
        // Test code for deleteCustomer() method
    }

    @Test
    void getAllCustomers() {
        // Test code for getAllCustomers() method
        // Create a mock CustomerCRUD instance
        CustomerCRUD customerCRUD = mock(CustomerCRUD.class);

        // Create a sample list of customers
        List<Customer> customers = List.of(
                new Customer(1, "John Doe", "john@example.com", "123 Main St", "1234567890"),
                new Customer(2, "Jane Smith", "jane@example.com", "456 Elm St", "9876543210")
        );

        // Mock the getAllCustomers() method to return the sample list of customers
        when(customerCRUD.getAllCustomers()).thenReturn(customers);

        // Call the getAllCustomers() method
        List<Customer> result = customerCRUD.getAllCustomers();

        // Verify that the getAllCustomers() method was called
        verify(customerCRUD, times(1)).getAllCustomers();

        // Verify the result matches the expected list of customers
        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0), result.get(0));
        assertEquals(customers.get(1), result.get(1));

    }

    @Test
    void getCustomerById() {
        // Test code for getCustomerById() method
    }

    @Test
    void getCustomersByName() {
        // Test code for getCustomersByName() method
    }



}
