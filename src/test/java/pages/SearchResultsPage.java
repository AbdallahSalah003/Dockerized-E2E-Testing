package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchResultsPage extends BasePage {
    private static volatile SearchResultsPage instance;
    private SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    public static SearchResultsPage getInstance(WebDriver driver) {
        if(instance == null) {
            synchronized (SearchResultsPage.class) {
                if(instance == null) {
                    instance = new SearchResultsPage(driver);
                }
            }
        }
        return instance;
    }
    @FindBy(id="products-orderby")
    WebElement sortByOption;
    @FindBy(css=".product-item")
    List<WebElement> productsList;
    @FindBy(xpath="//input[@id='q']")
    WebElement verifySearchAttempt;
	@FindBy(css=".no-result")
	WebElement noResultsFoundMsg;

    public void filterSortByOption(String option) {
        // option example : Rating (Highest)
        new Select(sortByOption).selectByVisibleText(option);
    }
    public void clickOnProduct(String productName) {
        WebElement product = productsList.stream()
                .filter(prod -> prod.findElement(By.cssSelector("div.details h2 a")).getText().equals(productName))
                .findFirst().orElse(null);
        assert product != null;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product.findElement(By.tagName("a")));
    }
    public boolean verifySearch(String query) {
        return verifySearchAttempt.isDisplayed() && verifySearchAttempt.getDomAttribute("value").equals(query);
    }
    public boolean verifyFiltersOnSearchResults(String query) {
        boolean ascending = "Name: A to Z".equals(query);
		wait.until(mydriver -> productsList.getFirst().findElement(By.cssSelector("div.details h2 a")));
        List<String> productNames = productsList.stream()
                .map(product -> product.findElement(By.cssSelector("div.details h2 a")).getText())
                .toList();

        return IntStream.range(0, productNames.size() - 1)
                .allMatch(i -> {
                    int comparison = productNames.get(i).compareTo(productNames.get(i + 1));
                    return ascending ? comparison <= 0 : comparison >= 0;
                });
    }
	public boolean verifyNoResultsFoundMsg() {
		return noResultsFoundMsg.isDisplayed() && noResultsFoundMsg.getText().contains("No products were found");
	}
}
