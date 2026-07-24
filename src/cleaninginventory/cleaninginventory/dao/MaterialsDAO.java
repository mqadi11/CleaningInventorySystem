package cleaninginventory.cleaninginventory.dao;


import cleaninginventorysystem.db.DBConnection;
import cleaninginventorysystem.model.Materials;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialsDAO {

    // CREATE
    public void addMaterial(Materials m) throws SQLException {
        String sql = "INSERT INTO materials (name, quantity_in_stock, reorder_level, supplier_id, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getName());
            stmt.setInt(2, m.getQuantity_in_stock());
            stmt.setInt(3, m.getReorder_level());
            if (m.getSupplier_id() != null) {
                stmt.setInt(4, m.getSupplier_id());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setString(5, m.getDescription());
            stmt.executeUpdate();
        }
    }

    // READ ALL
    public List<Materials> getAllMaterials() throws SQLException {
        List<Materials> materials = new ArrayList<>();
        String sql = "SELECT * FROM materials ORDER BY material_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int supplierId = rs.getInt("supplier_id");
                Integer supplierIdObj = rs.wasNull() ? null : supplierId;
                materials.add(new Materials(
                    rs.getInt("material_id"),
                    rs.getString("name"),
                    rs.getInt("quantity_in_stock"),
                    rs.getInt("reorder_level"),
                    supplierIdObj,
                    rs.getString("description")
                ));
            }
        }
        return materials;
    }

    // READ ONE
    public Materials getMaterialById(int id) throws SQLException {
        String sql = "SELECT * FROM materials WHERE material_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int supplierId = rs.getInt("supplier_id");
                    Integer supplierIdObj = rs.wasNull() ? null : supplierId;
                    return new Materials(
                        rs.getInt("material_id"),
                        rs.getString("name"),
                        rs.getInt("quantity_in_stock"),
                        rs.getInt("reorder_level"),
                        supplierIdObj,
                        rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public void updateMaterial(Materials m) throws SQLException {
        String sql = "UPDATE materials SET name = ?, quantity_in_stock = ?, reorder_level = ?, supplier_id = ?, description = ? WHERE material_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getName());
            stmt.setInt(2, m.getQuantity_in_stock());
            stmt.setInt(3, m.getReorder_level());
            if (m.getSupplier_id() != null) {
                stmt.setInt(4, m.getSupplier_id());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setString(5, m.getDescription());
            stmt.setInt(6, m.getMaterial_id());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteMaterial(int id) throws SQLException {
        String sql = "DELETE FROM materials WHERE material_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}