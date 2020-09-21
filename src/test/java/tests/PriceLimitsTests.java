package tests;

import org.testng.annotations.Test;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertTrue;

public class PriceLimitsTests extends BaseTest {

    public String MIN_PRICE = "12666";
    public String MAX_PRICE = "32612";
    public String PRICE_TAG = MIN_PRICE + "-" + MAX_PRICE;

    @Test
    public void checkPriceLimits()
    {
        getHomePage().clickOnComputersButton();
        getBasePage().waitVisibilityOfMonitors(30, getMonitorsPage().getMonitorsButton());
        getMonitorsPage().clickOnMonitors();
        getBasePage().waitVisibilityOfPriceLimitField(30, getMonitorsPage().getPriceField());
        getMonitorsPage().clickOnMinPriceField();
        getMonitorsPage().clearMinPriceField();
        getMonitorsPage().enterMinPrice(MIN_PRICE);
        getHomePage().waitVisibilityOfApplyButton(30, getHomePage().getAcceptButton());
        getHomePage().clickOnAcceptButton();
        getBasePage().waitForPageLoadComplete(30);
        getMonitorsPage().clickOnMaxPriceField();
        getMonitorsPage().clearMaxPriceField();
        getMonitorsPage().enterMaxPrice(MAX_PRICE);
        getHomePage().waitVisibilityOfApplyButton(30, getHomePage().getAcceptButton());
        getHomePage().clickOnAcceptButton();
        getBasePage().waitForPageLoadComplete(30);

        assertTrue(getMonitorsPage().getTextFromPriceTag().contains(PRICE_TAG));
    }
}
