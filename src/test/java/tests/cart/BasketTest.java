package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.cart.CartPopUpPage;
import pages.products.ProductsCategoriesPage;
import pages.products.ProductsDetailsPage;
import pages.products.ProductsGridPage;
import testModels.Product;
import tests.configuration.BaseTest;

@Slf4j
public class BasketTest extends BaseTest {
    CartDataProvider c = new CartDataProvider();
    Product product;
    SoftAssertions s = new SoftAssertions();
    int qnt = c.getQuantity();
    String productName = c.getProductName();

    @Test
    @DisplayName("Cart test")
    public void cartTest() {
        at(ProductsGridPage.class).clickCategoryByName(c.getCategoryName());
        at(ProductsCategoriesPage.class).getProductByName(productName);
        String productPrice = String.valueOf(at(ProductsDetailsPage.class).getProductPrice());
        at(ProductsDetailsPage.class).setQuantity(qnt)
                .addToCart();
        String quantityText = at(CartPopUpPage.class).getProductQuantityAssertion();
        product = new Product(at(CartPopUpPage.class).getProductName(), at(CartPopUpPage.class).getProductPrice(),
                at(CartPopUpPage.class).getQuantity(), at(CartPopUpPage.class).getTotalProductsCost());
        at(CartPopUpPage.class).continueShoppingClick();
        String basketCount = at(ProductsDetailsPage.class).getCartProductsCount();

        log.info("Starting assertions");
        s.assertThat(product.name()).isEqualTo(productName);
        s.assertThat(product.price()).isEqualTo(productPrice);
        s.assertThat(product.quantity()).isEqualTo(qnt);
        s.assertThat(product.totalCost()).isEqualTo(product.getTotalPrice());
        s.assertThat(quantityText).isEqualTo(c.getQuantityAssertionText());
        s.assertThat(basketCount).isEqualTo(c.getCartQuantityExpectedText());
    }
}
