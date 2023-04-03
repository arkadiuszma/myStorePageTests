package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class ProductCategoriesPage extends BasePage {
    public ProductCategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-miniature")
    private List<WebElement> products;
    @FindBy(css = "#products p:not(.h6)")
    private WebElement numbersOfProductSearch;

    @FindBy(css = ".h1")
    private WebElement categoryTitle;

    @FindBy(css = "#search_filters")
    private WebElement filtersGrid;

    public String getCategoryName() {
        log.info("Getting category name from result page");
        return categoryTitle.getText();
    }

    public boolean isFilterGridDisplayed() {
        log.info("Checking is filter grid displayed");
        return isDisplayed(filtersGrid);
    }

    public long getNumberOfProducts() {
        log.info("Getting number of products from grid text");
        String text = numbersOfProductSearch.getText().replace("There are ", "")
                .replace(" products.", "")
                .replace(" product.", "")
                .replace("There is ", "");
        return Long.parseLong(text);
    }

    public long getProductsCount() {
        log.info("Getting number of products in category by counting miniatures");
        return products.size();
    }

}
