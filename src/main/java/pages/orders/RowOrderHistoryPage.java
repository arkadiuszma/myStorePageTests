package pages.orders;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class RowOrderHistoryPage extends BasePage {
    public RowOrderHistoryPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = "tbody th")
    private WebElement orderReference;
    @FindBy(css = ".order-actions > a:nth-child(1)")
    private WebElement detailsBtn;

    public String getOrderReferenceText() {
        return orderReference.getText();
    }

    public void clickDetails() {
        log.info("Clicking order details");
        clickElement(detailsBtn);
    }
}
