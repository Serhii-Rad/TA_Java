package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IphonePage extends BasePage {

    @FindBy(xpath = "//a[contains(@data-ecomm-cart,'MWL82')]")
    private WebElement addToCartButton;

    @FindBy(id = "js_cart")
    private WebElement addToCartPopup;

    @FindBy(xpath = "//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[@class='item']/i[@data-cart-remove]")
    private WebElement deleteElementFromCart;



    public IphonePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddToCartButton() {
        addToCartButton.click();
    }

    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public WebElement getAddToCartPopup() {
        return addToCartPopup;
    }

    public void clickOnDeleteButton() { deleteElementFromCart.click(); }

}
