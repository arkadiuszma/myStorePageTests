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
    Product productDetailsAfterAdd;
    Product productDetailsBeforeAdd;
    SoftAssertions s = new SoftAssertions();

    @Test
    @DisplayName("Cart test")
    public void shouldValidCorrectProductProperties() {
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
}
