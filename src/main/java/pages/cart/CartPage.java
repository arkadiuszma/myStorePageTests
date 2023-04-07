package pages.cart;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import testModels.Order;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItemsList;
    @FindBy(css = "#cart-subtotal-products span.value")
    private WebElement totalProductCosts;
    @FindBy(css = ".no-items")
    private WebElement noItemsMessage;
    @FindBy(css = "span.cart-products-count")
    private WebElement cartProductsCount;
    @FindBy(css = "a.btn-primary")
    private WebElement proceedToCheckoutBtn;

    public Order toOrder() {
        log.info("Getting products order from cart page");
        Order order = new Order();
        for (WebElement row : cartItemsList) {
            order.addProduct(new CartRowPage(driver, row).toProduct());
        }
        return order;
    }

    private List<CartRowPage> getCartRows() {
        return cartItemsList.stream().map(row -> new CartRowPage(driver, row)).toList();
    }

    public BigDecimal getElementTotalPriceByIndex(int elementIndex) {
        log.info("Getting total price for row with index: " + elementIndex);
        return getCartRows().get(elementIndex - 1).getTotalProductCost();
    }

    public void removeProductFromCartByIndex(int elementIndex) {
        log.info("Removing row with index: " + elementIndex + " from cart");
        getCartRows().get(elementIndex - 1).removeProductFromCart();
    }

    public BigDecimal getTotalItemsCost() {
        log.info("Getting total items cost");
        wait(2);
        return getPrice(totalProductCosts);
    }
    public String getNoItemsInCartMessage(){
        return getWebElementText(noItemsMessage);
    }

    public String getCartProductsCount() {
        log.info("Getting products count located in basket");
        return cartProductsCount.getAttribute("innerText");
    }
    public void clickProceedToCheckout(){
        log.info("Clicking proceed to checkout");
        clickElement(proceedToCheckoutBtn);
    }
}
