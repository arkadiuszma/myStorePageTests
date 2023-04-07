package pages.user;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Slf4j
public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[name='email']")
    private WebElement emailInput;
    @FindBy(css = "[name='password']")
    private WebElement passwordInput;
    @FindBy(css = "#submit-login")
    private WebElement signInBtn;
    public SignInPage enterEmail(String email) {
        sendKeysToElement(emailInput, email);
        return this;
    }

    public SignInPage enterPassword(String password) {
        sendKeysToElement(passwordInput, password);
        return this;
    }
    public void confirmSignIn(){
        log.info("Confirming sign in");
        clickElement(signInBtn);
    }
}
