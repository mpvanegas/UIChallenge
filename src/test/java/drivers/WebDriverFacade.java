package drivers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFacade {

    private WebDriver webDriver;
    private Alert alert;

    public WebDriverFacade(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public Boolean waitForLoaderInvisibility() {
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']")));
    }

    public WebElement waitForElementVisibility(WebElement element) {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']")));
        return new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public String checkAlert() {
        alert = new WebDriverWait(webDriver, 5).until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }
}
