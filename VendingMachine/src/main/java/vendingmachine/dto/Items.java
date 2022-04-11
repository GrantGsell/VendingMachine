package vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Grant
 */
public class Items {
    // Item fields
    private String name;
    private BigDecimal price;
    private int stock;
    private String selectionCode;

    // Class getters
    public String getName() {
        return name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }

    public String getSelectionCode(){
        return selectionCode;
    }
    
    // Class setters
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public void setSelectionCode(String code){
        this.selectionCode = code;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
