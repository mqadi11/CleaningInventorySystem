/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventorysystem.model;

/**
 *
 * @author Tetelo
 */
public class InventoryReport {
    private String materialName;
    private String category;
    private int quantity;
    private int minimumStockLevel;
    private String supplierName;
    
    public InventoryReport(
                            String materialName,
            String category,
            int quantity,
            int minimumStockLevel,
            String supplierName){
        
        this.materialName = materialName;
        this.category = category;
        this.quantity = quantity;
        this.minimumStockLevel = minimumStockLevel;
        this.supplierName = supplierName;
    } 
    
      public String getMaterialName() {
        return materialName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public String getSupplierName() {
        return supplierName;
    }

    @Override
    public String toString() {
        return materialName + " | " +
               category + " | " +
               quantity + " | " +
               minimumStockLevel + " | " +
               supplierName;
    }
}


--Issuances History
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventorysystem.model;

/**
 *
 * @author Tetelo
 */
public class IssuanceHistory {
    
    private String materialName;
    private String issuedByUsername;
    private String dateIssued;
    private int quantityIssued;
    private String cleanerName;

    public IssuanceHistory(
                        String materialName,
                        String issuedByUsername,
                        String dateIssued,
                        int quantityIssued,
                        String cleanerName) {

        this.materialName = materialName;
        this.issuedByUsername = issuedByUsername;
        this.dateIssued = dateIssued;
        this.quantityIssued = quantityIssued;
        this.cleanerName = cleanerName;
    }
     public String getMaterialName() {
        return materialName;
    }

    public String getIssuedByUsername() {
        return issuedByUsername;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public int getQuantityIssued() {
        return quantityIssued;
    }

    public String getCleanerName() {
        return cleanerName;
    }

    @Override
    public String toString() {
        return materialName + " | "
                + issuedByUsername + " | "
                + dateIssued + " | "
                + quantityIssued + " | "
                + cleanerName;
    }
}

--LowstockReport
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventorysystem.model;

/**
 *
 * @author Tetelo
 */
public class LowStockReport {
    private String materialName;
    private String category;
    private int quantity;
    private int minimumStockLevel;
    private String supplierName;
    
    public LowStockReport(
            String materialName,
            String category,
            int quantity,
            int minimumStockLevel,
            String supplierName){
        
        this.materialName = materialName;
        this.category = category;
        this.quantity = quantity;
        this.minimumStockLevel = minimumStockLevel;
        this.supplierName = supplierName;
    }
   public String getMaterialName() {
        return materialName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public String getSupplierName() {
        return supplierName;
    }

    @Override
    public String toString() {
        return materialName + " | " +
               category + " | " +
               quantity + " | " +
               minimumStockLevel + " | " +
               supplierName;
    }
}

--Material Usage Report
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cleaninginventorysystem.model;

/**
 *
 * @author Tetelo
 */
public class LowStockReport {
    private String materialName;
    private String category;
    private int quantity;
    private int minimumStockLevel;
    private String supplierName;
    
    public LowStockReport(
            String materialName,
            String category,
            int quantity,
            int minimumStockLevel,
            String supplierName){
        
        this.materialName = materialName;
        this.category = category;
        this.quantity = quantity;
        this.minimumStockLevel = minimumStockLevel;
        this.supplierName = supplierName;
    }
   public String getMaterialName() {
        return materialName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public String getSupplierName() {
        return supplierName;
    }

    @Override
    public String toString() {
        return materialName + " | " +
               category + " | " +
               quantity + " | " +
               minimumStockLevel + " | " +
               supplierName;
    }
}

--ModelPlaceholder.java
