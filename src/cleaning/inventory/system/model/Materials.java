
package cleaning.inventory.system.model;

public class Materials {

    private int material_id;
    private String name;
    private int quantity_in_stock;
    private int reorder_level;
    private Integer supplier_id;
    private String description;

    public Materials() {
    }
    

    public Materials(int material_id, String name, int quantity_in_stock, int reorder_level, int supplier_id, String description) {
        this.material_id = material_id;
        this.name = name;
        this.quantity_in_stock = quantity_in_stock;
        this.reorder_level = reorder_level;
        this.supplier_id = supplier_id;
        this.description = description;
    }
    
        // When creating new materials
        public Materials(String name, int quantity_in_stock, int reorder_level, int supplier_id, String description) {
        this.name = name;
        this.quantity_in_stock = quantity_in_stock;
        this.reorder_level = reorder_level;
        this.supplier_id = supplier_id;
        this.description = description;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public int getReorder_level() {
        return reorder_level;
    }

    public Integer getSupplier_id() {
        return supplier_id;
    }

    public String getDescription() {
        return description;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public void setReorder_level(int reorder_level) {
        this.reorder_level = reorder_level;
    }

    public void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public String toString() {
        return material_id + " | "  + name + " | Stock: " + quantity_in_stock + " | Reorder at: " + reorder_level + " | Supplier: " + supplier_id + " | " + description;
    }
    
}

