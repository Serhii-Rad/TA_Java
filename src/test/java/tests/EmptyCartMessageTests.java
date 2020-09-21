package tests;

import org.testng.annotations.Test;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmptyCartMessageTests extends BaseTest {

    private  String CART_EMPTY_MESSAGE = "Корзина пустая!";

    @Test
    public void checkEmptyCartMessage()
    {
        getHomePage().clickOnCartButton();
        getBasePage().waitVisibilityOfAddToCartPopup(30, getIphonePage().getAddToCartPopup());
        getHomePage().clickOnApplyOfferButton();
        getBasePage().waitForAlert(30, getHomePage().getAlert());
        getHomePage().getTextOfEmptyCartMessage();

        assertTrue(getHomePage().getTextOfEmptyCartMessage().contains(CART_EMPTY_MESSAGE));
    }
}
