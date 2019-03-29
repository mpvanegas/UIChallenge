package runners;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.CheckoutPage;
import pages.OrdersPage;
import utils.Configs;
import utils.Hooks;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class OrdersFulfillmentTest extends Hooks {

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
