
package cleaning.inventory.system.Controller;
import cleaninginventory.cleaninginventory.dao.SupplierDAO;
import cleaninginventorysystem.model.Supplier;
import java.sql.SQLException;
import java.util.List;

public class SupplierController {
    private final SupplierDAO supplierDAO;

    public SupplierController() {
        this.supplierDAO = new SupplierDAO();
    }

    // CREATE
    public boolean addSupplier(String name, String email, String phone, String contactPerson) {
        if (name == null || name.trim().isEmpty()) {
            return false; // basic validation lives here, not in the GUI or DAO
        }
        try {
            supplierDAO.addSupplier(new Supplier(name, email, phone, contactPerson));
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding supplier: " + e.getMessage());
            return false;
        }
    }

    // READ ALL
    public List<Supplier> getAllSuppliers() {
        try {
            return supplierDAO.getAllSuppliers();
        } catch (SQLException e) {
            System.err.println("Error fetching suppliers: " + e.getMessage());
            return List.of();
        }
    }

    // READ ONE
    public Supplier getSupplierById(int id) {
        try {
            return supplierDAO.getSupplierById(id);
        } catch (SQLException e) {
            System.err.println("Error fetching supplier: " + e.getMessage());
            return null;
        }
    }

    // UPDATE
    public boolean updateSupplier(Supplier s) {
        if (s.getName() == null || s.getName().trim().isEmpty()) {
            return false;
        }
        try {
            supplierDAO.updateSupplier(s);
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating supplier: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteSupplier(int id) {
        try {
            supplierDAO.deleteSupplier(id);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting supplier: " + e.getMessage());
            return false;
        }
    }
}
