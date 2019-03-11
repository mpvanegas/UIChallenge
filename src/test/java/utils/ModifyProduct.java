package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.util.Random;

public class ModifyProduct {

    public static void addProducts(WebDriver webDriver, BasePage page, WebElement productCarousel, String productXpath) {
        Actions action = new Actions(webDriver);
        page.waitForLoaderInvisibility();
        for (int i = 0; i < productCarousel.findElements(By.xpath(productXpath)).size(); i++) {
            WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + (i + 1) + "]"));
            WebElement addButton = product.findElement(By.xpath(".//div[@class='action-item miso-cart-plus']/a"));
            action.moveToElement(product).perform();
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(addButton));
            addButton.click();
        }
    }

    public static String getTotProductsAdded(WebDriver webDriver, BasePage page, WebElement cartAddedProds) {
        Actions action = new Actions(webDriver);
        action.moveToElement(cartAddedProds).perform();
        cartAddedProds = page.waitForElementVisibility(cartAddedProds);
        return cartAddedProds.getText();
    }

    public static String addAllStockOfRandomProduct(WebDriver webDriver, BasePage page, WebElement productCarousel, String productXpath) {
        Random rand = new Random();
        Actions action = new Actions(webDriver);

        page.waitForLoaderInvisibility();

        int index = rand.nextInt(productCarousel.findElements(By.xpath(productXpath)).size()) +1;

        WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + index + "]"));
        action.moveToElement(product).perform();

        WebElement addButton = product.findElement(By.xpath(".//div[@class='action-item miso-cart-plus']/a"));
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(addButton));
        WebElement stock = product.findElement(By.xpath(".//div[@class='miso-prd-total']"));

        for(int i = 1; i<=Integer.parseInt(stock.getText()); i++) {
            addButton.click();
        }
        return stock.getText();
    }

    public static String decreaseQuantityOfProduct(WebDriver webDriver, BasePage page, WebElement productCarousel, String productXpath) {
        Random rand = new Random();
        Actions action = new Actions(webDriver);

        page.waitForLoaderInvisibility();

        int index = rand.nextInt(productCarousel.findElements(By.xpath(productXpath)).size()) +1;

        WebElement product = productCarousel.findElement(By.xpath(productXpath + "[" + index + "]"));
        action.moveToElement(product).perform();

        WebElement decreaseButton = product.findElement(By.xpath(".//div[@class='action-item miso-cart-minus']/a"));
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(decreaseButton));
        WebElement quantityAdded = product.findElement(By.xpath(".//div[@class='miso-prd-qty']"));

        decreaseButton.click();

        return quantityAdded.getText();
    }
}