package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	private static volatile MyAccountPage instance;
	private MyAccountPage(WebDriver driver) {
		super(driver);
	}
	public static MyAccountPage getInstance(WebDriver driver) {
		if(instance == null) {
			synchronized (MyAccountPage.class) {
				if(instance == null) {
					instance = new MyAccountPage(driver);
				}
			}
		}
		return instance;
	}
	@FindBy(linkText="Orders")
	WebElement ordersLnk;
	public void clickOrdersLnk() {
		wait.until(mydriver->ordersLnk.isEnabled());
		ordersLnk.click();
	}
}
