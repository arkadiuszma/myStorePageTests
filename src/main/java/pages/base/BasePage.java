package pages.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
public class BasePage {
    public BasePage(WebDriver driver) {
        initDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement element) {
        initDriver(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    protected void initDriver(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("webElementTimeout"))));
        action = new Actions(driver);
    }

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    protected <T> T getRandomElementFromList(List<T> elements) {
        assert elements != null;
        log.info("Getting random element from list");
        return elements.get(new Random().nextInt(elements.size()));
    }

    protected BigDecimal getPrice(WebElement element) {
        return new BigDecimal(element.getText().replace("$", ""));
    }

    protected BigDecimal getPriceFromString(String text) {
        return new BigDecimal(text.replace("$", ""));
    }

    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void sendKeysToElement(WebElement element, String text) {
        element.clear();
        if (!element.getText().isEmpty()) {
            element.sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
        }
        element.sendKeys(text);
        log.info("Entered value: " + text);
    }

    protected String getWebElementText(WebElement element) {
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void moveToElement(WebElement element) {
        action.moveToElement(element).perform();
    }

    protected void wait(int seconds) {
        long x = 1000;
        try {
            Thread.sleep(x * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getInnerText(WebElement element) {
        return element.getAttribute("innerText");
    }
}
