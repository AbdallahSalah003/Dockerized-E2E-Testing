package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
	private static volatile RegisterPage instance;
	private RegisterPage(WebDriver driver) {
		super(driver);
	}
	public static RegisterPage getInstance(WebDriver driver) {
		if(instance == null) {
			synchronized (RegisterPage.class) {
				if(instance == null) {
					instance = new RegisterPage(driver);
				}
			}
		}
		return instance;
	}
	@FindBy(id="gender-male")
	WebElement genderMale;
	@FindBy(id="gender-female")
	WebElement genderFemale;
	@FindBy(id="FirstName")
	WebElement firstNameInputField;
	@FindBy(id="LastName")
	WebElement lastNameInputField;
	@FindBy(id="Email")
	WebElement emailInputField;
	@FindBy(id="Password")
	WebElement passwordInputField;
	@FindBy(id="ConfirmPassword")
	WebElement confirmPasswordInputField;
	@FindBy(id="register-button")
	WebElement registerButton;
	@FindBy(xpath="//div[@class='result']")
	WebElement registerResultMessage;
	@FindBy(css=".button-1.register-continue-button")
	WebElement registerContinueButton;
	public void chooseGender(String gender) {
		if(gender.equalsIgnoreCase("male")) genderMale.click();
		else genderFemale.click();
	}
	public void enterFirstName(String firstName) {
		firstNameInputField.sendKeys(firstName);
	}
	public void enterLastName(String lastName) {
		lastNameInputField.sendKeys(lastName);
	}
	public void enterEmail(String email) {
		emailInputField.sendKeys(email);
	}
	public void enterPassword(String password) {
		passwordInputField.sendKeys(password);
	}
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordInputField.sendKeys(confirmPassword);
	}
	public void clickRegisterButton() {
		registerButton.click();
	}
	public boolean verifyRegisterAttempt() {
		return registerResultMessage.getText().contains("Your registration completed");
	}
	public void clickContinueAfterRegister() {
		registerContinueButton.click();
	}

}
