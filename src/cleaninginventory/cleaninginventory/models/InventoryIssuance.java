/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventory.cleaninginventory.models;

import java.sql.Timestamp;

public class InventoryIssuance {
    private int issuanceId;
    private int itemId;
    private String issuedTo;
    private String campusLocation;
    private int quantityIssued;
    private Timestamp dateIssued;
    private String remarks;

    // Default Constructor
    public InventoryIssuance() {}

    // Parameterized Constructor
    public InventoryIssuance(int issuanceId, int itemId, String issuedTo, String campusLocation, int quantityIssued, Timestamp dateIssued, String remarks) {
        this.issuanceId = issuanceId;
        this.itemId = itemId;
        this.issuedTo = issuedTo;
        this.campusLocation = campusLocation;
        this.quantityIssued = quantityIssued;
        this.dateIssued = dateIssued;
        this.remarks = remarks;
    }

    // Getters and Setters
    public int getIssuanceId() { return issuanceId; }
    public void setIssuanceId(int issuanceId) { this.issuanceId = issuanceId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getIssuedTo() { return issuedTo; }
    public void setIssuedTo(String issuedTo) { this.issuedTo = issuedTo; }

    public String getCampusLocation() { return campusLocation; }
    public void setCampusLocation(String campusLocation) { this.campusLocation = campusLocation; }

    public int getQuantityIssued() { return quantityIssued; }
    public void setQuantityIssued(int quantityIssued) { this.quantityIssued = quantityIssued; }

    public Timestamp getDateIssued() { return dateIssued; }
    public void setDateIssued(Timestamp dateIssued) { this.dateIssued = dateIssued; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
