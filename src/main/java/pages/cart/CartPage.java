package pages.cart;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import testModels.Order;

import java.util.List;
@Slf4j
public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItemsList;

    public Order toOrder() {
        log.info("Getting products order from cart page");
        Order order = new Order();
        for (WebElement row : cartItemsList) {
            order.addProduct(new CartRowPage(driver, row).toProduct());
        }
        return order;
    }
}
