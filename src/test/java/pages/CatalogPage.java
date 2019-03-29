package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends InteractWithProductPage {

    @FindBy(xpath = "//div[@class='heading-page-1']/h3")
    private WebElement pageTitle;

    @FindBy(xpath = "//ul[@id='product-category-list']//a[contains(text(),'Frutas')]")
    private WebElement fruitsButton;

    @FindBy(xpath = "//ul[@id='product-category-list']//a[contains(text(),'Verduras')]")
    private WebElement vegetablesButton;

    @FindBy(xpath = "//div[@id='products_by_category']")
    private WebElement productCarousel;

    @FindBy(xpath = "//div[@id='js-navbar']/div[@class='container']/div[@class='nav-1 nav-2']//span[@class='totals']")
    private WebElement cartAddedProds;

    @FindBy(id = "category_name")
    private WebElement categoryName;


    private String productXpath = "//div[@class='col-md-4 col-sm-6 product-1 miso-prd-holder']";
    private String totNumProducts = "0";

    public CatalogPage(WebDriver webDriver){
        super(webDriver);
    }

    public String getTitle(){
        return pageTitle.getText();
    }

    public void addFruits(){
        webDriverFacade.waitForElementVisibility(fruitsButton);
        fruitsButton.click();
        webDriverFacade.waitForLoaderInvisibility();

        addProducts(productCarousel, productXpath);

        totNumProducts = String.valueOf(Integer.parseInt(totNumProducts) + Integer.parseInt(getNumProducts()));
    }

    public void addVegetables() {
        webDriverFacade.waitForElementVisibility(vegetablesButton);

        Actions action = new Actions(webDriverFacade.getWebDriver());
        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", categoryName);
        action.moveToElement(vegetablesButton).perform();

        vegetablesButton.click();
        webDriverFacade.waitForLoaderInvisibility();

        addProducts(productCarousel, productXpath);

        totNumProducts = String.valueOf(Integer.parseInt(totNumProducts) + Integer.parseInt(getNumProducts()));
    }

    public String getNumProducts(){
        return String.valueOf(productCarousel.findElements(By.xpath(productXpath)).size());
    }

    public String getTotNumProducts(){
        return totNumProducts;
    }
}
