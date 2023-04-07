package pages.checkout;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class CheckoutAddressPage extends BasePage {
    public CheckoutAddressPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "[data-link-action='different-invoice-address']")
    private WebElement differentBillingAddressBtn;

    @FindBy(css = "[name='address1']")
    private WebElement addressInput;
    @FindBy(css = "[name='city']")
    private WebElement cityInput;
    @FindBy(css = "[name='id_state']")
    private WebElement stateSelect;
    @FindBy(css = "[name='postcode']")
    private WebElement postCodeInput;
    @FindBy(css = ".form-footer > .continue")
    private WebElement continueButton;
    @FindBy(css = "#delivery-addresses article")
    private List<WebElement> shippingAddresses;
    @FindBy(css = "#delivery-addresses article:nth-child(2) .delete-address")
    private WebElement deleteSecondAddressBtn;

    public CheckoutAddressPage clickDifferentBillingAddress(){
        log.info("Clicking difference billing address");
        clickElement(differentBillingAddressBtn);
        return this;
    }
    public CheckoutAddressPage enterAddress(String address){
        sendKeysToElement(addressInput, address);
        return this;
    }
    public CheckoutAddressPage enterCity(String city){
        sendKeysToElement(cityInput, city);
        return this;
    }
    public CheckoutAddressPage selectState(String state){
        log.info("Selecting state: " + state);
        new Select(stateSelect).selectByVisibleText(state);
        return this;
    }
    public CheckoutAddressPage enterPostCode(String postCode){
        sendKeysToElement(postCodeInput, postCode);
        return this;
    }
    public void clickContinue(){
        log.info("Clicking continue");
        clickElement(continueButton);
    }
    public CheckoutAddressPage removingSecondAddressIfExist(){
        if (shippingAddresses.size()==2){
            clickElement(deleteSecondAddressBtn);
        }
        return this;
    }
}
