package testModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Product {
    private String name;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalCost;

    public Product(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalCost = getTotalPrice();
    }

    private BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
