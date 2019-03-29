package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class CheckoutPage extends BasePage {

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "country")
    private WebElement countryOptions;

    @FindBy(id = "dpto")
    private WebElement dptoOptions;

    @FindBy(id = "zip")
    private WebElement zipInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(id = "card_number")
    private WebElement cardNumberInput;

    @FindBy(id = "exp_date")
    private WebElement expDateInput;

    @FindBy(id = "code")
    private WebElement codeInput;

    @FindBy(id = "purchase")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='modal-body' and contains(text(),'compra')]")
    private WebElement message;

    @FindBy(xpath = "//div[@class='introduce']")
    private WebElement paymentMethods;

    private By countryOptionColombiaBy = By.xpath(".//option[contains(text(),'Colombia')]");
    private By dptoOptionBogotaBy = By.xpath(".//option[contains(text(),'Bogotá D.C')]");
    private By radioButtonSelectBy = By.xpath(".//input");
    private By messageTitleBy = By.xpath("//h5[@id='paymentm']");
    private By messageOkButtonBy = By.xpath("//button[@class='btn btn-success']");
    private String methodXpath = "//div[@class='radio']";

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillAndSubmitPersonalInfo(String address, String zip, String phone) {

        webDriverFacade.waitForLoaderInvisibility();

        addressInput.sendKeys(address);

        webDriverFacade.waitForElementToBeClickable(countryOptions);
        countryOptions.click();
        webDriverFacade.waitForElementToBeClickable(countryOptions.findElement(countryOptionColombiaBy)).click();

        webDriverFacade.waitForElementToBeClickable(dptoOptions);
        dptoOptions.click();
        webDriverFacade.waitForElementToBeVisible(dptoOptions.findElement(dptoOptionBogotaBy));
        webDriverFacade.waitForElementToBeClickable(dptoOptions.findElement(dptoOptionBogotaBy)).click();

        zipInput.sendKeys(zip);
        phoneInput.sendKeys(phone);
    }

    public void fillAndSubmitCardInfo(String cardNumber, String expDate, String code) {

        cardNumberInput.sendKeys(cardNumber);
        expDateInput.sendKeys(expDate);
        codeInput.sendKeys(code);

        submitButton = webDriverFacade.waitForElementVisibility(submitButton);
        submitButton.click();
    }

    public void selectPaymentMethod() {
        Actions action = new Actions(webDriverFacade.getWebDriver());
        Random rand = new Random();

        int numPayMethods = paymentMethods.findElements(By.xpath(methodXpath)).size();
        WebElement randomMethod = paymentMethods.findElements(By.xpath(methodXpath)).get(rand.nextInt(numPayMethods + 1));

        WebElement radioButton = randomMethod.findElement(radioButtonSelectBy);
        webDriverFacade.waitForElementToBeClickable(radioButton);
        radioButton.click();

        submitButton = webDriverFacade.waitForElementVisibility(submitButton);
        submitButton.click();
    }

    @Override
    public String checkMessage() {
        webDriverFacade.waitForElementToBeVisible(message);
        String messageTitle = message.findElement(messageTitleBy).getText();

        WebElement messageOkButton = message.findElement(messageOkButtonBy);
        webDriverFacade.waitForElementToBeClickable(messageOkButton);
        messageOkButton.click();
        return messageTitle;
    }
}
