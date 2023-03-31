package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.products.ProductsGridPage;

public class SearchTest extends BaseTest{
    @Test
    public void searchTest(){
        String productName = at(ProductsGridPage.class).chooseRandomProductAndGetTitle();
        String productDetailName = at(ProductsGridPage.class).goToProductsDetailsPage().getProductTitle();
        Assertions.assertThat(productName).isEqualTo(productDetailName);
    }
}
