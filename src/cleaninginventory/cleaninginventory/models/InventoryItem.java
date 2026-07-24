package cleaninginventory.cleaninginventory.models;

public class InventoryItem {
    private int itemId;
    private String itemName;
    private String category;
    private int quantityAvailable;
    private int minimumStockLevel;
    private String unitOfMeasure;

    // Default Constructor
    public InventoryItem() {}

    // Parameterized Constructor
    public InventoryItem(int itemId, String itemName, String category, int quantityAvailable, int minimumStockLevel, String unitOfMeasure) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.quantityAvailable = quantityAvailable;
        this.minimumStockLevel = minimumStockLevel;
        this.unitOfMeasure = unitOfMeasure;
    }

    // Getters and Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { this.quantityAvailable = quantityAvailable; }

    public int getMinimumStockLevel() { return minimumStockLevel; }
    public void setMinimumStockLevel(int minimumStockLevel) { this.minimumStockLevel = minimumStockLevel; }

    public String getUnitOfMeasure() { return unitOfMeasure; }
    public void setUnitOfMeasure(String unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }

    // Helper method for low stock alert
    public boolean isLowStock() {
        return this.quantityAvailable <= this.minimumStockLevel;
    }
}