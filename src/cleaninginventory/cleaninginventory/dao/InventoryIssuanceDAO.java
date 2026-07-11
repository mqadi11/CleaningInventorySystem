/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author roebssie
 */
package cleaninginventory.cleaninginventory.dao;

import cleaninginventory.cleaninginventory.DatabaseConnection;
import cleaninginventory.cleaninginventory.models.InventoryIssuance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryIssuanceDAO {

    // RECORD A NEW ITEM ISSUANCE TRANSACTION
    public boolean issueItem(InventoryIssuance issuance) {
        String insertSql = "INSERT INTO inventory_issuances (item_id, issued_to, campus_location, quantity_issued, remarks) VALUES (?, ?, ?, ?, ?)";
        String updateStockSql = "UPDATE inventory_items SET quantity_available = quantity_available - ? WHERE item_id = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction block

            // 1. Insert the issuance tracking record
            try (PreparedStatement stmtInsert = conn.prepareStatement(insertSql)) {
                stmtInsert.setInt(1, issuance.getItemId());
                stmtInsert.setString(2, issuance.getIssuedTo());
                stmtInsert.setString(3, issuance.getCampusLocation());
                stmtInsert.setInt(4, issuance.getQuantityIssued());
                stmtInsert.setString(5, issuance.getRemarks());
                stmtInsert.executeUpdate();
            }

            // 2. Deduct the issued quantity from the available stock balances
            try (PreparedStatement stmtUpdate = conn.prepareStatement(updateStockSql)) {
                stmtUpdate.setInt(1, issuance.getQuantityIssued());
                stmtUpdate.setInt(2, issuance.getItemId());
                stmtUpdate.executeUpdate();
            }

            conn.commit(); // Save both operations together safely
            return true;

        } catch (SQLException e) {
            System.err.println("Transaction failed, rolling back changes: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
