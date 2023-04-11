package pages.orders;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> rowsList;
    @FindBy(css = "#order-history td:nth-child(1)")
    private WebElement dateRow;
    @FindBy(css = "#order-history td:nth-child(2)")
    private WebElement statusRow;
    @FindBy(css = "td.text-xs-right:last-child")
    private WebElement totalPrice;
    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;
    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    public String getOrderDate() {
        log.info("Getting order date from order history page");
        return getWebElementText(dateRow);
    }

    public String getOrderStatus() {
        log.info("Getting order status from order history page");
        return getWebElementText(statusRow);
    }

    public BigDecimal getTotalPrice() {
        log.info("Getting total price from order history page");
        return getPrice(totalPrice);
    }

    public String getDeliveryAddressText() {
        log.info("Getting delivery address from order history page");
        return getWebElementText(deliveryAddress);
    }

    public String getInvoiceAddressText() {
        log.info("Getting invoice address from order history page");
        return getWebElementText(invoiceAddress);
    }

    public void clickOrderDetailsByOrderReference(String orderReference) {
        log.info("Clicking details for order: " + orderReference);
        getHistoryOrderRows().stream().filter(el -> el.getOrderReferenceText().equals(orderReference)).toList().get(0).clickDetails();
    }

    private List<RowOrderHistoryPage> getHistoryOrderRows() {
        return rowsList.stream().map(el -> new RowOrderHistoryPage(driver, el)).toList();
    }
}
