package testCases;

import data.TransactionData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelUtils;

import java.io.File;
import java.util.List;

public class TC001_NopCommerceTransactionFlowTest extends BaseTest {
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	ProductPage productPage;
	ShoppingCartPage shoppingCartPage;
	CheckoutPage checkoutPage;
	OrdersPage ordersPage;
	RegisterPage registerPage;
	MyAccountPage myAccountPage;
    /**
     * Test case for the "happy path" scenario of a typical e-commerce transaction.
     * This test simulates a user journey from register to placing an order
     * It covers the following steps:
     *   User register new account using valid credentials.
     *   User searches for a product using a query and verifies the search results.
     *   User applies a filter to sort the search results and verifies the filter operation.
     *   User selects a product, specifies a quantity, adds it to the cart, and verifies the addition.
     *   User navigates to the shopping cart, estimates shipping and taxes, applies a coupon, and verifies the coupon application.
     *   User proceeds to checkout, selects shipping and payment methods, confirms the order, and verifies the order placement.
     *   User checks the order history to ensure the order is recorded correctly.
     */
    @Test(dataProvider="flow_data", groups = {"Regression", "Master"})
    public void testCompleteTransactionFlow(TransactionData data) {
        logger.info("******** Starting TC001_NopCommerceTransactionFlowTest *******");
        logger.debug("Transaction data: {}", data);
        try {
			homePage = HomePage.getInstance(driver);
            homePage.clickOnRegisterLnk();
			registerPage = RegisterPage.getInstance(driver);
			registerPage.chooseGender(data.getGender());
			registerPage.enterFirstName(data.getFirstName());
			registerPage.enterLastName(data.getLastName());
			registerPage.enterEmail(data.getEmail());
			registerPage.enterPassword(data.getPassword());
			registerPage.enterConfirmPassword(data.getPassword());
			registerPage.clickRegisterButton();
			Assert.assertTrue(registerPage.verifyRegisterAttempt());
			logger.info("Register verified successfully");
			registerPage.clickContinueAfterRegister();


			homePage.enterSearchInput(data.getSearchQuery());
            logger.debug("Entered search query: {}", data.getSearchQuery());
            homePage.clickOnSearchButton();
			searchResultsPage = SearchResultsPage.getInstance(driver);
			Assert.assertTrue(searchResultsPage.verifySearch(data.getSearchQuery()));
            logger.info("Search results verified for query: {}", data.getSearchQuery());

			searchResultsPage.filterSortByOption(data.getFilterQuery());
            logger.debug("Applied filter: {}", data.getFilterQuery());
            Assert.assertTrue(searchResultsPage.verifyFiltersOnSearchResults(data.getFilterQuery()));
            logger.info("Filter operation verified");

            searchResultsPage.clickOnProduct(data.getProduct());
            logger.debug("Clicked on product: {}", data.getProduct());
			productPage = ProductPage.getInstance(driver);
			productPage.enterQty(data.getQuantity());
            productPage.clickOnAddToCart();
            Assert.assertTrue(productPage.verifyAddToCartOperation());
            logger.info("Product added to cart successfully");
            productPage.clickOnShoppingCartFromConfirmMessage();
			shoppingCartPage = ShoppingCartPage.getInstance(driver);
			shoppingCartPage.checkTermsOfServiceCheckbox();
			shoppingCartPage.clickCheckoutBtn();

			checkoutPage = CheckoutPage.getInstance(driver);
			checkoutPage.shippingInfoEnterFirstname(data.getFirstName());
			checkoutPage.shippingInfoEnterLastname(data.getLastName());
			checkoutPage.shippingInfoSelectCountry(data.getCountry());
			checkoutPage.shippingInfoEnterAddress1(data.getAddress());
			checkoutPage.shippingInfoEnterCity(data.getZone());
			checkoutPage.shippingInfoSelectRegion(data.getZone());
			checkoutPage.shippingInfoEnterPhone(data.getPhone());
			checkoutPage.shippingInfoEnterPostalCode(data.getPostcode());
			checkoutPage.clickOnShippingInfoContinueBtn();
            logger.info("Shipping info entered successfully");

			checkoutPage.selectShippingOption("Ground");
			checkoutPage.clickOnShippingMethodContinueBtn();
			checkoutPage.selectMoneyOrderPaymentMethod();
			checkoutPage.acceptPaymentTerms();
			checkoutPage.clickOnConfirmOrderBtn();
			Assert.assertTrue(checkoutPage.verifyConfirmOrder());
			logger.info("Confirm order verified successfully");

            homePage.clickOnMyAccountLnk();
            myAccountPage = MyAccountPage.getInstance(driver);
			myAccountPage.clickOrdersLnk();
			ordersPage = OrdersPage.getInstance(driver);
			Assert.assertTrue(ordersPage.verifyOrderExists());
            logger.info("Order exists in order history");
        } catch (Exception e) {
            logger.error("An error occurred during the test execution", e);
            Assert.fail();
        } finally {
            logger.info("******** Ending TC001_NopCommerceTransactionFlowTest *******");
        }
    }
    @DataProvider(name = "flow_data")
    public Object[][] provideHappyFlowData() {
        List<TransactionData> transactionDataList = ExcelUtils.readTransactionData("./testdata/TC001_TestData.xlsx");

        Object[][] data = new Object[transactionDataList.size()][1];
        for (int i = 0; i < transactionDataList.size(); i++) {
            data[i][0] = transactionDataList.get(i);
        }

        return data;
    }

}
