package tests.checkout;

import java.math.BigDecimal;

public class CheckoutDataProvider {
    protected String getEmail() {
        return System.getProperty("eMail");
    }

    protected String getPassword() {
        return System.getProperty("password");
    }

    protected String getCity() {
        return System.getProperty("secondCity");
    }

    protected String getState() {
        return System.getProperty("secondState");
    }

    protected String getAddress() {
        return System.getProperty("secondAddress");
    }

    protected String getPostCode() {
        return System.getProperty("secondPostCode");
    }

    protected String getCategoryName() {
        return System.getProperty("category");
    }

    protected String getProductName() {
        return System.getProperty("productNameInCheckoutTest");
    }
    protected BigDecimal getProductPrice(){
        return BigDecimal.valueOf(Long.parseLong(System.getProperty("productPrice")));
    }
}
