package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ModifyProduct;

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
        waitForLoaderInvisibility();

        ModifyProduct.addProducts(webDriver,this,productCarousel,productXpath);

        totNumProducts = String.valueOf(Integer.parseInt(totNumProducts) + Integer.parseInt(getNumProducts()));
    }

    public void addVegetables() {
        waitForElementVisibility(vegetablesButton);
        vegetablesButton.click();
        waitForLoaderInvisibility();

        ModifyProduct.addProducts(webDriver,this,productCarousel,productXpath);

        totNumProducts = String.valueOf(Integer.parseInt(totNumProducts) + Integer.parseInt(getNumProducts()));
    }

    public String getTotProductsAdded() {
        return ModifyProduct.getTotProductsAdded(webDriver,this,cartAddedProds);
    }

    public String getNumProducts(){
        return String.valueOf(productCarousel.findElements(By.xpath(productXpath)).size());
    }

    public String getTotNumProducts(){
        return totNumProducts;
    }
}
