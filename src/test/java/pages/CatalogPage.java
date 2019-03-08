package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage extends BasePage {

    @FindBy(xpath = "//div[@class='heading-page-1']/h3")
    private WebElement pageTitle;

    @FindBy(xpath = "//ul[@id='product-category-list']//a[contains(text(),'Frutas')]")
    private WebElement fruitsButton;

    @FindBy(xpath = "//ul[@id='product-category-list']//a[contains(text(),'Verduras')]")
    private WebElement vegetablesButton;

    @FindBy(xpath = "//div[@id='products_by_category']")
    WebElement productCarousel;

    @FindBy(xpath = "//div[@id='js-navbar']/div[@class='container']/div[@class='nav-1 nav-2']//span[@class='totals']")
    WebElement cartAddedProds;


    String productXpath = "//div[@class='col-md-4 col-sm-6 product-1 miso-prd-holder']";
    String totNumProducts = "0";

    public CatalogPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getTitle(){
        return pageTitle.getText();
    }

    public void addFruits(){
        waitForElementVisibility(fruitsButton);
        fruitsButton.click();
        Actions action = new Actions(webDriver);
        waitForLoaderInvisibility();
        Boolean match = true;
        for (int i = 0; i < productCarousel.findElements(By.xpath(productXpath)).size(); i++) {
            WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + (i + 1) + "]"));
            WebElement addButton = product.findElement(By.xpath(".//div[@class='action-item miso-cart-plus']/a"));
            action.moveToElement(product).perform();
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(addButton));
            addButton.click();
        }
        totNumProducts = String.valueOf(Integer.parseInt(totNumProducts) + Integer.parseInt(getNumProducts()));
    }

    public void addVegetables() {
        waitForElementVisibility(vegetablesButton);
        vegetablesButton.click();
        Actions action = new Actions(webDriver);
        waitForLoaderInvisibility();
        Boolean match = true;
        for (int i = 0; i < productCarousel.findElements(By.xpath(productXpath)).size(); i++) {
            WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + (i + 1) + "]"));
            WebElement addButton = product.findElement(By.xpath(".//div[@class='action-item miso-cart-plus']/a"));
            action.moveToElement(product).perform();
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(addButton));
            addButton.click();
        }
        totNumProducts = String.valueOf(Integer.parseInt(totNumProducts) + Integer.parseInt(getNumProducts()));
    }

    public String getTotProductsAdded() {
        Actions action = new Actions(webDriver);

        action.moveToElement(cartAddedProds).perform();
        cartAddedProds = waitForElementVisibility(cartAddedProds);

        return cartAddedProds.getText();
    }

    public String getNumProducts(){
        return String.valueOf(productCarousel.findElements(By.xpath(productXpath)).size());
    }

    public String getTotNumProducts(){
        return totNumProducts;
    }
}
