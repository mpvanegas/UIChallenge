import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CatalogPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class EcoFoodTest {
    WebDriver webDriver;
    HomePage homePage;

    @Test
    public void successfulRegistration(){
        RegistrationPage registrationPage = homePage.goToRegisterPage();
        assertThat("The Registration button does not redirect to the Registrar Usuario page", registrationPage.getTitle().equals("Registrar Usuario"));
        registrationPage.fillAndSubmitForm("Paula","Vanegas","pvanegas","pvanegas@mail.com","pass","pass");
        assertThat("The message on the alert is not correct. Maybe the registration was not successful or the user already exists",registrationPage.checkAlert().equals("Usuario creado"));
    }

    @Test
    public void successfulLogin(){
        LoginPage loginPage = homePage.goToLoginPage();
        assertThat("The Log in button does not redirect to the Inicia Sesión page", loginPage.getTitle().equals("Inicia Sesión"));
        HomePage homePage = loginPage.fillAndSubmitForm("pvanegas","pass");
        assertThat("The user is not able to log in. In the home page there is no button to logout",homePage.getLogout().equals("Logout"));
    }

    @Test
    public void unsuccessfulLogin(){
        LoginPage loginPage = homePage.goToLoginPage();
        assertThat("The Log in button does not redirect to the Inicia Sesión page", loginPage.getTitle().equals("Inicia Sesión"));
        loginPage.fillAndSubmitForm("pvanegas","passs");
        assertThat("The message on the alert is not correct.",loginPage.checkMessage().equals("Usuario y/o Contraseña incorrectos."));
    }

    @Test
    public void logout(){
        successfulLogin();
        homePage.goToLogout();
        assertThat("The Logout button does not log out the user. The logout button is still on the page",homePage.getLogin().equals("Iniciar sesión"));
    }

    @Test
    public void addProductsFromHomeAsAuthUser(){
        successfulLogin();
        homePage.addProducts();
        assertThat("The number of products added is not correct",homePage.getTotProductsAdded(), Matchers.equalTo(homePage.getNumProducts()));
    }

    @Test
    public void addProductsFromCatalogAsAuthUser(){
        successfulLogin();
        CatalogPage catalogPage = homePage.goToCatalogPage();
        assertThat("The Log in button does not redirect to the Inicia Sesión page", catalogPage.getTitle().equals("Nuestros productos"));
        catalogPage.addFruits();
        catalogPage.addVegetables();
        assertThat("The number of products added is not correct",catalogPage.getTotProductsAdded(), Matchers.equalTo(catalogPage.getTotNumProducts()));
    }

    @Test
    public void verifyShoppingCartRemainsWhenUnauthUser(){
        homePage.addProducts();
        String addedProdsBeforeAuth = homePage.getTotProductsAdded();
        successfulLogin();
        String addedProdsAfterAuth = homePage.getTotProductsAdded();
        assertThat("The number of products added is not correct",addedProdsBeforeAuth, Matchers.equalTo(addedProdsAfterAuth));
    }

    @Test
    public void addAllStockOfProdToCartAsAuthUser(){
        successfulLogin();
        String stock = homePage.addAllStockOfRandomProduct();
        assertThat("The product can not be added to full stock",homePage.getTotProductsAdded(),Matchers.equalTo(stock));
    }

    @Test
    public void decreaseQuantityOfProdsAsAuthUser(){
        successfulLogin();
        homePage.addProducts();
        homePage.addProducts();
        homePage.addProducts();
        String quantityAdded = homePage.decreaseQuantityOfProduct();
        assertThat("The product does not decrease the quantity of a product as expected",homePage.decreaseQuantityOfProduct(),Matchers.equalTo("2"));
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
