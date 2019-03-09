package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class ModifyProduct {

    public static void addProducts(WebDriver webDriver, BasePage page, WebElement productCarousel, String productXpath) {
        Actions action = new Actions(webDriver);
        page.waitForLoaderInvisibility();
        Boolean match = true;
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
}
