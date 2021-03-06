package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//*[@id='register']")
    private WebElement registerButton;

    public RegistrationPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getTitle(){
        return pageTitle.getText();
    }

    public void fillAndSubmitForm(String name, String lastName, String username, String email, String password, String confirmPassword) {

        nameInput.sendKeys(name);
        lastNameInput.sendKeys(lastName);
        usernameInput.sendKeys(username);
        emailInput.sendKeys(email);
        passInput.sendKeys(password);
        confirmPassInput.sendKeys(confirmPassword);

        registerButton = webDriverFacade.waitForElementVisibility(registerButton);
        registerButton.click();
    }
}
