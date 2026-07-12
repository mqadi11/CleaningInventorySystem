package cleaninginventory.cleaninginventory.dao;

import cleaninginventory.cleaninginventory.DatabaseConnection;
import cleaninginventory.cleaninginventory.models.InventoryItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryItemDAO {

    // 1. READ ALL ITEMS FROM DATABASE
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> itemsList = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items ORDER BY item_name ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getInt("quantity_available"),
                    rs.getInt("minimum_stock_level"),
                    rs.getString("unit_of_measure")
                );
                itemsList.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching stock items: " + e.getMessage());
            e.printStackTrace();
        }
        return itemsList;
    }

    // 2. ADD A NEW CLEANING ITEM
    public boolean addItem(InventoryItem item) {
        String sql = "INSERT INTO inventory_items (item_name, category, quantity_available, minimum_stock_level, unit_of_measure) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getItemName());
            stmt.setString(2, item.getCategory());
            stmt.setInt(3, item.getQuantityAvailable());
            stmt.setInt(4, item.getMinimumStockLevel());
            stmt.setString(5, item.getUnitOfMeasure());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error adding inventory item: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 3. UPDATE STOCK QUANTITY (When issuing or restocking items)
    public boolean updateStockQuantity(int itemId, int newQuantity) {
        String sql = "UPDATE inventory_items SET quantity_available = ? WHERE item_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, itemId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating quantity: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> list = new java.util.ArrayList<>();
        String sql = "SELECT * FROM inventory_items"; // Make sure this matches your DB table name exactly!
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setCategory(rs.getString("category"));
                item.setQuantityAvailable(rs.getInt("quantity_available"));
                item.setMinimumStockLevel(rs.getInt("minimum_stock_level"));
                item.setUnitOfMeasure(rs.getString("unit_of_measure"));
                
                list.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching inventory items: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}