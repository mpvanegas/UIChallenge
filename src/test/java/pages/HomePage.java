package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{

    //@FindBy(xpath = "/html/body/header/div/div/div/div[2]/div/a[2][@href='/regUser/']")
    @FindBy(xpath = "//a[contains(text(),'Registrarse')]")
    WebElement registerButton;

    WebDriverWait wait;

    public HomePage(WebDriver webDriver){
        super(webDriver);
        wait = new WebDriverWait(webDriver, 10);
    }

    public RegistrationPage clickRegisterButton(){

        registerButton = waitForClickableElement(registerButton);
        registerButton.click();

        return PageFactory.initElements(webDriver, RegistrationPage.class);
    }
}
