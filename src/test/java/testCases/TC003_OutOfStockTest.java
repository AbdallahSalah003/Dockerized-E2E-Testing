package testCases;

import data.TC002_Data;
import data.TC003_Data;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.ExcelUtils;

import java.util.List;

public class TC003_OutOfStockTest extends BaseTest {
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	LoginPage loginPage;
	ProductPage productPage;
	/**
	 * Test case for testing out-of-stock products scenarios
	 * This test simulates a user journey from login to search for a product
	 * and try to add it to the cart.
	 * It covers the following steps:
	 *   User login with account using valid credentials.
	 *   User searches for a product using a valid query.
	 *   User Add the product to the shopping cart.
	 *   User should see a message contains out of stock.
	 */
	@Test(dataProvider="data", groups = {"Regression", "Master"})
	public void testInvalidSearching(TC003_Data data) {
		logger.info("******** Starting TC003_OutOfStockTest *******");
		logger.debug("Test Case Data: {}", data);
		try {
			homePage = HomePage.getInstance(driver);
			homePage.clickOnLoginLnk();
			loginPage = LoginPage.getInstance(driver);
			loginPage.enterEmail(data.getEmail());
			loginPage.enterPassword(data.getPassword());
			loginPage.clickLogin();
			Assert.assertTrue(loginPage.verifyLogin());
			logger.info("Login verified successfully");

			homePage.enterSearchInput(data.getProduct());
			logger.debug("Entered search query: {}", data.getProduct());
			homePage.clickOnSearchButton();

			searchResultsPage = SearchResultsPage.getInstance(driver);
			Assert.assertTrue(searchResultsPage.verifySearch(data.getProduct()));
			logger.info("Search operation verified for query: {}", data.getProduct());

			searchResultsPage.clickOnProduct(data.getProduct());
			logger.debug("Clicked on product: {}", data.getProduct());
			productPage = ProductPage.getInstance(driver);
			productPage.clickOnAddToCart();
			Assert.assertTrue(productPage.verifyOutOfStockMsg());
			logger.info("Out of stock message is shown successfully");

		} catch (Exception e) {
			logger.error("An error occurred during the test execution", e);
			Assert.fail();
		} finally {
			logger.info("******** Ending TC003_OutOfStockTest *******");
		}
	}
	@DataProvider(name = "data")
	public Object[][] provideHappyFlowData() {
		List<TC003_Data> searchDataList = ExcelUtils.readTC003Data("./testdata/TC003_TestData.xlsx");

		Object[][] data = new Object[searchDataList.size()][1];
		for (int i = 0; i < searchDataList.size(); i++) {
			data[i][0] = searchDataList.get(i);
		}
		return data;
	}
}
