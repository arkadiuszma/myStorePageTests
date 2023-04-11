package pages.checkout;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class CheckoutPaymentPage extends BasePage {
    public CheckoutPaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#payment-option-1")
    private WebElement payByCheckBtn;
    @FindBy(css = ".custom-checkbox")
    private WebElement agreementCheckbox;
    @FindBy(css = ".ps-shown-by-js > .btn")
    private WebElement placeOrderBtn;

    public CheckoutPaymentPage selectPayByCheck() {
        log.info("Selecting pay by check");
        payByCheckBtn.click();
        return this;
    }

    public CheckoutPaymentPage acceptAgreement() {
        log.info("Accepting agreement");
        clickElement(agreementCheckbox);
        return this;
    }

    public void clickPlaceOrder() {
        log.info("Clicking place order");
        placeOrderBtn.click();
    }
}
