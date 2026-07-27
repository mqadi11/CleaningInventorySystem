
package cleaninginventorysystem.model;


public class Supplier {
    private int supplierId;
    private String name;
    private String email;
    private String phone;
    private String contactPerson;

    public Supplier() {}

    public Supplier(int supplierId, String name, String email, String phone, String contactPerson) {
        this.supplierId = supplierId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.contactPerson = contactPerson;
    }

    // For creating new suppliers (no ID yet — Database autogenerates it)
    public Supplier(String name, String email, String phone, String contactPerson) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.contactPerson = contactPerson;
    }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    @Override
    public String toString() {
        return supplierId + " | " + name + " | " + email + " | " + phone + " | " + contactPerson;
    }
}
