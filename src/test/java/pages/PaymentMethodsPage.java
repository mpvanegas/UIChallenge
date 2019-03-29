package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentMethodsPage extends BasePage {

    @FindBy(xpath = "//button[@id='list_payment_btn']")
    private WebElement addMethodButton;

    @FindBy(id = "card_number")
    private WebElement cardNumberInput;

    @FindBy(id = "exp_date")
    private WebElement expDateInput;

    @FindBy(id = "code")
    private WebElement codeInput;

    @FindBy(id = "sub_new")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='modal-body' and contains(text(),'agregado')]")
    private WebElement message;

    private By messageTitleBy = By.xpath("//h5[@id='paymentm']");
    private By messageOkButtonBy = By.xpath("//button[@class='btn btn-success']");

    public PaymentMethodsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addPaymentMethod(String cardNumber, String expDate, String code) {

        webDriverFacade.waitForLoaderInvisibility();

        webDriverFacade.waitForElementToBeClickable(addMethodButton);
        addMethodButton.click();

        webDriverFacade.waitForElementToBeClickable(cardNumberInput);
        cardNumberInput.sendKeys(cardNumber);
        expDateInput.sendKeys(expDate);
        codeInput.sendKeys(code);

        submitButton = webDriverFacade.waitForElementVisibility(submitButton);
        submitButton.click();

        checkMessage();
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
