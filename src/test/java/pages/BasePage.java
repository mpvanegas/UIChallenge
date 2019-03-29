package pages;

import drivers.WebDriverFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    @FindBy(xpath = "//div[@class='loader']")
    protected WebElement pageLoader;

    @FindBy(xpath = "//a[contains(text(),'Registrarse')]")
    protected WebElement registerButton;

    @FindBy(xpath = "//button[@class='btn btn-danger'][contains(text(),'Intentar de Nuevo')]")
    protected WebElement messageButton;
    @FindBy(xpath = "//a[text()[contains(string(),'Iniciar')]]")
    protected WebElement loginButton;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    protected WebElement logoutButton;
    @FindBy(xpath = "//div[@class='container']//a[@class='has-text-color-hover'][contains(text(),'LOGO')]")
    protected WebElement catalogButton;
    @FindBy(xpath = "//div[@class='cart-shopping js-mini-shopcart']")
    protected WebElement shoppingCartButton;
    @FindBy(xpath = "//button[@class='au-btn au-btn-primary au-btn-radius btn-checkout']")
    protected WebElement checkoutButton;
    @FindBy(xpath = "//div[@class='container']//a[@class='has-text-color-hover'][contains(text(),'PAGO')]")
    protected WebElement paymentMethodsButton;
    @FindBy(xpath = "//div[@class='container']//a[@class='has-text-color-hover'][contains(text(),'ORDENES')]")
    protected WebElement ordersButton;
    protected WebDriverFacade webDriverFacade;
    protected By messageTitleBy = By.xpath("//h5[@id='paymentm']");
    protected Alert alert;
    protected By messageOkButtonBy = By.xpath("//button[@class='btn btn-success']");
    @FindBy(xpath = "//div[@class='modal-body'][contains(text(),' incorrectos.')]")
    WebElement message;

    public BasePage(WebDriver webDriver) {
        this.webDriverFacade = new WebDriverFacade(webDriver);
    }

    public String checkAlert() {
        return webDriverFacade.checkAlert();
    }

    public String checkMessage() {
        new WebDriverWait(webDriverFacade.getWebDriver(), 5).until(ExpectedConditions.visibilityOf(message));

        String messagee = message.getText();
        messageButton.click();

        return messagee;
    }

    public RegistrationPage goToRegisterPage() {

        registerButton = webDriverFacade.waitForElementVisibility(registerButton);
        registerButton.click();

        return PageFactory.initElements(webDriverFacade.getWebDriver(), RegistrationPage.class);
    }

    public LoginPage goToLoginPage() {

        loginButton = webDriverFacade.waitForElementVisibility(loginButton);
        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        webDriverFacade.waitForElementToBeClickable(loginButton);
        loginButton.click();

        return PageFactory.initElements(webDriverFacade.getWebDriver(), LoginPage.class);
    }

    public HomePage goToLogout() {
        logoutButton = webDriverFacade.waitForElementToBeVisible(logoutButton);
        webDriverFacade.waitForLoaderInvisibility();
        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", logoutButton);
        logoutButton.click();
        return PageFactory.initElements(webDriverFacade.getWebDriver(), HomePage.class);
    }

    public CatalogPage goToCatalogPage() {
        catalogButton = webDriverFacade.waitForElementVisibility(catalogButton);
        webDriverFacade.waitForLoaderInvisibility();
        catalogButton.click();
        return PageFactory.initElements(webDriverFacade.getWebDriver(), CatalogPage.class);
    }

    public CheckoutPage goToCheckout() {
        Actions action = new Actions(webDriverFacade.getWebDriver());
        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", shoppingCartButton);
        action.moveToElement(shoppingCartButton);
        webDriverFacade.waitForLoaderInvisibility();
        webDriverFacade.waitForElementToBeClickable(shoppingCartButton);
        shoppingCartButton.click();

        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
        action.moveToElement(checkoutButton);
        webDriverFacade.waitForElementToBeClickable(checkoutButton);
        checkoutButton.click();

        return PageFactory.initElements(webDriverFacade.getWebDriver(), CheckoutPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodPage() {
        paymentMethodsButton = webDriverFacade.waitForElementVisibility(paymentMethodsButton);
        paymentMethodsButton.click();
        return PageFactory.initElements(webDriverFacade.getWebDriver(), PaymentMethodsPage.class);
    }

    public OrdersPage goToOrdersPage() {
        ordersButton = webDriverFacade.waitForElementVisibility(ordersButton);
        ordersButton.click();
        return PageFactory.initElements(webDriverFacade.getWebDriver(), OrdersPage.class);
    }
}