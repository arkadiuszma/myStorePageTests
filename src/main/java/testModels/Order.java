package testModels;

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
        boolean isInOrder = false;
        for(Product p : products){
            if (product.getName().equals(p.getName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                p.setTotalCost(p.getTotalCost().add(product.getTotalCost()));
                isInOrder = true;
                break;
            }
        }
        if (!isInOrder){
            products.add(product);
        }
    }
}
