package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@id='input_search']")
    private WebElement searchInput;

    @FindBy(xpath = "//span[@class='sidebar-item']")
    private WebElement productCatalogButton;

    @FindBy(xpath = "//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")
    private WebElement appleSoreButton;

    @FindBy(xpath = "//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")
    private WebElement amountOfProductsInCart;

    @FindBy(xpath = "//a/div[contains(@class, 'header-bottom__cart active-cart')]")
    private WebElement cartButton;

    @FindBy(xpath = "//div[@class='btns-cart-holder']//a[contains(@class,'btn--green')]")
    private WebElement applyOffer;

    @FindBy(id = "modalAlert")
    private WebElement alertMessage;

    @FindBy(xpath = "//div[contains(text(), 'Корзина пустая!')]")
    private  WebElement messageOfEmptyCart;

    @FindBy(xpath = "//div[@class='sidebar']//a[contains(text(), 'Компьютеры')]")
    private WebElement computersButton;

    @FindBy(xpath = "//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//a[@class='filter-tooltip js_filters_accept']")
    private WebElement acceptButton;




    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyword(final String keyword) {
        searchInput.sendKeys(keyword, Keys.ENTER);
    }

    public void clickOnProductCatalogButton() {
        productCatalogButton.click();
    }

    public void clickOnAppleStoreButton() {
        appleSoreButton.click();
    }

    public String getTextOfAmountProductsInCart() {
        return amountOfProductsInCart.getText();
    }

    public void clickOnCartButton() { cartButton.click(); }

    public WebElement getCartButton() { return  cartButton;}

    public void  clickOnApplyOfferButton() { applyOffer.click(); }

    public WebElement getAlert() { return  alertMessage; }

    public String getTextOfEmptyCartMessage() { return  messageOfEmptyCart.getText(); }

    public void clickOnComputersButton() { computersButton.click(); }

    public void clickOnAcceptButton() { acceptButton.click(); }

    public WebElement getAcceptButton() { return acceptButton; }

}
