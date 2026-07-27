package cleaninginventory.cleaninginventory;

import cleaninginventory.cleaninginventory.dao.InventoryItemDAO;
import cleaninginventory.cleaninginventory.models.InventoryItem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/CleaningInventory";
    private static final String USER = "postgres";
    private static final String PASSWORD = "YourNewPassword123"; 

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        // Test connection
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Successfully connected to the Cleaning System Database!");
                System.out.println("--------------------------------------------------");
                
                // Live Test: Fetch data using our brand new DAO layer!
                System.out.println("Fetching current cleaning stock items from database...");
                InventoryItemDAO itemDAO = new InventoryItemDAO();
                List<InventoryItem> items = itemDAO.getAllItems();
                
                for (InventoryItem item : items) {
                    System.out.println("-> " + item.toString());
                    if (item.isLowStock()) {
                        System.out.println("   ⚠️ ALERT: Low stock on this item!");
                    }
                }
                System.out.println("--------------------------------------------------");
            }
        } catch (SQLException e) {
            System.err.println("Database test failed!");
            e.printStackTrace();
        }
    }
}