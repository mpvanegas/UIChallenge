package runners;

import org.junit.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.Configs;
import utils.GenerateData;
import utils.Hooks;

import static org.hamcrest.MatcherAssert.assertThat;

public class UsersAccountTest extends Hooks {

    @Test
    public void successfulRegistration() {
        RegistrationPage registrationPage = homePage.goToRegisterPage();
        String name = GenerateData.getRandomAlphabetic();
        String lastName = GenerateData.getRandomAlphabetic();
        String username = GenerateData.getRandomAlphaNumeric();
        String email = GenerateData.getRandomEmail();
        String password = GenerateData.getRandomAlphaNumeric();
        registrationPage.fillAndSubmitForm(name, lastName, username, email, password, password);
        assertThat("The message on the alert is not correct. Maybe the registration was not successful or the user already exists", registrationPage.checkAlert().equals("Usuario creado"));
    }

    @Test
    public void successfulLogin() {
        commonFlows.Login();
        assertThat("The user is not able to log in. In the home page there is no button to logout", homePage.getLogout().equals("Logout"));
    }

    @Test
    public void unsuccessfulLogin() {
        LoginPage loginPage = homePage.goToLoginPage();
        String username = Configs.getValueByKey("username");
        String password = GenerateData.getRandomAlphaNumeric();
        loginPage.fillAndSubmitForm(username, password);
        assertThat("The message on the alert is not correct.", loginPage.checkMessage().equals("Usuario y/o Contraseña incorrectos."));
    }

    @Test
    public void logout() {
        commonFlows.Login();
        homePage.goToLogout();
        assertThat("The Logout button does not log out the user. The logout button is still on the page", homePage.getLogin().equals("Iniciar sesión"));
    }
}
