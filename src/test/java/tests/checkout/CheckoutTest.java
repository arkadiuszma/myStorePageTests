package tests.checkout;

import lombok.extern.slf4j.Slf4j;
import models.User;
import models.UserFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.cart.CartPage;
import pages.cart.CartPopUpPage;
import pages.checkout.CheckoutAddressPage;
import pages.checkout.CheckoutPaymentPage;
import pages.checkout.CheckoutShippingPage;
import pages.checkout.CheckoutSummaryPage;
import pages.orders.OrderHistoryPage;
import pages.products.ProductsDetailsPage;
import pages.products.ProductsGridPage;
import pages.user.MyAccountPage;
import pages.user.SignInPage;
import tests.configuration.BaseTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CheckoutTest extends BaseTest {
    CheckoutDataProvider c = new CheckoutDataProvider();
    private final String invoiceAddress = c.getAddress();
    private final String invoiceCity = c.getCity();
    private final String invoiceState = c.getState();
    private final String invoicePostCode = c.getPostCode();

    @Test
    @DisplayName("Checkout test")
    public void shouldCheckOrderReferences() {
        log.info("Start checkout test");
        signIn(c.getEmail(), c.getPassword());
        addProductToCartByName(c.getProductName());
        BigDecimal totalPrice = at(CartPopUpPage.class).getProductPrice();
        goToCheckout();
        addInvoiceAddress(invoiceAddress, invoiceCity, invoiceState, invoicePostCode);
        chooseShippingAndPaymentMethod();
        goToOrderHistoryByReferrence();

        log.info("Start assertions");
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(at(OrderHistoryPage.class).getOrderDate()).isEqualTo(getCurrentDate());
            s.assertThat(at(OrderHistoryPage.class).getOrderStatus()).isEqualTo(c.getOrderStatus());
            s.assertThat(at(OrderHistoryPage.class).getTotalPrice()).isEqualTo(totalPrice);
            s.assertThat(at(OrderHistoryPage.class).getDeliveryAddressText()).isEqualTo(getDeliveryAddressText());
            s.assertThat(at(OrderHistoryPage.class).getInvoiceAddressText()).isEqualTo(getInvoiceAddressText());
        });
    }

    private void signIn(String email, String password) {
        at(ProductsGridPage.class).clickSignInBtn();
        at(SignInPage.class).enterEmail(email)
                .enterPassword(password)
                .confirmSignIn();
    }

    private void addProductToCartByName(String productName) {
        at(MyAccountPage.class).goToProductGridPage();
        at(ProductsGridPage.class).getProductByName(productName);
        at(ProductsDetailsPage.class).addToCart();
    }

    private void goToCheckout() {
        at(CartPopUpPage.class).clickProceedToCheckout();
        at(CartPage.class).clickProceedToCheckout();
    }

    private void addInvoiceAddress(String address, String city, String state, String postCode) {
        at(CheckoutAddressPage.class).clickDifferentBillingAddress()
                .removingSecondAddressIfExist()
                .enterAddress(address)
                .enterCity(city)
                .selectState(state)
                .enterPostCode(postCode)
                .clickContinue();
    }

    private void chooseShippingAndPaymentMethod() {
        at(CheckoutShippingPage.class).clickContinue();
        at(CheckoutPaymentPage.class).selectPayByCheck()
                .acceptAgreement()
                .clickPlaceOrder();
    }

    private void goToOrderHistoryByReferrence() {
        String orderReference = at(CheckoutSummaryPage.class).getOrderReference();
        at(CheckoutSummaryPage.class).clickMyAccount();
        at(MyAccountPage.class).clickCategoriesByName(c.getCategoryName());
        at(OrderHistoryPage.class).clickOrderDetailsByOrderReference(orderReference);
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    }

    private String getInvoiceAddressText() {
        User user = new User(c.getUserName(), c.getUserLastName(), invoiceState, invoiceCity, invoicePostCode, invoiceAddress);
        return user.getUserAddressAssertionText();
    }

    private String getDeliveryAddressText() {
        return new UserFactory().getAlreadyRegisteredUser().getUserAddressAssertionText();
    }
}
