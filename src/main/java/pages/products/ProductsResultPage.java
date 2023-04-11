package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class ProductsResultPage extends BasePage {

    public ProductsResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-miniature")
    private List<WebElement> products;

    public List<ProductsMiniaturePage> getProducts() {
        return products.stream().map(element -> new ProductsMiniaturePage(driver, element)).toList();
    }

    public String getProductTitle() {
        log.info("Getting product title");
        String productTitle = getProducts().stream().map(ProductsMiniaturePage::getProductTitle).toList().get(0);
        log.info("Product title: " + productTitle);
        return productTitle;
    }
}
