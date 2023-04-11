package pages.checkout;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class CheckoutShippingPage extends BasePage {
    public CheckoutShippingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='confirmDeliveryOption']")
    private WebElement continueButton;

    public void clickContinue() {
        log.info("Clicking continue");
        clickElement(continueButton);
    }
}
