package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    private static volatile ProductPage instance;
    private ProductPage(WebDriver driver) {
        super(driver);
    }
    public static ProductPage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (ProductPage.class) {
                if(instance == null) {
                    instance = new ProductPage(driver);
                }
            }
        }
        return instance;
    }
    @FindBy(xpath = "//button[text()='Add to cart']")
    WebElement addToCartBtn;
    @FindBy(css="input.qty-input")
    WebElement qtyInputField;
    @FindBy(css=".content")
    WebElement popUpMessageBox;
	@FindBy(css="span[title='Close']")
	WebElement closeConfirmMsgBtn;
	@FindBy(css="p[class='content'] a")
	WebElement CartLnkAddToCartConfirmMsg;
    public void clickOnAddToCart() {
        addToCartBtn.click();
    }
    public void enterQty(String qty) {
        qtyInputField.sendKeys(qty);
    }
    public boolean verifyAddToCartOperation() {
        return popUpMessageBox.isDisplayed()
			&& popUpMessageBox.getText().contains("The product has been added");
    }
	public void clickOnCloseConfirmMessage() {
		closeConfirmMsgBtn.click();
	}
	public void clickOnShoppingCartFromConfirmMessage() {
		CartLnkAddToCartConfirmMsg.click();
	}
	public boolean verifyOutOfStockMsg() {
		return popUpMessageBox.isDisplayed() && popUpMessageBox.getText().contains("Out of stock");
	}
}
