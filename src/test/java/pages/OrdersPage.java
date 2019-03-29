package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrdersPage extends BasePage {

    @FindBy(xpath = "//tbody[@id='orders']/tr")
    private WebElement table;

    @FindBy(xpath = "//tbody[@id='orders']")
    private WebElement orders;

    @FindBy(xpath = "//div[@id='details']/div[@class='modal-dialog modal-lg']")
    private WebElement message;

    @FindBy(xpath = "//button[@class='btn btn-default'][contains(text(),'Close')]")
    private WebElement messageButton;

    private By ordersBy = By.xpath("./tr");
    private By numOrderBy = By.xpath("./td");
    private By viewDetailsButtonBy = By.xpath(".//a[@data-target='#details']");
    private By totProdsBy = By.xpath("//tbody[@id='products']/tr");

    public OrdersPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLastOrder() {
        SortedSet<Integer> numOrderList = new TreeSet<>();

        webDriverFacade.waitForLoaderInvisibility();
        webDriverFacade.waitForElementToBeVisible(table);

        int totOrders = orders.findElements(ordersBy).size();

        for (int i = 1; i <= totOrders; i++) {
            WebElement order = orders.findElements(ordersBy).get(i - 1);
            numOrderList.add(Integer.valueOf(order.findElement(numOrderBy).getText()));
        }
        int lastOrder = numOrderList.last();

        By orderBy = By.xpath("./tr[td[contains(text()," + lastOrder + ")]]");

        return orders.findElement(orderBy);
    }

    public void goToDetails(WebElement order) {
        Actions action = new Actions(webDriverFacade.getWebDriver());
        webDriverFacade.waitForLoaderInvisibility();

        WebElement viewDetailsButton = order.findElement(viewDetailsButtonBy);
        ((JavascriptExecutor) webDriverFacade.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", viewDetailsButton);
        action.moveToElement(viewDetailsButton);
        webDriverFacade.waitForElementToBeClickable(viewDetailsButton);
        viewDetailsButton.click();
    }

    public WebElement getMessage() {
        webDriverFacade.waitForElementToBeVisible(message);
        return message;
    }

    public List<String> productDetails(WebElement message, String dataToView) {
        List<String> prodsDetails = new ArrayList<>();
        int dataToViewIndex;
        if (dataToView.equals("NAME")) {
            dataToViewIndex = 1;
        } else if (dataToView.equals("QUANTITY")) {
            dataToViewIndex = 2;
        } else {
            dataToViewIndex = 3;
        }

        int totProds = message.findElements(totProdsBy).size();
        for (int i = 1; i <= totProds; i++) {
            By prodDetailsBy = By.xpath(".//tbody[@id='products']/tr[" + i + "]");
            WebElement prodDetails = message.findElement(prodDetailsBy);

            By prodDetailsInfoBy = By.xpath(".//tbody[@id='products']/tr[" + i + "]/td[" + dataToViewIndex + "]");
            prodsDetails.add(message.findElement(prodDetailsInfoBy).getText());
            if (dataToViewIndex == 3) {
                prodsDetails.set(i - 1, prodsDetails.get(i - 1).replace(".00", ""));
                prodsDetails.set(i - 1, prodsDetails.get(i - 1).replaceAll("[^\\d]", ""));
            }
        }
        return prodsDetails;
    }
}
