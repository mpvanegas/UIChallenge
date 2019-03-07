package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage{

    @FindBy(xpath = "//h3[contains(text(),'Registrar Usuario')]")
    private WebElement pageTitle;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "pass1")
    private WebElement passInput;

    @FindBy(id = "pass2")
    private WebElement confirmPassInput;

   // @FindBy(id = "register")
    @FindBy(xpath = "//*[@id='register']")
    private WebElement registerButton;

    private Alert alert;

    public RegistrationPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getTitle(){
        return pageTitle.getText();
    }

    public void fillAndSubmitForm(String name, String lastName, String username, String email, String password, String confirmCassword){

        nameInput.sendKeys(name);
        lastNameInput.sendKeys(lastName);
        usernameInput.sendKeys(username);
        emailInput.sendKeys(email);
        passInput.sendKeys(password);
        confirmPassInput.sendKeys(confirmCassword);

        registerButton = waitForElementVisibility(registerButton);
        registerButton.click();
    }

    public String checkAlert(){
        alert = new WebDriverWait(webDriver, 5).until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }
}
