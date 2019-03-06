import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class EcoFoodTest {
    WebDriver webDriver;
    HomePage homePage;

    @Test
    public void successfulRegistration(){
        RegistrationPage registrationPage = homePage.clickRegisterButton();
        registrationPage.getTitle();
        assertThat("The Registration button does not redirect to the Registrar Usuario page", registrationPage.getTitle().equals("Registrar Usuario"));
    }

    @Before
    public void setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://ecofoodmarket.herokuapp.com/");
       // webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }
    @After
    public void after(){
        webDriver.quit();
    }
}
