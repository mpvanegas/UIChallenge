package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
    @FindBy(xpath = "//h3[contains(text(),'Registrar Usuario')]")
    private WebElement pageTitle;

    public RegistrationPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getTitle(){
        return pageTitle.getText();
    }
}
