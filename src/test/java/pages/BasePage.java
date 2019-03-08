package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    @FindBy(xpath = "//div[@class='loader']")
    protected WebElement pageLoader;

    @FindBy(xpath = "//div[@class='modal-body'][contains(text(),'Usuario y/o Contraseña incorrectos.')]")
    protected WebElement message;

    @FindBy(xpath = "//button[@class='btn btn-danger'][contains(text(),'Intentar de Nuevo')]")
    protected WebElement messageButton;

    protected WebDriver webDriver;
    protected Alert alert;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Boolean waitForLoaderInvisibility() {
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']")));
    }

    public WebElement waitForElementVisibility(WebElement element) {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']")));
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(element));
    }

    public String checkAlert() {
        alert = new WebDriverWait(webDriver, 5).until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert();
        String message = alert.getText();
        alert.accept();
        return message;
    }

    public String checkMessage() {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(message));
        String messagee = message.getText();
        messageButton.click();
        return messagee;
    }
}
