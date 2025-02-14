package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    private static volatile CheckoutPage instance;
    private CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public static CheckoutPage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (CheckoutPage.class) {
                if(instance == null) {
                    instance = new CheckoutPage(driver);
                }
            }
        }
        return instance;
    }
    @FindBy(id="BillingNewAddress_FirstName")
    WebElement firstnameInputField;
    @FindBy(id="BillingNewAddress_LastName")
    WebElement lastnameInputField;
    @FindBy(id="BillingNewAddress_Address1")
    WebElement address1InputField;
    @FindBy(id="BillingNewAddress_City")
    WebElement cityInputField;
    @FindBy(id="BillingNewAddress_CountryId")
    WebElement countryDropdown;
    @FindBy(id="BillingNewAddress_ZipPostalCode")
    WebElement postalCodeInputField;
    @FindBy(id="BillingNewAddress_StateProvinceId")
    WebElement regionDropdown;
	@FindBy(id="BillingNewAddress_PhoneNumber")
	WebElement phoneInputField;
    @FindBy(xpath="(//button[text()='Continue'])[1]")
    WebElement billingAddressContinueBtn;
	@FindBy(xpath = "(//button[text()='Continue'])[3]")
	WebElement shippingMethodContinueBtn;
	@FindBy(xpath = "(//button[text()='Continue'])[4]")
	WebElement paymentMethodContinueBtn;
	@FindBy(xpath="//input[@value='Payments.CheckMoneyOrder']")
	WebElement paymentMethodCheckMoneyOption;
	@FindBy(xpath = "(//button[text()='Continue'])[5]")
	WebElement paymentInfoContinueBtn;
	@FindBy(xpath="//button[normalize-space()='Confirm']")
	WebElement confirmOrderBtn;
	@FindBy(css="div[class='section order-completed'] div[class='title'] strong")
	WebElement confirmOrderMsg;
    public void shippingInfoEnterFirstname(String firstname) {
        firstnameInputField.sendKeys(firstname);
    }
    public void shippingInfoEnterLastname(String lastname) {
        lastnameInputField.sendKeys(lastname);
    }
    public void shippingInfoEnterAddress1(String address1) {
        address1InputField.sendKeys(address1);
    }
    public void shippingInfoEnterCity(String city) {
        cityInputField.sendKeys(city);
    }
    public void shippingInfoEnterPostalCode(String code) {
        postalCodeInputField.sendKeys(code);
    }
    public void shippingInfoSelectCountry(String country) {
        new Select(countryDropdown).selectByVisibleText(country);
    }
    public void shippingInfoSelectRegion(String region) {
        new Select(regionDropdown).selectByVisibleText(region);
    }
    public void shippingInfoEnterPhone(String phone) {phoneInputField.sendKeys(phone);}
    public void clickOnShippingInfoContinueBtn() {
		billingAddressContinueBtn.click();
    }
	public void selectShippingOption(String option) {
		if(option.equals("Ground")) return;
		switch (option) {
			case "NextDayAir": driver.findElement(By.id("shippingoption_1")).click(); break;
			case "SecondDayAir": driver.findElement(By.id("shippingoption_2")).click(); break;
			default: driver.findElement(By.id("shippingoption_0")).click(); break;
		}
	}
	public void clickOnShippingMethodContinueBtn() {
		shippingMethodContinueBtn.click();
	}
	public void selectMoneyOrderPaymentMethod() {
		paymentMethodCheckMoneyOption.click();
		paymentMethodContinueBtn.click();
	}
	public void acceptPaymentTerms() {
		paymentInfoContinueBtn.click();
	}
	public void clickOnConfirmOrderBtn() {
		confirmOrderBtn.click();
	}
	public boolean verifyConfirmOrder() {
		return confirmOrderMsg.isDisplayed()
			&& confirmOrderMsg.getText().contains("Your order has been successfully processed!");
	}
}
