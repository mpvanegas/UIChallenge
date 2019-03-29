package drivers;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void createDriver() {
//        String sauceUserName = System.getenv("SAUCE_USERNAME");
//        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
//        String sauceURL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("username", sauceUserName);
//        capabilities.setCapability("accessKey", sauceAccessKey);
//        capabilities.setCapability("browserName", "Chrome");
//        capabilities.setCapability("platform", "Windows 10");
//        capabilities.setCapability("version", "72.0");
//
//        try {
//            webDriver = new RemoteWebDriver(new URL(sauceURL),capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        webDriver = new ChromeDriver();
    }
}
