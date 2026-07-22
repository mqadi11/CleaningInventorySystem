package cleaninginventory.cleaninginventory.dao;

import cleaninginventory.cleaninginventory.DatabaseConnection;
import cleaninginventory.cleaninginventory.models.Cleaner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CleanerDAO {

    public boolean addCleaner(Cleaner c) {
        String sql = "INSERT INTO cleaners (first_name, last_name, employee_number, department) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmployeeNumber());
            ps.setString(4, c.getDepartment());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to add cleaner: " + e.getMessage());
            return false;
        }
    }

    public List<Cleaner> getAllCleaners() {
        List<Cleaner> list = new ArrayList<>();
        String sql = "SELECT * FROM cleaners ORDER BY last_name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Cleaner(
                        rs.getInt("cleaner_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("employee_number"),
                        rs.getString("department")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch cleaners: " + e.getMessage());
        }
        return list;
    }

    public boolean updateCleaner(Cleaner c) {
        String sql = "UPDATE cleaners SET first_name=?, last_name=?, employee_number=?, department=? WHERE cleaner_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmployeeNumber());
            ps.setString(4, c.getDepartment());
            ps.setInt(5, c.getCleanerId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to update cleaner: " + e.getMessage());
            return false;
        }
    }

    public boolean hasIssuanceHistory(int cleanerId) {
        String sql = "SELECT COUNT(*) FROM issuance_transactions WHERE cleaner_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cleanerId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Failed to check issuance history: " + e.getMessage());
            return true; // fail safe: assume history exists so we don't accidentally allow deletion
        }
    }

    public boolean deleteCleaner(int cleanerId) {
        if (hasIssuanceHistory(cleanerId)) {
            System.err.println("Cannot delete cleaner: issuance history exists.");
            return false;
        }
        String sql = "DELETE FROM cleaners WHERE cleaner_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cleanerId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to delete cleaner: " + e.getMessage());
            return false;
        }
    }
}