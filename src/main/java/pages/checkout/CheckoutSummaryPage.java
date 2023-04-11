package pages.checkout;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class CheckoutSummaryPage extends BasePage {
    public CheckoutSummaryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-details > ul > li:nth-child(1)")
    private WebElement orderReference;
    @FindBy(css = ".account")
    private WebElement myAccountBtn;

    public String getOrderReference() {
        return getWebElementText(orderReference).replace("Order reference: ", "");
    }

    public void clickMyAccount() {
        log.info("Clicking my account button");
        clickElement(myAccountBtn);
    }
}
