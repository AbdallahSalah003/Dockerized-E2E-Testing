package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage extends BasePage {
    private static volatile ShoppingCartPage instance;
    private ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    public static ShoppingCartPage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (ShoppingCartPage.class) {
                if(instance == null) {
                    instance = new ShoppingCartPage(driver);
                }
            }
        }
        return instance;
    }
    @FindBy(id="termsofservice")
	WebElement termsOfServiceCheckbox;
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	public void checkTermsOfServiceCheckbox() {
		termsOfServiceCheckbox.click();
	}
	public void clickCheckoutBtn() {
		checkoutBtn.click();
	}
}
