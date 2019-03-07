package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    @FindBy(xpath = "//div[@class='loader']")
    protected WebElement pageLoader;

    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement waitForElementVisibility(WebElement element){
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loader']")));
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(element));
    }
}
