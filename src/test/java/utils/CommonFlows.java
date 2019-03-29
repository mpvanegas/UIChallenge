package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.LoginPage;

public class CommonFlows {

    WebDriver webDriver;
    HomePage homePage;

    public CommonFlows(WebDriver webDriver) {
        this.webDriver = webDriver;
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    public HomePage Login() {
        LoginPage loginPage = homePage.goToLoginPage();
        String username = Configs.getValueByKey("username");
        String password = Configs.getValueByKey("password");
        return loginPage.fillAndSubmitForm(username, password);
    }
}
