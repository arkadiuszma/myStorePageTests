package tests.categories;

public class CategoriesDataProvider {

    protected int getCategoriesNumber() {
        return getIntValue(System.getProperty("categoryNumber"));
    }

    protected int getSubCategoriesNumber() {
        return getIntValue(System.getProperty("subCategoryNumber"));
    }

    private int getIntValue(String value) {
        assert value != null;
        return Integer.parseInt(value);
    }
}
