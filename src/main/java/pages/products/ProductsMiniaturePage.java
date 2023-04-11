package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

public class ProductsMiniaturePage extends BasePage {

    public ProductsMiniaturePage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = ".product-title")
    private WebElement productTitle;

    @FindBy(css = ".price")
    private WebElement productPrice;

    public String getProductTitle() {
        return getWebElementText(productTitle);
    }

    public BigDecimal getProductPrice() {
        return getPrice(productPrice);
    }

    public void clickProduct() {
        clickElement(productTitle);
    }
}
