package testCases;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.ScreenShots;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger;
    protected Properties properties;


    @BeforeClass(groups = {"Regression", "Sanity", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException, URISyntaxException {
        FileReader file=new FileReader("./src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());


		if(properties.getProperty("exe_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			switch(os.toLowerCase()) {
				case "linux": capabilities.setPlatform(Platform.LINUX); break;
				case "windows": capabilities.setPlatform(Platform.WINDOWS); break;
				case "mac": capabilities.setPlatform(Platform.MAC); break;
				default:
					System.out.println("Unrecognized operating system: " + os); return;
			}
			switch (browser.toLowerCase()) {
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("Firefox"); break;
				case "chrome": capabilities.setBrowserName("chrome"); break;
				default: System.out.println("Unrecognized browser: " + browser); break;
			}
			driver = new RemoteWebDriver(new URI(properties.getProperty("hubURL")).toURL(), capabilities);
		}
		if(properties.getProperty("exe_env").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
				case "chrome": {
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
					driver = new ChromeDriver(options);
				} break;
				case "edge":  {
					EdgeOptions options = new EdgeOptions();
					options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
					driver = new EdgeDriver(options);
				} break;
				case "firefox": {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("dom.webdriver.enabled", false);
					profile.setPreference("useAutomationExtension", false);
					FirefoxOptions options = new FirefoxOptions();
					options.setProfile(profile);
					driver = new FirefoxDriver();
				} break;
				default: System.out.println("Invalid browser name");return;
			}
		}

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();

    }
    @AfterClass(groups = {"Regression", "Sanity", "Master"})
    public void tearDown() {
        driver.quit();
    }
	@AfterMethod
	public void postTestcaseExecution(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus())
		{
			try
			{
				File screenshot = ScreenShots.takeSnapShot(driver, "./src/test/resources/screenshots/"+result.getMethod().getMethodName()+".png");
				Allure.addAttachment("Page Screenshot", FileUtils.openInputStream(screenshot));
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		}
	}
}
