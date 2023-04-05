package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.cart.CartPopUpPage;

import java.math.BigDecimal;

@Slf4j
public class ProductsDetailsPage extends BasePage {
    public ProductsDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#quantity_wanted")
    private WebElement quantity;
    @FindBy(css = "[data-button-action='add-to-cart']")
    private WebElement addToCartBtn;
    @FindBy(css = "span.cart-products-count")
    private WebElement cartProductsCount;
    @FindBy(css = "[itemprop='price']")
    private WebElement productPrice;

    public ProductsDetailsPage setQuantity(int quantity) {
        log.info("Setting product quantity");
        sendKeysToElement(this.quantity, String.valueOf(quantity));
        return this;
    }

    public CartPopUpPage addToCart() {
        log.info("Adding product to cart.");
        clickElement(addToCartBtn);
        return new CartPopUpPage(driver);
    }

    public String getCartProductsCount() {
        log.info("Getting products count located in basket");
        return cartProductsCount.getAttribute("innerText");
    }

    public BigDecimal getProductPrice() {
        log.info("Getting product price from product details page");
        return getPriceFromString(productPrice.getAttribute("innerText"));
    }
}
