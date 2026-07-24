package cleaninginventorysystem.dao;

import cleaninginventorysystem.db.DbPlaceholder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cleaninginventorysystem.model.InventoryReport;
import cleaninginventorysystem.model.LowStockReport;
import cleaninginventorysystem.model.IssuanceHistory;
import cleaninginventorysystem.model.MaterialUsageReport;
import java.sql.PreparedStatement;

public class DaoPlaceholder {

    public List<InventoryReport> getInventoryReport() throws ClassNotFoundException {

        List<InventoryReport> reportResults = new ArrayList<>();

        try {

            DbPlaceholder db = new DbPlaceholder();
            Connection conn = db.getCon();

            String query
                    = "SELECT m.name AS material_name,\n"
                    + "       m.category,\n"
                    + "       i.quantity_available AS quantity,\n"
                    + "       i.minimum_stock_level,\n"
                    + "       s.name AS supplier_name\n"
                    + "FROM materials m\n"
                    + "INNER JOIN inventory_items i ON m.material_id = i.material_id\n"
                    + "INNER JOIN suppliers s ON m.supplier_id = s.supplier_id\n"
                    + "ORDER BY m.name ASC;";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String materialName = rs.getString("material_name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                int minimumStockLevel = rs.getInt("minimum_stock_level");
                String supplierName = rs.getString("supplier_name");

                reportResults.add(
                        new InventoryReport(
                                materialName,
                                category,
                                quantity,
                                minimumStockLevel,
                                supplierName));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reportResults;
    }
    
    //Low-stock Report
    public List<LowStockReport> getLowStockInventoryReport() throws ClassNotFoundException {

        List<LowStockReport> reportResults = new ArrayList<>();

        try {

            DbPlaceholder db = new DbPlaceholder();
            Connection conn = db.getCon();

            String query
                    = "SELECT m.name AS material_name,\n"
                    + "       m.category,\n"
                    + "       i.quantity_available AS quantity,\n"
                    + "       i.minimum_stock_level,\n"
                    + "       s.name AS supplier_name\n"
                    + "FROM materials m\n"
                    + "INNER JOIN inventory_items i ON m.material_id = i.material_id\n"
                    + "INNER JOIN suppliers s ON m.supplier_id = s.supplier_id\n"
                    + "ORDER BY m.name ASC;";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String materialName = rs.getString("material_name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                int minimumStockLevel = rs.getInt("minimum_stock_level");
                String supplierName = rs.getString("supplier_name");

                reportResults.add(
                        new LowStockReport(
                                materialName,
                                category,
                                quantity,
                                minimumStockLevel,
                                supplierName));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reportResults;
    }
    
    //Issuance History Report
    public List<IssuanceHistory> getIssuance() throws ClassNotFoundException {

        List<IssuanceHistory> reportResults = new ArrayList<>();

        try {

            DbPlaceholder db = new DbPlaceholder();
            Connection conn = db.getCon();

            String query =
                    "SELECT m.name AS material_name, " +
                    "u.username AS issued_by_username, " +
                    "ii.date_issued, " +
                    "ii.quantity_issued, " +
                    "CONCAT(c.first_name, ' ', c.last_name) AS cleaner_name " +
                    "FROM inventory_issuances ii " +
                    "INNER JOIN inventory_items i ON ii.item_id = i.item_id " +
                    "INNER JOIN materials m ON i.material_id = m.material_id " +
                    "INNER JOIN cleaners c ON ii.cleaner_id = c.cleaner_id " +
                    "INNER JOIN users u ON ii.issued_by = u.user_id " +
                    "ORDER BY m.name ASC";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String materialName = rs.getString("material_name");
                String issuedByUsername = rs.getString("issued_by_username");
                String dateIssued = rs.getString("date_issued");
                int quantityIssued = rs.getInt("quantity_issued");
                String cleanerName = rs.getString("cleaner_name");

                reportResults.add(
                        new IssuanceHistory(
                                materialName,
                                issuedByUsername,
                                dateIssued,
                                quantityIssued,
                                cleanerName
                        )
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reportResults;
    }
    //MaterialUsageReport
    public List<MaterialUsageReport> getReport() throws ClassNotFoundException {

        List<MaterialUsageReport> reportResults = new ArrayList<>();

        try {

            DbPlaceholder db = new DbPlaceholder();
            Connection conn = db.getCon();

            String query =
                    "SELECT m.name AS material_name, " +
                    "SUM(ii.quantity_issued) AS total_quantity_issued " +
                    "FROM inventory_issuances ii " +
                    "INNER JOIN inventory_items i ON ii.item_id = i.item_id " +
                    "INNER JOIN materials m ON i.material_id = m.material_id " +
                    "GROUP BY m.name " +
                    "ORDER BY total_quantity_issued DESC";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String materialName = rs.getString("material_name");
                int totalQuantityIssued = rs.getInt("total_quantity_issued");

                reportResults.add(
                        new MaterialUsageReport(
                                materialName,
                                totalQuantityIssued
                        )
                );
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reportResults;
    }
}
