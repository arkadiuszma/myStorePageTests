package testModels;

import java.math.BigDecimal;

public record Product(String name, BigDecimal price, int quantity, BigDecimal totalCost) {
    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
