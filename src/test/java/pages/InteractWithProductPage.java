package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class InteractWithProductPage extends BasePage {

    @FindBy(xpath = "//span[@class='totals']")
    protected WebElement cartAddedProds;

    @FindBy(xpath = "//div[@id='products-carousel']")
    protected WebElement productCarousel;

    protected String productXpath = ".//div[@class='col-md-3 col-xs-6 product-1 miso-prd-holder']";
    protected By addButtonBy = By.xpath(".//div[@class='action-item miso-cart-plus']/a");
    protected By stockBy = By.xpath(".//div[@class='miso-prd-total']");
    protected By productQuantityBy = By.xpath(".//div[@class='miso-prd-qty']");
    protected By minusButtonBy = By.xpath(".//div[@class='action-item miso-cart-minus']/a");
    protected By deleteButtonBy = By.xpath(".//div[@class='action-item miso-cart-clear']/a");
    protected By prodNameBy = By.xpath(".//a[@class='name']");
    protected By prodPriceBy = By.xpath(".//p[@class='price']");
    protected By detailsButtonBy = By.xpath(".//div[@class='action-item']/a");
    protected By messageTitleBy = By.xpath(".//div[@class='product-name']");
    protected By messageCloseButtonBy = By.xpath(".//button[@class='fa fa-close']");
    protected By goBackButtonBy = By.xpath("//button[@class='au-btn au-btn-radius au-btn-primary']");

    public InteractWithProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addProducts(WebElement productCarousel, String productXpath) {
        Actions action = new Actions(webDriverFacade.getWebDriver());
        webDriverFacade.waitForElementToBeVisible(productCarousel);
        webDriverFacade.waitForLoaderInvisibility();

        for (int i = 0; i < productCarousel.findElements(By.xpath(productXpath)).size(); i++) {
            WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + (i + 1) + "]"));
            WebElement addButton = product.findElement(addButtonBy);

            ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
            action.moveToElement(product).perform();
            webDriverFacade.waitForElementToBeVisible(addButton);
            addButton.click();
        }
    }

    public String getTotProductsAdded() {
        cartAddedProds = webDriverFacade.waitForElementVisibility(cartAddedProds);
        return cartAddedProds.getText();
    }

    public String addAllStockOfRandomProduct() {
        Random rand = new Random();
        Actions action = new Actions(webDriverFacade.getWebDriver());

        webDriverFacade.waitForLoaderInvisibility();
        webDriverFacade.waitForElementToBeVisible(productCarousel);
        webDriverFacade.waitForLoaderInvisibility();

        if (productCarousel.findElements(By.xpath(productXpath)).size() == 0) {
            return "0";
        } else {
            int index = rand.nextInt(productCarousel.findElements(By.xpath(productXpath)).size()) + 1;

            WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + index + "]"));
            ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
            action.moveToElement(product).perform();

            WebElement addButton = product.findElement(addButtonBy);

            webDriverFacade.waitForElementToBeVisible(addButton);
            WebElement stock = product.findElement(stockBy);

            for (int i = 1; i <= Integer.parseInt(stock.getText()); i++) {
                addButton.click();
            }
            ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", productCarousel);
            action.moveToElement(productCarousel).perform();
            ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
            action.moveToElement(product).perform();

            webDriverFacade.waitForElementToBeVisible(addButton);

            return stock.getText();
        }
    }

    public WebElement addRandomProduct() {
        Random rand = new Random();
        Actions action = new Actions(webDriverFacade.getWebDriver());

        webDriverFacade.waitForElementToBeVisible(productCarousel);
        webDriverFacade.waitForLoaderInvisibility();
        webDriverFacade.waitForElementToBeVisible(productCarousel.findElement(By.xpath(productXpath)));

        int index = rand.nextInt(productCarousel.findElements(By.xpath(productXpath)).size()) + 1;

        WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + index + "]"));
        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
        action.moveToElement(product).perform();
        WebElement addButton = product.findElement(addButtonBy);

        webDriverFacade.waitForElementToBeVisible(addButton);

        // String stock = product.findElement(By.xpath(".//div[@class='miso-prd-total']")).getText();
        String stock = "2";
        int quantityToAdd = rand.nextInt(Integer.valueOf(stock)) + 1;

        for (int i = 1; i <= quantityToAdd & i <= Integer.valueOf(stock); i++) {
            addButton.click();
        }
        return product;
    }

    public String getProdQuantityAdded(WebElement product) {
        return product.findElement(productQuantityBy).getText();
    }

    public String decreaseQuantityOfProduct(WebElement product) {
        Random rand = new Random();
        Actions action = new Actions(webDriverFacade.getWebDriver());

        webDriverFacade.waitForLoaderInvisibility();

        String quantityAdded = product.findElement(productQuantityBy).getText();

        int quantityToDecrease = rand.nextInt(Integer.valueOf(quantityAdded)) + 1;

        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
        action.moveToElement(product).perform();

        WebElement decreaseButton = product.findElement(minusButtonBy);

        webDriverFacade.waitForElementToBeVisible(decreaseButton);

        for (int i = 1; i <= quantityToDecrease; i++) {
            decreaseButton.click();
        }
        return String.valueOf(quantityToDecrease);
    }

    public void emptyCart() {
        Actions action = new Actions(webDriverFacade.getWebDriver());
        webDriverFacade.waitForLoaderInvisibility();
        for (int i = 1; i <= productCarousel.findElements(By.xpath(productXpath)).size(); i++) {

            WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + i + "]"));

            ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
            action.moveToElement(product).perform();
            String quantityAdded = getProdQuantityAdded(product);

            if (!quantityAdded.equals("")) {
                WebElement deleteButton = product.findElement(deleteButtonBy);

                ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
                action.moveToElement(product).perform();

                webDriverFacade.waitForElementToBeVisible(deleteButton);

                deleteButton.click();
            }
        }
    }

    public WebElement getRandomProd() {
        Random rand = new Random();
        webDriverFacade.waitForElementToBeVisible(productCarousel);
        webDriverFacade.waitForLoaderInvisibility();
        int index = rand.nextInt(productCarousel.findElements(By.xpath(productXpath)).size()) + 1;
        return productCarousel.findElement(By.xpath(productXpath + "[" + index + "]"));
    }

    public String getProdName(WebElement product) {
        Actions action = new Actions(webDriverFacade.getWebDriver());

        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
        action.moveToElement(product).perform();
        String prodName = product.findElement(prodNameBy).getText();
        return prodName;
    }

    public String getProdPrice(WebElement product) {
        Actions action = new Actions(webDriverFacade.getWebDriver());

        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
        action.moveToElement(product).perform();
        String prodPrice = product.findElement(prodPriceBy).getText();
        return prodPrice.replaceAll("[^\\d]", "");
    }

    public String goToViewDetailsNormProd(WebElement message, WebElement product) {
        Actions action = new Actions(webDriverFacade.getWebDriver());

        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
        action.moveToElement(product).perform();

        webDriverFacade.waitForElementToBeVisible(product);

        WebElement detailsButton = product.findElement(detailsButtonBy);

        webDriverFacade.waitForElementToBeClickable(detailsButton);
        webDriverFacade.waitForElementToBeVisible(detailsButton);

        detailsButton.click();

        webDriverFacade.waitForElementToBeVisible(message);

        String messageTitle = message.findElement(messageTitleBy).getText();

        WebElement closeButton = message.findElement(messageCloseButtonBy);

        webDriverFacade.waitForElementToBeClickable(closeButton);


        closeButton.click();
        return messageTitle;
    }

    public String goToViewDetailsComplexProd(WebElement product) {

        Actions action = new Actions(webDriverFacade.getWebDriver());

        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", product);
        action.moveToElement(product).perform();

        webDriverFacade.waitForElementToBeVisible(product);

        WebElement detailsButton = product.findElement(detailsButtonBy);

        webDriverFacade.waitForElementToBeClickable(detailsButton);

        detailsButton.click();
        webDriverFacade.waitForLoaderInvisibility();

        WebElement goBackButton = webDriverFacade.getWebDriver().findElement(goBackButtonBy);
        webDriverFacade.waitForElementToBeClickable(goBackButton);

        String pageTitle = webDriverFacade.getWebDriver().getTitle();
        goBackButton.click();
        return pageTitle;
    }
}
