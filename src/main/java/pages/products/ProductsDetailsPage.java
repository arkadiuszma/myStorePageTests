package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.cart.CartPopUpPage;
import testModels.Product;

import java.math.BigDecimal;

@Slf4j
public class ProductsDetailsPage extends BasePage {
    Product product;
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
    @FindBy(css = ".h1[itemprop='name']")
    private WebElement productName;

    public ProductsDetailsPage setQuantity(int quantity) {
        log.info("Setting product quantity: " + quantity);
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
    public String getProductName(){
        log.info("Getting product name from details page");
        return productName.getAttribute("innerText");
    }
    public int getQuantity(){
        log.info("Getting quantity from details page");
        return Integer.parseInt(quantity.getAttribute("value"));
    }
    public Product getProductDetailsBeforeAddToCart(){
        return new Product(getProductName(), getProductPrice(), getQuantity());
    }
}
