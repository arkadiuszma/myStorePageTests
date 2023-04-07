package pages.user;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "tbody tr")
    private List<WebElement> rowsList;

    public void clickOrderDetailsByOrderReference(String orderReference){
        log.info("Clicking details for order: " + orderReference);
        getHistoryOrderRows().stream().filter(el -> el.getOrderReferenceText().equals(orderReference)).toList().get(0).clickDetails();
    }
    private List<RowOrderHistoryPage> getHistoryOrderRows(){
        return rowsList.stream().map(el -> new RowOrderHistoryPage(driver, el)).toList();
    }
}
