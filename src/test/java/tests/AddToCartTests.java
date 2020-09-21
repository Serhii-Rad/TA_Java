package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddToCartTests extends BaseTest {

    private String EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART = "1";

    @Test
    public void checkAddToCart() {
        getHomePage().clickOnProductCatalogButton();
        getHomePage().clickOnAppleStoreButton();
        getAppleStorePage().clickOnIphoneButton();
        getBasePage().waitForPageLoadComplete(30);
        getIphonePage().clickOnAddToCartButton();
        getBasePage().waitVisibilityOfAddToCartPopup(30, getIphonePage().getAddToCartPopup());
        getIphonePage().clickOnContinueShoppingButton();
        assertEquals(getHomePage().getTextOfAmountProductsInCart(), EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART);
    }
}
