package runners;

import org.hamcrest.Matchers;
import org.junit.Test;
import pages.CheckoutPage;
import pages.PaymentMethodsPage;
import utils.Configs;
import utils.Hooks;

import static org.hamcrest.MatcherAssert.assertThat;

public class PaymentMethodAdministrationTest extends Hooks {

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
}
