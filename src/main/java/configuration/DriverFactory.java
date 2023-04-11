package configuration;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

@Slf4j
public class DriverFactory {
    private String browser;
    private String url;
    private long timeout;
    private WebDriver driver;


    public DriverFactory() {
        this.browser = "chrome";
        this.initBrowserSettings();
    }

    private void initBrowserSettings() {
        this.browser = System.getProperty("browser") != null ? System.getProperty("browser").toUpperCase() : this.browser.toUpperCase();
        this.timeout = System.getProperty("defaultTimeout") != null ? getLong(System.getProperty("defaultTimeout")) : this.timeout;
        this.url = System.getProperty("url") != null ? System.getProperty("url") : null;
    }

    public WebDriver getDriver() {
        switch (Browser.valueOf(this.browser)) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                driver = new ChromeDriver(options);
            }
            case FIREFOX -> {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("start-maximized");
                driver = new EdgeDriver(options);
            }
            case IE -> {
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
            }
        }
        driver.get(this.url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(this.timeout));
        log.debug("Browser: " + this.browser);
        log.debug(("Default timeout: " + this.timeout + " sec."));
        return driver;
    }

    public long getLong(String value) {
        return Long.parseLong(value);
    }
}
