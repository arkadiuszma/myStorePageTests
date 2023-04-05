package tests.categories;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.products.ProductsCategoriesPage;
import pages.products.ProductsGridPage;
import tests.configuration.BaseTest;

@Slf4j
public class CategoriesTest extends BaseTest {
    CategoriesDataProvider c = new CategoriesDataProvider();
    SoftAssertions s = new SoftAssertions();

    @Test
    @DisplayName("Categories test")
    public void shouldCheckCategoryNames() {
        log.info("Start categories test");
        for (int i = 0; i < c.getCategoriesNumber(); i++) {
            String categoryName = at(ProductsGridPage.class).getCategoryText(i);
            at(ProductsGridPage.class).clickCategoryByIndex(i);
            s.assertThat(at(ProductsCategoriesPage.class).isFilterGridDisplayed()).isEqualTo(true);
            s.assertThat(categoryName).isEqualTo(at(ProductsCategoriesPage.class).getCategoryName());
            s.assertThat(at(ProductsCategoriesPage.class).getNumberOfProducts()).isEqualTo(at(ProductsCategoriesPage.class).getProductsCount());
            s.assertAll();
        }
    }

    @Test
    @DisplayName("Sub categories test")
    public void shouldCheckSubCategoryNames() {
        log.info("Start sub categories test");
        for (int i = 0; i < c.getSubCategoriesNumber(); i++) {
            String categoryName = at(ProductsGridPage.class).getSubCategoryText(i);
            at(ProductsGridPage.class).clickSubCategory(i);
            s.assertThat(at(ProductsCategoriesPage.class).isFilterGridDisplayed()).isEqualTo(true);
            s.assertThat(categoryName).isEqualTo(at(ProductsCategoriesPage.class).getCategoryName());
            s.assertThat(at(ProductsCategoriesPage.class).getNumberOfProducts()).isEqualTo(at(ProductsCategoriesPage.class).getProductsCount());
            s.assertAll();
        }
    }
}
