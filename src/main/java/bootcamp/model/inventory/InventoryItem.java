package bootcamp.model.inventory;

import java.math.BigDecimal;

public class InventoryItem {

    private int id;
    private BigDecimal retail_price;
    private int number_available;

    public int getNumber_available() {
        return number_available;
    }

    public void setNumber_available(int number_available) {
        this.number_available = number_available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getRetailPrice() {
        return retail_price;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retail_price = retailPrice;
    }
}
