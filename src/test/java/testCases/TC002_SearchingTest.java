package testCases;

import data.SearchData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelUtils;

import java.util.List;

public class TC002_SearchingTest extends BaseTest{
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	LoginPage loginPage;
	/**
	 * Test case for testing search scenarios
	 * This test simulates a user journey from login to search for a product
	 * It covers the following steps:
	 *   User login with account using valid credentials.
	 *   User searches for a product using an invalid query.
	 *   User should see a message stating that there is no match products.
	 */
	@Test(dataProvider="search_data", groups = {"Regression", "Master"})
	public void testInvalidSearching(SearchData data) {
		logger.info("******** Starting TC002_SearchingTest *******");
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

			homePage.enterSearchInput(data.getSearchQuery());
			logger.debug("Entered search query: {}", data.getSearchQuery());
			homePage.clickOnSearchButton();

			searchResultsPage = SearchResultsPage.getInstance(driver);
			Assert.assertTrue(searchResultsPage.verifySearch(data.getSearchQuery()));
			logger.info("Search operation verified for query: {}", data.getSearchQuery());

			Assert.assertTrue(searchResultsPage.verifyNoResultsFoundMsg());
			logger.info("Invalid Search operation Message verified for query: {}", data.getSearchQuery());


		} catch (Exception e) {
			logger.error("An error occurred during the test execution", e);
			Assert.fail();
		} finally {
			logger.info("******** Ending TC002_SearchingTest *******");
		}
	}
	@DataProvider(name = "search_data")
	public Object[][] provideHappyFlowData() {
		List<SearchData> searchDataList = ExcelUtils.readSearchData("./testdata/TC002_TestData.xlsx");

		Object[][] data = new Object[searchDataList.size()][1];
		for (int i = 0; i < searchDataList.size(); i++) {
			data[i][0] = searchDataList.get(i);
		}
		return data;
	}
}
