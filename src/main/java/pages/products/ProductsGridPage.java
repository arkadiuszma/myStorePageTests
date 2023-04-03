package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class ProductsGridPage extends BasePage {

    public ProductsGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-miniature")
    private List<WebElement> products;

    @FindBy(css = "[aria-label='Search']")
    private WebElement searchInput;

    @FindBy(css = "#ui-id-1 li")
    private List<WebElement> searchItems;
    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;

    @FindBy(css = ".dropdown-item:not(.dropdown-submenu)")
    private List<WebElement> categoriesList;
    @FindBy(css = ".dropdown-submenu")
    private List<WebElement> subCategoriesList;

    public List<ProductsMiniaturePage> getProducts() {
        return products.stream().map(element -> new ProductsMiniaturePage(driver, element)).toList();
    }

    public String getRandomProductTitle() {
        List<String> list = getProducts().stream().map(ProductsMiniaturePage::getProductTitle).toList();
        return getRandomElementFromList(list);
    }

    public String chooseRandomProductAndGetTitle() {
        String productName = getRandomProductTitle();
        sendKeysToElement(searchInput, productName);
        return productName;
    }

    public ProductsGridPage enterSearchingProduct(String text) {
        sendKeysToElement(searchInput, text);
        return this;
    }

    public List<String> getSearchProductNames() {
        log.info("Getting search product names to list");
        return searchItems.stream().map(WebElement::getText).toList();
    }

    public ProductsResultsPage clickSearchProducts() {
        clickElement(searchBtn);
        return new ProductsResultsPage(driver);
    }

    public ProductCategoriesPage clickCategory(int numberOfElement) {
        log.info("Clicking in chosen category");
        categoriesList.get(numberOfElement).click();
        return new ProductCategoriesPage(driver);
    }

    public String getCategoryText(int numberOfElement) {
        log.info("Getting category text");
        return categoriesList.get(numberOfElement).getText();
    }

    public String getSubCategoryText(int i) {
        log.info("Getting sub category text");
        chooseProperCategory(i);
        return subCategoriesList.get(i).getText();
    }

    public ProductCategoriesPage clickSubCategory(int i) {
        log.info("Clicking sub category");
        chooseProperCategory(i);
        subCategoriesList.get(i).click();
        return new ProductCategoriesPage(driver);
    }

    private void chooseProperCategory(int i) {
        if (i < Integer.parseInt(System.getProperty("clothesCategoryNumber"))) {
            moveToElement(categoriesList.get(0));
        } else {
            moveToElement(categoriesList.get(1));
        }
    }
}
