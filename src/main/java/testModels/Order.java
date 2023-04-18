package testModels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        for (Product p : products) {
            if (product.getName().equals(p.getName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                p.setTotalCost(p.getTotalCost().add(product.getTotalCost()));
                return;
            }
        }
        products.add(product);
    }

    public BigDecimal getTotalOrderSum() {
        double a = products.stream().mapToDouble(p -> p.getTotalCost().doubleValue()).sum();
        return BigDecimal.valueOf(a).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getOrderCostAfterRemovingItem(BigDecimal removeCost) {
        products.removeIf(p -> removeCost.equals(p.getTotalCost()));
        return getTotalOrderSum();
    }

    public int getProductsCount() {
        return products.size();
    }
}
