package tests.categories;

public class CategoriesDataProvider {

    protected int getCategoriesNumber() {
        return getIntValue(System.getProperty("categoryNumber"));
    }

    protected int getSubCategoriesNumber() {
        return getIntValue(System.getProperty("subCategoryNumber"));
    }

    protected String getCategoryFilterText() {
        return System.getProperty("categoryFilter");
    }

    protected int getPriceFrom() {
        return getIntValue(System.getProperty("priceFrom"));
    }

    protected int getPriceTo() {
        return getIntValue(System.getProperty("priceTo"));
    }

    private int getIntValue(String value) {
        assert value != null;
        return Integer.parseInt(value);
    }
}
