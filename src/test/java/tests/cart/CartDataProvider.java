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
    protected String getQuantityAssertionText() {
        int qnt = getQuantity();
        if (qnt==1){
            return "There is " + qnt + " item in your cart.";
        }
        return "There are " + qnt + " items in your cart.";
    }
    protected String getCartQuantityExpectedText(){
        return "(" + getQuantity() + ")";
    }
    protected int getNumberOfProducts() {
        return getIntValue(System.getProperty("productsNumber"));
    }
    protected String getUrl(){
        return System.getProperty("url");
    }
    private int getIntValue(String value) {
        assert value != null;
        return Integer.parseInt(value);
    }
}
