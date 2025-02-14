package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private static volatile LoginPage instance;
    private LoginPage(WebDriver driver) {
        super(driver);
    }
    public static LoginPage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (LoginPage.class) {
                if(instance == null) {
                    instance = new LoginPage(driver);
                }
            }
        }
        return instance;
    }
    @FindBy(id="Email")
    WebElement emailInputField;
    @FindBy(id="Password")
    WebElement passwordInputField;
    @FindBy(css="button[class='button-1 login-button']")
    WebElement loginBtn;
    @FindBy(css=".ico-logout")
    WebElement loginVerification;
    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }
    public void enterPassword(String password) {
        passwordInputField.sendKeys(password);
    }
    public void clickLogin() {
        loginBtn.click();
    }
    public boolean verifyLogin() {
        return loginVerification.isDisplayed();
    }
}
