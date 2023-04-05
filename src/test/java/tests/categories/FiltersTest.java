package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.products.ProductsCategoriesPage;
import pages.products.ProductsGridPage;
import tests.configuration.BaseTest;

@Slf4j
public class FiltersTest extends BaseTest {
    CategoriesDataProvider c = new CategoriesDataProvider();
    int priceFrom = c.getPriceFrom();
    int priceTo = c.getPriceTo();
    SoftAssertions s = new SoftAssertions();

    @Test
    @DisplayName("Filters test")
    public void shouldCheckFiltersWork() {
        at(ProductsGridPage.class).clickCategoryByName(c.getCategoryFilterText());
        long quantityBefore = at(ProductsCategoriesPage.class).getProductsCount();
        boolean isPriceBetweenFilters = at(ProductsCategoriesPage.class).filterByPrice(priceFrom, priceTo)
                .isPriceBetweenFilters(priceFrom, priceTo);
        long quantityAfter = at(ProductsCategoriesPage.class).removeFilters().getProductsCount();

        log.info("Checking assertions");
        s.assertThat(isPriceBetweenFilters).isEqualTo(true);
        s.assertThat(quantityBefore).isEqualTo(quantityAfter);
    }
}
