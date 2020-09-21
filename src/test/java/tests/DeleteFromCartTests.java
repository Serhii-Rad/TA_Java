package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DeleteFromCartTests extends BaseTest {
    private String EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART = "0";

    @Test
    public void checkDeletingElementFromCart() {
        getHomePage().clickOnProductCatalogButton();
        getHomePage().clickOnAppleStoreButton();
        getAppleStorePage().clickOnIphoneButton();
        getBasePage().waitForPageLoadComplete(30);
        getIphonePage().clickOnAddToCartButton();
        getBasePage().waitVisibilityOfAddToCartPopup(30, getIphonePage().getAddToCartPopup());
        getIphonePage().clickOnContinueShoppingButton();
        getBasePage().waitVisibilityOfCartButton(30, getHomePage().getCartButton());
        getHomePage().clickOnCartButton();
        getBasePage().waitVisibilityOfAddToCartPopup(30, getIphonePage().getAddToCartPopup());
        getIphonePage().clickOnDeleteButton();
        getIphonePage().clickOnContinueShoppingButton();
        getBasePage().refresh();
        getBasePage().waitForPageLoadComplete(30);

        assertEquals(getHomePage().getTextOfAmountProductsInCart(), EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART);
    }
}
