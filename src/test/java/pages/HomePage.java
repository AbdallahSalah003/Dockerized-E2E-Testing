package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private static volatile HomePage instance;
    private HomePage(WebDriver driver) {
        super(driver);
    }
    public static HomePage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (HomePage.class) {
                if(instance == null) {
                    instance = new HomePage(driver);
                }
            }
        }
        return instance;
    }
	@FindBy(css=".ico-register")
	WebElement registerLnk;
	@FindBy(css=".ico-login")
	WebElement loginLnk;
    @FindBy(css=".ico-account")
	WebElement myAccountLnk;
	@FindBy(css=".wishlist-label")
	WebElement wishListLnk;
	@FindBy(css=".ico-cart")
	WebElement shoppingCartLnk;
	@FindBy(id="small-searchterms")
	WebElement searchInputField;
	@FindBy(xpath="//button[normalize-space()='Search']")
	WebElement searchButton;
	public void clickOnRegisterLnk() {
		registerLnk.click();
	}
	public void clickOnMyAccountLnk() {
		myAccountLnk.click();
	}
	public void clickOnWishListLnk() {
		wishListLnk.click();
	}
	public void clickOnShoppingCartLnk() {
		shoppingCartLnk.click();
	}
	public void enterSearchInput(String searchInput) {
		searchInputField.sendKeys(searchInput);
	}
	public void clickOnSearchButton() {
		searchButton.click();
	}
	public void clickOnLoginLnk() {loginLnk.click();}
}
