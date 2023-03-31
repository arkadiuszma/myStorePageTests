package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class ProductsResultPage extends BasePage {

    public ProductsResultPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".product-miniature")
    private List<WebElement> products;

    public List<ProductsMiniaturePage> getProducts(){
        return products.stream().map(element -> new ProductsMiniaturePage(driver, element)).toList();
    }
    public String getProductTitle(){
        List <String> list = getProducts().stream().map(ProductsMiniaturePage::getProductTitle).toList();
        return list.get(0);
    }
}
