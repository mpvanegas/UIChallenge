package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage{

    @FindBy(xpath = "//a[contains(text(),'Registrarse')]")
    WebElement registerButton;

    @FindBy(xpath = "//*[@class='header-login']/a[text()[contains(string(),'I')]]")
    WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutButton;

    WebDriverWait wait;

    public HomePage(WebDriver webDriver){
        super(webDriver);
    //    wait = new WebDriverWait(webDriver, 10);
    }

    public RegistrationPage goToRegisterPage(){

        registerButton = waitForElementVisibility(registerButton);
        registerButton.click();

        return PageFactory.initElements(webDriver, RegistrationPage.class);
    }

    public LoginPage goToLoginPage(){

        loginButton = waitForElementVisibility(loginButton);
        loginButton.click();

        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    public String getLogout(){
        logoutButton = waitForElementVisibility(logoutButton);
        return logoutButton.getText();
    }
}
