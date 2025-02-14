package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrdersPage extends BasePage {
    private static volatile OrdersPage instance;
    private OrdersPage(WebDriver driver) {
        super(driver);
    }
    public static OrdersPage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (OrdersPage.class) {
                if(instance == null) {
                    instance = new OrdersPage(driver);
                }
            }
        }
        return instance;
    }
    @FindBy(css=".order-item")
    List<WebElement> orders;
    public boolean verifyOrderExists() {
        return !orders.isEmpty();
    }
}
