package tests;

import configuration.DriverFactory;
import configuration.PropertyStore;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

@Slf4j
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    protected void setupDriver() {
        new PropertyStore();
        driver = new DriverFactory().getDriver();
        log.debug("Driver initialized properly");
    }

    @AfterEach
    protected void tearDown() {
        driver.quit();
        log.info("Browser was closed.");
    }
    @SneakyThrows
    protected <T extends BasePage> T at(Class <T> pageName){
        log.debug("Creating page object: " + pageName.getName());
        return pageName.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }
}
