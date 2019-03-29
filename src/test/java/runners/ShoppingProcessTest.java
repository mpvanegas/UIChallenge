package runners;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.CatalogPage;
import utils.Hooks;

import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingProcessTest extends Hooks {

    @Test
    public void addProductsFromHomeAsAuthUser() {
        commonFlows.Login();
        homePage.addProductsFromHome();
        assertThat("The number of products added is not correct", homePage.getTotProductsAdded(), Matchers.equalTo(homePage.getNumProducts()));
    }

    @Test
    public void addProductsFromCatalogAsAuthUser() {
        commonFlows.Login();
        CatalogPage catalogPage = homePage.goToCatalogPage();
        catalogPage.addFruits();
        catalogPage.addVegetables();
        assertThat("The number of products added is not correct", catalogPage.getTotProductsAdded(), Matchers.equalTo(catalogPage.getTotNumProducts()));
    }

    @Test
    public void verifyShoppingCartRemainsWhenUnauth() {
        homePage.addProductsFromHome();
        String addedProdsBeforeAuth = homePage.getTotProductsAdded();
        commonFlows.Login();
        String addedProdsAfterAuth = homePage.getTotProductsAdded();
        assertThat("The number of products added is not correct", addedProdsBeforeAuth, Matchers.equalTo(addedProdsAfterAuth));
    }

    @Test
    public void addAllStockOfProdToCartAsAuthUser() {
        commonFlows.Login();
        String stock = homePage.addAllStockOfRandomProduct();
        assertThat("The product can not be added to full stock", homePage.getTotProductsAdded(), Matchers.equalTo(stock));
    }

    @Test
    public void decreaseQuantityOfProdsAsAuthUser() {
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
}
