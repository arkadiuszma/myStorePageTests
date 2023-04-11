package pages.cart;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import testModels.Product;

import java.math.BigDecimal;

@Slf4j
public class CartPopUpPage extends BasePage {
    public CartPopUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "p.product-price")
    private WebElement productPrice;
    @FindBy(css = ".product-quantity strong")
    private WebElement productQuantity;
    @FindBy(css = "p.cart-products-count")
    private WebElement productsCountAssertion;
    @FindBy(css = ".subtotal.value")
    private WebElement productTotalValue;
    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingBtn;
    @FindBy(css = ".product-name")
    private WebElement productName;
    @FindBy(css = "a.btn-primary")
    private WebElement checkoutBtn;

    public BigDecimal getProductPrice() {
        log.info("Getting product price from cart pop up page");
        return getPriceFromString(productPrice.getAttribute("innerText"));
    }

    public int getQuantity() {
        log.info("Getting quantity number from cart pop up page");
        return Integer.parseInt(productQuantity.getAttribute("innerText"));
    }

    public String getProductQuantityAssertion() {
        log.info("Getting quantity product assertion from cart pop up page");
        return productsCountAssertion.getAttribute("innerText");
    }

    public BigDecimal getTotalProductsCost() {
        log.info("Getting total product cost from cart pop up page");
        return getPriceFromString(productTotalValue.getAttribute("innerText"));
    }

    public void continueShoppingClick() {
        log.info("Clicking continue shopping");
        clickElement(continueShoppingBtn);
    }

    public String getProductName() {
        log.info("Getting product name from cart pop up page");
        return productName.getAttribute("innerText");
    }

    public Product getProductDetails() {
        return new Product(getProductName(), getProductPrice(), getQuantity(), getTotalProductsCost());
    }

    public CartPage clickProceedToCheckout() {
        log.info("Clicking proceed to checkout");
        clickElement(checkoutBtn);
        return new CartPage(driver);
    }
}
