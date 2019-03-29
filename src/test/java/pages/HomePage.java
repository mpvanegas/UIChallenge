package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends InteractWithProductPage {

    @FindBy(xpath = "//*[@id='myModal']//div[@class='modal-content']")
    private WebElement message;

    private String productXpath = "//div[@class='col-md-3 col-xs-6 product-1 miso-prd-holder']";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getLogout() {
        logoutButton = webDriverFacade.waitForElementVisibility(logoutButton);
        return logoutButton.getText();
    }

    public String getLogin() {
        loginButton = webDriverFacade.waitForElementVisibility(loginButton);
        return loginButton.getText();
    }

    public String getNumProducts(){
        return String.valueOf(productCarousel.findElements(By.xpath(productXpath)).size());
    }

    public void addProductsFromHome() {
        addProducts(productCarousel, productXpath);
    }

    public String goToViewDetails(WebElement product) {
        if (getProdName(product).startsWith("Canasta")) {
            ComplexProductsPage detailsPage = PageFactory.initElements(webDriverFacade.getWebDriver(), ComplexProductsPage.class);
            return goToViewDetailsComplexProd(product);
        } else {
            return goToViewDetailsNormProd(message, product);
        }
    }
}
