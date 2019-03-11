package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ModifyProduct;


public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Registrarse')]")
    WebElement registerButton;

    @FindBy(xpath = "//*[@class='header-login']/a[text()[contains(string(),'I')]]")
    WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutButton;

    @FindBy(xpath = "//div[@id='products-carousel']")
    WebElement productCarousel;

    @FindBy(xpath = "//div[@id='js-navbar']/div[@class='container']/div[@class='nav-1 nav-2']//span[@class='totals']")
    WebElement cartAddedProds;

    @FindBy(xpath = "//div[@class='container']//a[@class='has-text-color-hover'][contains(text(),'CATÁLOGO')]")
    WebElement catalogButton;

    String productXpath = "//div[@class='col-md-3 col-xs-6 product-1 miso-prd-holder']";

    WebDriverWait wait;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        //    wait = new WebDriverWait(webDriver, 10);
    }

    public RegistrationPage goToRegisterPage() {

        registerButton = waitForElementVisibility(registerButton);
        registerButton.click();

        return PageFactory.initElements(webDriver, RegistrationPage.class);
    }

    public LoginPage goToLoginPage() {

        loginButton = waitForElementVisibility(loginButton);
        loginButton.click();

        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    public String getLogout() {
        logoutButton = waitForElementVisibility(logoutButton);
        return logoutButton.getText();
    }

    public HomePage goToLogout() {
        logoutButton = waitForElementVisibility(logoutButton);
        logoutButton.click();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

    public String getLogin() {
        loginButton = waitForElementVisibility(loginButton);
        return loginButton.getText();
    }

    public String getNumProducts(){
        return String.valueOf(productCarousel.findElements(By.xpath(productXpath)).size());
    }

    public void addProducts() {
        ModifyProduct.addProducts(webDriver,this,productCarousel,productXpath);
    }

    public String getTotProductsAdded() {
        return ModifyProduct.getTotProductsAdded(webDriver,this,cartAddedProds);
    }

    public CatalogPage goToCatalogPage() {
        catalogButton = waitForElementVisibility(catalogButton);
        catalogButton.click();
        return PageFactory.initElements(webDriver, CatalogPage.class);
    }

    public String addAllStockOfRandomProduct(){
        return ModifyProduct.addAllStockOfRandomProduct(webDriver,this,productCarousel,productXpath);
    }

    public String decreaseQuantityOfProduct(){
        return ModifyProduct.decreaseQuantityOfProduct(webDriver,this,productCarousel,productXpath);
    }
}
