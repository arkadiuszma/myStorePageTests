package pages.cart;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import testModels.Product;

import java.math.BigDecimal;

@Slf4j
public class CartRowPage extends BasePage {
    public CartRowPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = ".product-line-info a.label")
    private WebElement productName;
    @FindBy(css = ".current-price span")
    private WebElement productPrice;
    @FindBy(css = "[name='product-quantity-spin']")
    private WebElement productQuantity;
    @FindBy(css = ".product-price strong")
    private WebElement totalPrice;
    @FindBy(css = ".remove-from-cart")
    private WebElement removeProductBtn;

    private String getProductName() {
        log.info("Getting product name from card row page");
        return getInnerText(productName);
    }

    private BigDecimal getProductPrice() {
        log.info("Getting product price from card row page");
        return getPriceFromString(getWebElementText(productPrice));
    }

    private int getQuantity() {
        log.info("Getting product quantity from card row page");
        return Integer.parseInt(productQuantity.getAttribute("defaultValue"));
    }

    public BigDecimal getTotalProductCost() {
        log.info("Getting product total cost from card row page");
        return getPriceFromString(getInnerText(totalPrice));
    }

    public Product toProduct() {
        return new Product(getProductName(), getProductPrice(), getQuantity(), getTotalProductCost());
    }

    public void removeProductFromCart() {
        log.info("Removing product from card");
        clickElement(removeProductBtn);
    }

}
