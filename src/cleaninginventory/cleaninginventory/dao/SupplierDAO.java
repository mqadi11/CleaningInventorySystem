
package cleaninginventory.cleaninginventory.dao;

import cleaninginventorysystem.db.DBConnection;
import cleaninginventorysystem.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    // CREATE
    public void addSupplier(Supplier s) throws SQLException {
        String sql = "INSERT INTO suppliers (name, email, phone, contact_person) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getPhone());
            stmt.setString(4, s.getContactPerson());
            stmt.executeUpdate();
        }
    }

    // READ ALL
    public List<Supplier> getAllSuppliers() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers ORDER BY supplier_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suppliers.add(new Supplier(
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("contact_person")
                ));
            }
        }
        return suppliers;
    }

    // READ ONE
    public Supplier getSupplierById(int id) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE supplier_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("contact_person")
                    );
                }
            }
        }
        return null;
    }

    // UPDATE
    public void updateSupplier(Supplier s) throws SQLException {
        String sql = "UPDATE suppliers SET name = ?, email = ?, phone = ?, contact_person = ? WHERE supplier_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getPhone());
            stmt.setString(4, s.getContactPerson());
            stmt.setInt(5, s.getSupplierId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deleteSupplier(int id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
