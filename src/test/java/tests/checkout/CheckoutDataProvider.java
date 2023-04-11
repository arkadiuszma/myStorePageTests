package tests.checkout;

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

    protected String getOrderStatus() {
        return System.getProperty("orderStatus");
    }

    protected String getUserName() {
        return System.getProperty("name");
    }

    protected String getUserLastName() {
        return System.getProperty("lastName");
    }
}
