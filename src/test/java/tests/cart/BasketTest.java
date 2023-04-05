package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.cart.CartPopUpPage;
import pages.products.ProductsCategoriesPage;
import pages.products.ProductsDetailsPage;
import pages.products.ProductsGridPage;
import testModels.Order;
import testModels.Product;
import tests.configuration.BaseTest;

import java.util.Random;
import java.util.stream.IntStream;


@Slf4j
public class BasketTest extends BaseTest {
    CartDataProvider c = new CartDataProvider();
    Product productDetailsAfterAdd;
    Product productDetailsBeforeAdd;
    SoftAssertions s = new SoftAssertions();
    Order expectedOrder = new Order();
    Order actualOrder = new Order();

    @Test
    @DisplayName("Cart test")
    public void shouldValidProductProperties() {
        log.info("Start cart test");
        at(ProductsGridPage.class).clickCategoryByName(c.getCategoryName());
        at(ProductsCategoriesPage.class).getProductByName(c.getProductName());
        at(ProductsDetailsPage.class).setQuantity(c.getQuantity());
        productDetailsBeforeAdd = at(ProductsDetailsPage.class).getProductDetailsBeforeAddToCart();
        at(ProductsDetailsPage.class).addToCart();
        String quantityText = at(CartPopUpPage.class).getProductQuantityAssertion();
        productDetailsAfterAdd = at(CartPopUpPage.class).getProductDetails();
        at(CartPopUpPage.class).continueShoppingClick();
        String basketCount = at(ProductsDetailsPage.class).getCartProductsCount();

        log.info("Starting assertions");
        s.assertThat(productDetailsAfterAdd).usingRecursiveComparison().isEqualTo(productDetailsBeforeAdd);
        s.assertThat(quantityText).isEqualTo(c.getQuantityAssertionText());
        s.assertThat(basketCount).isEqualTo(c.getCartQuantityExpectedText());
        s.assertAll();
    }
    @Test
    @DisplayName("Order test")
    public void shouldValidRandomProductsOrder() {
        log.info("Start order test");
        IntStream.range(0, c.getNumberOfProducts()).forEach(i -> {
            driver.get(c.getUrl());
            expectedOrder.addProduct(at(ProductsGridPage.class).getRandomProduct()
                    .setQuantity(new Random().nextInt(5) + 1)
                    .getProductDetailsBeforeAddToCart());
            at(ProductsDetailsPage.class).addToCart();
        });
        actualOrder = at(CartPopUpPage.class).goToCartPage().toOrder();
        log.info("Start assertion");
        Assertions.assertThat(actualOrder).usingRecursiveComparison().isEqualTo(expectedOrder);
    }
}
