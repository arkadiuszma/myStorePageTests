package tests.categories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.products.ProductCategoriesPage;
import pages.products.ProductsGridPage;
import tests.configuration.BaseTest;

public class CategoriesTest extends BaseTest {
    CategoriesDataProvider c = new CategoriesDataProvider();

    @Test
    @DisplayName("Categories test")
    public void shouldCheckCategoryNames() {
        for (int i = 0; i < c.getCategoriesNumber(); i++) {
            String categoryName = at(ProductsGridPage.class).getCategoryText(i);
            at(ProductsGridPage.class).clickCategory(i);
            Assertions.assertThat(at(ProductCategoriesPage.class).isFilterGridDisplayed()).isEqualTo(true);
            Assertions.assertThat(categoryName).isEqualTo(at(ProductCategoriesPage.class).getCategoryName());
            Assertions.assertThat(at(ProductCategoriesPage.class).getNumberOfProducts()).isEqualTo(at(ProductCategoriesPage.class).getProductsCount());
        }
    }

    @Test
    @DisplayName("Sub categories test")
    public void shouldCheckSubCategoryNames() {
        for (int i = 0; i < c.getSubCategoriesNumber(); i++) {
            String categoryName = at(ProductsGridPage.class).getSubCategoryText(i);
            at(ProductsGridPage.class).clickSubCategory(i);
            Assertions.assertThat(at(ProductCategoriesPage.class).isFilterGridDisplayed()).isEqualTo(true);
            Assertions.assertThat(categoryName).isEqualTo(at(ProductCategoriesPage.class).getCategoryName());
            Assertions.assertThat(at(ProductCategoriesPage.class).getNumberOfProducts()).isEqualTo(at(ProductCategoriesPage.class).getProductsCount());
        }
    }
}
