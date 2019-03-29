package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComplexProductsPage extends BasePage {

    @FindBy(xpath = "//h3[@id='basket_name']")
    private WebElement pageTitle;

    public ComplexProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTitle() {
        webDriverFacade.waitForElementVisibility(pageTitle);
        return pageTitle.getText();
    }
}
