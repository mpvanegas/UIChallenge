package runners;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.*;
import utils.Configs;
import utils.GenerateData;
import utils.Hooks;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class EcoFoodTest extends Hooks {

    @Test
    public void successfulRegistration(){
        RegistrationPage registrationPage = homePage.goToRegisterPage();
        String name = GenerateData.getRandomAlphabetic();
        String lastName = GenerateData.getRandomAlphabetic();
        String username = GenerateData.getRandomAlphaNumeric();
        String email = GenerateData.getRandomEmail();
        String password = GenerateData.getRandomAlphaNumeric();
        registrationPage.fillAndSubmitForm(name, lastName, username, email, password, password);
        assertThat("The message on the alert is not correct. Maybe the registration was not successful or the user already exists",registrationPage.checkAlert().equals("Usuario creado"));
    }

    @Test
    public void successfulLogin(){
        commonFlows.Login();
        assertThat("The user is not able to log in. In the home page there is no button to logout",homePage.getLogout().equals("Logout"));
    }

    @Test
    public void unsuccessfulLogin(){
        LoginPage loginPage = homePage.goToLoginPage();
        String username = Configs.getValueByKey("username");
        String password = GenerateData.getRandomAlphaNumeric();
        loginPage.fillAndSubmitForm(username, password);
        assertThat("The message on the alert is not correct.",loginPage.checkMessage().equals("Usuario y/o Contraseña incorrectos."));
    }

    @Test
    public void logout(){
        commonFlows.Login();
        homePage.goToLogout();
        assertThat("The Logout button does not log out the user. The logout button is still on the page",homePage.getLogin().equals("Iniciar sesión"));
    }

    @Test
    public void addProductsFromHomeAsAuthUser(){
        commonFlows.Login();
        homePage.addProductsFromHome();
        assertThat("The number of products added is not correct",homePage.getTotProductsAdded(), Matchers.equalTo(homePage.getNumProducts()));
    }

    @Test
    public void addProductsFromCatalogAsAuthUser(){
        commonFlows.Login();
        CatalogPage catalogPage = homePage.goToCatalogPage();
        catalogPage.addFruits();
        catalogPage.addVegetables();
        assertThat("The number of products added is not correct",catalogPage.getTotProductsAdded(), Matchers.equalTo(catalogPage.getTotNumProducts()));
    }

    @Test
    public void verifyShoppingCartRemainsWhenUnauth() {
        homePage.addProductsFromHome();
        String addedProdsBeforeAuth = homePage.getTotProductsAdded();
        commonFlows.Login();
        String addedProdsAfterAuth = homePage.getTotProductsAdded();
        assertThat("The number of products added is not correct",addedProdsBeforeAuth, Matchers.equalTo(addedProdsAfterAuth));
    }

    @Test
    public void addAllStockOfProdToCartAsAuthUser(){
        commonFlows.Login();
        String stock = homePage.addAllStockOfRandomProduct();
        assertThat("The product can not be added to full stock",homePage.getTotProductsAdded(),Matchers.equalTo(stock));
    }

    @Test
    public void decreaseQuantityOfProdsAsAuthUser(){
        commonFlows.Login();
        WebElement product = homePage.addRandomProduct();

        String quantityBeforeDecrease = homePage.getProdQuantityAdded(product);
        String quantityDecreased = homePage.decreaseQuantityOfProduct(product);
        String quantityAfterDecrease = homePage.getProdQuantityAdded(product);

        String expectedResult = String.valueOf(Integer.valueOf(quantityBeforeDecrease) - Integer.valueOf(quantityDecreased));
        if (expectedResult.equals("0")) {
            expectedResult = "";
        }

        assertThat("The product does not decrease the quantity of a product as expected", quantityAfterDecrease, Matchers.equalTo(expectedResult));
    }

    @Test
    public void emptyCartAsAuthUser() {
        commonFlows.Login();
        homePage.addRandomProduct();
        homePage.addRandomProduct();

        homePage.emptyCart();
        String quantityAfterEmpty = homePage.getTotProductsAdded();

        assertThat("The cart is not empty after removing all the products", quantityAfterEmpty, Matchers.equalTo("0"));
    }

    @Test
    public void verifyProdDetAsAuthUser() {
        commonFlows.Login();
        WebElement product = homePage.getRandomProd();
        String prodName = homePage.getProdName(product);
        String messageTitle = homePage.goToViewDetails(product);
        assertThat("The details shown do not correspond to the product selected", messageTitle, Matchers.equalTo(prodName));
    }

    @Test
    public void successfulCheckoutWithUnregPaymentMethod() {
        commonFlows.Login();
        homePage.addRandomProduct();
        CheckoutPage checkoutPage = homePage.goToCheckout();

        String address = Configs.getValueByKey("address");
        String zip = Configs.getValueByKey("zip");
        String phone = Configs.getValueByKey("phone");

        String cardNumber = Configs.getValueByKey("cardNumber");
        String expDate = Configs.getValueByKey("expDate");
        String code = Configs.getValueByKey("code");

        checkoutPage.fillAndSubmitPersonalInfo(address, zip, phone);
        checkoutPage.fillAndSubmitCardInfo(cardNumber, expDate, code);
        String actualMessage = checkoutPage.checkMessage();
        assertThat("The checkout was not successful using an unregistered payment method", actualMessage, Matchers.equalTo("Compra Realizada"));
    }

    @Test
    public void successfulCheckoutWithRegPaymentMethod() {
        commonFlows.Login();
        homePage.addRandomProduct();

        PaymentMethodsPage paymentMethodsPage = homePage.goToPaymentMethodPage();
        String cardNumber = Configs.getValueByKey("cardNumber");
        String expDate = Configs.getValueByKey("expDate");
        String code = Configs.getValueByKey("code");
        paymentMethodsPage.addPaymentMethod(cardNumber, expDate, code);

        CheckoutPage checkoutPage = paymentMethodsPage.goToCheckout();
        String address = Configs.getValueByKey("address");
        String zip = Configs.getValueByKey("zip");
        String phone = Configs.getValueByKey("phone");
        checkoutPage.fillAndSubmitPersonalInfo(address, zip, phone);
        checkoutPage.selectPaymentMethod();
        String actualMessage = checkoutPage.checkMessage();
        assertThat("The checkout was not successful using a registered payment method", actualMessage, Matchers.equalTo("Compra Realizada"));
    }

    @Test
    public void verifyOrderDetails() {
        commonFlows.Login();
        WebElement product1 = homePage.addRandomProduct();
        String name1 = homePage.getProdName(product1);
        String quantity1 = homePage.getProdQuantityAdded(product1);
        String price1 = homePage.getProdPrice(product1);

        WebElement product2 = homePage.addRandomProduct();
        String name2 = homePage.getProdName(product2);
        String quantity2 = homePage.getProdQuantityAdded(product2);
        String price2 = homePage.getProdPrice(product2);

        CheckoutPage checkoutPage = homePage.goToCheckout();
        String address = Configs.getValueByKey("address");
        String zip = Configs.getValueByKey("zip");
        String phone = Configs.getValueByKey("phone");
        checkoutPage.fillAndSubmitPersonalInfo(address, zip, phone);
        String cardNumber = Configs.getValueByKey("cardNumber");
        String expDate = Configs.getValueByKey("expDate");
        String code = Configs.getValueByKey("code");
        checkoutPage.fillAndSubmitCardInfo(cardNumber, expDate, code);
        checkoutPage.checkMessage();

        OrdersPage ordersPage = homePage.goToOrdersPage();
        WebElement lastOrder = ordersPage.getLastOrder();
        ordersPage.goToDetails(lastOrder);

        WebElement message = ordersPage.getMessage();
        List<String> names = ordersPage.productDetails(message, "NAME");
        List<String> quantities = ordersPage.productDetails(message, "QUANTITY");
        List<String> prices = ordersPage.productDetails(message, "PRICE");

        assertThat("A product name is missing or does not match", names, Matchers.hasItem(name1));
        assertThat("A product name is missing or does not match", names, Matchers.hasItem(name2));
        assertThat("A product quantity is missing or does not match", quantities, Matchers.hasItem(quantity1));
        assertThat("A product quantity is missing or does not match", quantities, Matchers.hasItem(quantity2));
        assertThat("A product price is missing or does not match", prices, Matchers.hasItem(price1));
        assertThat("A product price is missing or does not match", prices, Matchers.hasItem(price2));
    }
}