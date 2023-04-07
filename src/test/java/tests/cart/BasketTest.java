package tests.cart;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.cart.CartPopUpPage;
import pages.products.ProductsCategoriesPage;
import pages.products.ProductsDetailsPage;
import pages.products.ProductsGridPage;
import testModels.Order;
import testModels.Product;
import tests.configuration.BaseTest;

import java.math.BigDecimal;
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
    private final int productsNumber = c.getRemovingTestProductNumber();
    private final int indexOfElementToRemove = c.getIndexOfElementToRemove();

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
    @DisplayName("Order generic test")
    public void shouldValidRandomProductsOrder() {
        log.info("Start order generic test");
        getProductsToBasket(c.getOrderProductNumber());
        actualOrder = at(CartPopUpPage.class).goToCartPage().toOrder();
        log.info("Start assertion");
        Assertions.assertThat(actualOrder).usingRecursiveComparison().isEqualTo(expectedOrder);
    }

    @Test
    @DisplayName("Removing products test")
    public void shouldRemoveProductsFromCart() {
        log.info("Start remove product test");
        getProductsToBasket(productsNumber);
        BigDecimal cost = at(CartPopUpPage.class).goToCartPage().getTotalItemsCost();
        BigDecimal expectedCost = expectedOrder.getTotalOrderSum();
        boolean isEqualAfterFirstRemove = isTotalCostEqualAfterRemove(expectedOrder.getProductsCount());
        if (expectedOrder.getProductsCount() > 0 && expectedOrder.getProductsCount() >= indexOfElementToRemove) {
            boolean isEqualAfterSecondRemove = isTotalCostEqualAfterRemove(indexOfElementToRemove);
            s.assertThat(isEqualAfterSecondRemove).isEqualTo(true);
        }
        if (expectedOrder.getProductsCount() == 0) {
            s.assertThat(at(CartPage.class).getNoItemsInCartMessage()).isEqualTo(c.getMessage());
            s.assertThat(at(CartPage.class).getCartProductsCount()).isEqualTo(c.getZeroQuantityInBasketText());
        }
        log.info("Start assertions");
        s.assertThat(cost).isEqualTo(expectedCost);
        s.assertThat(isEqualAfterFirstRemove).isEqualTo(true);
        s.assertAll();
    }

    private void getProductsToBasket(int loopNumber) {
        IntStream.range(0, loopNumber).forEach(i -> {
            driver.get(c.getUrl());
            expectedOrder.addProduct(at(ProductsGridPage.class).getRandomProduct()
                    .setQuantity(new Random().nextInt(c.getQuantityToRandom()) + 1)
                    .getProductDetailsBeforeAddToCart());
            at(ProductsDetailsPage.class).addToCart();
        });
    }

    private boolean isTotalCostEqualAfterRemove(int elementIndex) {
        BigDecimal removeValue = at(CartPage.class).getElementTotalPriceByIndex(elementIndex);
        at(CartPage.class).removeProductFromCartByIndex(elementIndex);
        BigDecimal costAfterRemove = expectedOrder.getOrderCostAfterRemovingItem(removeValue);
        return costAfterRemove.compareTo(at(CartPage.class).getTotalItemsCost()) == 0;
    }
}
