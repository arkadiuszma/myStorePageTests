package tests.search;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.products.ProductsGridPage;
import pages.products.ProductsResultPage;
import tests.configuration.BaseTest;

import java.util.List;

@Slf4j
public class SearchTest extends BaseTest {
    @Test
    @DisplayName("Search product title test")
    public void shouldSearchExpectedProduct() {
        log.info("Start search product title test");
        String productName = at(ProductsGridPage.class).chooseRandomProductAndGetTitle();
        at(ProductsGridPage.class).clickSearchProducts();
        String productDetailName = at(ProductsResultPage.class).getProductTitle();
        Assertions.assertThat(productName).isEqualTo(productDetailName);
    }

    @Test
    @DisplayName("Dropdown search test")
    public void shouldShowProductsContainsOfGivenName() {
        String productName = System.getProperty("product");
        log.info("Start dropdown search test");
        List<String> productsList = at(ProductsGridPage.class).enterSearchingProduct(productName)
                .getSearchProductNames();
        log.info("Checking that all elements from list contains searching text");
        productsList.forEach(text -> Assertions.assertThat(text).contains(productName));
    }
}
