package drivers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver webDriver;

    protected abstract void createDriver();

    public void quitDriver() {
        webDriver.quit();
    }

    public WebDriver createWebDriver() {
        createDriver();
        return webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
