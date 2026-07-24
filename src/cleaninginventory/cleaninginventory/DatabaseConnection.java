package cleaninginventory.cleaninginventory;

import cleaninginventory.cleaninginventory.dao.InventoryItemDAO;
import cleaninginventory.cleaninginventory.models.InventoryItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseConnection { 

    public static Connection getConnection() throws SQLException {
    return cleaninginventorysystem.db.DBConnection.getConnection();
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