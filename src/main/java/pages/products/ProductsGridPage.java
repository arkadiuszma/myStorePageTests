package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class ProductsGridPage extends BasePage {

    public ProductsGridPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".product-miniature")
    private List<WebElement> products;

    @FindBy(css = "[aria-label='Search']")
    private WebElement searchInput;

    @FindBy(css = "#ui-id-1 li")
    private List <WebElement> searchItems;
    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;

    public List<ProductsMiniaturePage> getProducts(){
        return products.stream().map(element -> new ProductsMiniaturePage(driver, element)).toList();
    }
    public String getRandomProductTitle(){
        List <String> list = getProducts().stream().map(ProductsMiniaturePage::getProductTitle).toList();
        return getRandomElementFromList(list);
    }
    public String chooseRandomProductAndGetTitle(){
        String productName = getRandomProductTitle();
        sendKeysToElement(searchInput, productName);
        return productName;
    }
    public ProductsResultPage goToProductsDetailsPage(){
        clickElement(searchBtn);
        return new ProductsResultPage(driver);
    }
}
