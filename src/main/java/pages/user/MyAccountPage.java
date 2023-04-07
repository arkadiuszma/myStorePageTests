package pages.user;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "#_desktop_logo > a")
    private WebElement myStoreBtn;
    @FindBy(css = ".links > a")
    private List<WebElement> categoriesList;

    public void goToProductGridPage(){
        log.info("Clicking my store button");
        clickElement(myStoreBtn);
    }
    public void clickCategoriesByName(String name){
        WebElement element = categoriesList.stream().filter(el -> el.getAttribute("innerText").contains(name)).toList().get(0);
        clickElement(element);
    }
}
