package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//h3[contains(text(),'Inicia Sesión')]")
    private WebElement pageTitle;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passInput;

    @FindBy(xpath = "//*[@id='login']")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getTitle(){
        return pageTitle.getText();
    }

    public HomePage fillAndSubmitForm(String username, String password){

        usernameInput.sendKeys(username);
        passInput.sendKeys(password);

        loginButton = webDriverFacade.waitForElementVisibility(loginButton);
        loginButton.click();

        return PageFactory.initElements(webDriverFacade.getWebDriver(), HomePage.class);
    }
}
