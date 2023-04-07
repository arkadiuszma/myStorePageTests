package tests.cart;

public class CartDataProvider {

    protected String getCategoryName() {
        return System.getProperty("categoryName");
    }

    protected String getProductName() {
        return System.getProperty("productName");
    }

    protected int getQuantity() {
        return getIntValue(System.getProperty("quantity"));
    }

    protected int getQuantityToRandom() {
        return getIntValue(System.getProperty("quantityRandom"));
    }

    protected String getQuantityAssertionText() {
        int qnt = getQuantity();
        if (qnt == 1) {
            return "There is " + qnt + " item in your cart.";
        }
        return "There are " + qnt + " items in your cart.";
    }

    protected String getCartQuantityExpectedText() {
        return "(" + getQuantity() + ")";
    }

    protected int getOrderProductNumber() {
        return getIntValue(System.getProperty("productsNumberInOrderTest"));
    }

    protected int getRemovingTestProductNumber() {
        return getIntValue(System.getProperty("productsNumberInRemovingTest"));
    }

    protected int getIndexOfElementToRemove() {
        return getIntValue(System.getProperty("indexOfElementToRemove"));
    }

    protected String getUrl() {
        return System.getProperty("url");
    }

    protected String getMessage() {
        return System.getProperty("noItemsMessage");
    }

    protected String getZeroQuantityInBasketText() {
        return System.getProperty("quantityIfZeroProductsInCartText");
    }

    private int getIntValue(String value) {
        assert value != null;
        return Integer.parseInt(value);
    }
}
