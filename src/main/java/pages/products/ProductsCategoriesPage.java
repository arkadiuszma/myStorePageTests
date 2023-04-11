package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class ProductsCategoriesPage extends BasePage {
    public ProductsCategoriesPage(WebDriver driver) {
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
    @FindBy(css = "[id*='slider-range_']")
    private WebElement slider;
    @FindBy(css = ".collapse p")
    private WebElement filterPriceText;
    @FindBy(css = ".ui-slider-handle:nth-of-type(1)")
    private WebElement rightMoveSlider;
    @FindBy(css = ".ui-slider-handle:nth-of-type(2)")
    private WebElement leftMoveSlider;
    @FindBy(css = ".js-search-filters-clear-all")
    private WebElement clearFilterBtn;

    public String getCategoryName() {
        log.info("Getting category name from result page");
        return getWebElementText(categoryTitle);
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

    public List<ProductsMiniaturePage> getProducts() {
        return products.stream().map(element -> new ProductsMiniaturePage(driver, element)).toList();
    }

    public ProductsCategoriesPage filterByPrice(int priceFrom, int priceTo) {
        action.scrollToElement(slider).perform();
        int min = getMinSliderPrice();
        int max = getMaxSliderPrice();
        int oneOffset = slider.getSize().getWidth() / (max - min);
        int minOffset = (priceFrom - min) * oneOffset;
        int maxOffset = (priceTo - max) * oneOffset;
        action.dragAndDropBy(rightMoveSlider, minOffset, 0).perform();
        action.scrollToElement(slider).perform();
        action.dragAndDropBy(leftMoveSlider, maxOffset, 0).perform();
        log.info("Current filter price: $" + priceFrom + " - $" + priceTo);
        return this;
    }

    private int getMinSliderPrice() {
        log.info("Getting min price on slider");
        return getPrice(filterPriceText, 1, 3);
    }

    private int getMaxSliderPrice() {
        log.info("Getting max price on slider");
        return getPrice(filterPriceText, 10, 12);
    }

    private int getPrice(WebElement element, int charFrom, int charTo) {
        String s = element.getText();
        return Integer.parseInt(s.substring(charFrom, charTo));
    }

    public List<BigDecimal> getPrices() {
        log.info("Getting filtered products prices");
        wait(1);
        return getProducts().stream().map(ProductsMiniaturePage::getProductPrice).toList();
    }

    public boolean isPriceBetweenFilters(int priceFrom, int priceTo) {
        log.info("Checking are prices between entered filters");
        for (BigDecimal price : getPrices()) {
            if (price.compareTo(new BigDecimal(priceTo)) == 0) {
                return true;
            }
            int integerPrice = price.intValue();
            if (priceFrom > integerPrice || integerPrice >= priceTo) {
                return false;
            }
        }
        return true;
    }

    public ProductsCategoriesPage removeFilters() {
        log.info("Clearing filters");
        action.moveToElement(clearFilterBtn).perform();
        clickElement(clearFilterBtn);
        wait(1);
        return this;
    }

    public void getProductByName(String productName) {
        log.info("Getting product by name: " + productName);
        getProducts().stream().filter(el -> el.getProductTitle().equals(productName)).toList().get(0).clickProduct();
    }
}
