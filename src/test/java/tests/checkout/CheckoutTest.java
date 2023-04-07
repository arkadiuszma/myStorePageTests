package tests.checkout;

import lombok.extern.slf4j.Slf4j;
import models.User;
import models.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.cart.CartPopUpPage;
import pages.checkout.CheckoutAddressPage;
import pages.checkout.CheckoutPaymentPage;
import pages.checkout.CheckoutShippingPage;
import pages.checkout.CheckoutSummaryPage;
import pages.products.ProductsDetailsPage;
import pages.products.ProductsGridPage;
import pages.user.MyAccountPage;
import pages.user.OrderHistoryPage;
import pages.user.SignInPage;
import tests.configuration.BaseTest;

@Slf4j
public class CheckoutTest extends BaseTest {
    CheckoutDataProvider c = new CheckoutDataProvider();
    private final String email = c.getEmail();
    private final String password = c.getPassword();
    private final String productName = c.getProductName();
    User firstUser = new UserFactory().getAlreadyRegisteredUser();
    @Test
    @DisplayName("Checkout test")
    public void shouldCheckOrderReferences(){
        log.info("Start checkout test");
        at(ProductsGridPage.class).clickSignInBtn();
        at(SignInPage.class).enterEmail(email)
                .enterPassword(password)
                .confirmSignIn();
        at(MyAccountPage.class).goToProductGridPage();
        at(ProductsGridPage.class).getProductByName(productName);
        at(ProductsDetailsPage.class).addToCart();
        at(CartPopUpPage.class).clickProceedToCheckout();
        at(CartPage.class).clickProceedToCheckout();
        at(CheckoutAddressPage.class).clickDifferentBillingAddress()
                .removingSecondAddressIfExist()
                .enterAddress(c.getAddress())
                .enterCity(c.getCity())
                .selectState(c.getState())
                .enterPostCode(c.getPostCode())
                .clickContinue();
        at(CheckoutShippingPage.class).clickContinue();
        at(CheckoutPaymentPage.class).selectPayByCheck()
                .acceptAgreement()
                .clickPlaceOrder();
        String orderReference = at(CheckoutSummaryPage.class).getOrderReference();
        at(CheckoutSummaryPage.class).clickMyAccount();
        at(MyAccountPage.class).clickCategoriesByName(c.getCategoryName());
        at(OrderHistoryPage.class).clickOrderDetailsByOrderReference(orderReference);
    }
}
