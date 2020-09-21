package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTests extends BaseTest {

    private String SEARCH_KEYWORD = "iPhone 11";
    private String EXPECTED_SEARCH_QUERY = "query=iPhone";

    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        assertTrue(getDriver().getCurrentUrl().contains(EXPECTED_SEARCH_QUERY));
    }

    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        getBasePage().implicitWait(30);
        assertEquals(getSearchResultsPage().getSearchResultsCount(), 12);
    }

    @Test(priority = 3)
    public void checkThatSearchResultsContainsSearchWord() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        for (WebElement webElement : getSearchResultsPage().getSearchResultsList()) {
            assertTrue(webElement.getText().contains(SEARCH_KEYWORD));
        }
    }
}
