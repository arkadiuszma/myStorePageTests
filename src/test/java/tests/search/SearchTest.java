package tests.search;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.products.ProductsGridPage;
import pages.products.ProductsResultsPage;
import tests.configuration.BaseTest;

import java.util.List;

@Slf4j
public class SearchTest extends BaseTest {
    @Test
    @DisplayName("Search product title test")
    public void shouldSearchExpectedProduct(){
        log.info("Start search product title test");
        String productName = at(ProductsGridPage.class).chooseRandomProductAndGetTitle();
        at(ProductsGridPage.class).clickSearchProducts();
        String productDetailName = at(ProductsResultsPage.class).getProductTitle();
        Assertions.assertThat(productName).isEqualTo(productDetailName);
    }
    @ParameterizedTest
    @DisplayName("Dropdown search test")
    @CsvSource({"HUMMINGBIRD"})
    public void shouldShowProductsContainsOfGivenName(String productName){
        log.info("Start dropdown search test");
        List<String> productsList = at(ProductsGridPage.class).enterSearchingProduct(productName)
                .getSearchProductNames();
        log.info("Checking that all elements from list contains searching text");
        productsList.forEach(text -> Assertions.assertThat(text).contains(productName));
    }
}
