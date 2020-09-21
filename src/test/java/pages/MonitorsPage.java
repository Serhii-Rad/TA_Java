package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonitorsPage extends BasePage {

    @FindBy(xpath = "//div[@class='brand-box__info']/div/a[contains(text(), 'Мониторы')]")
    private WebElement monitorsButton;

    @FindBy(xpath = "//div[@class='row-m']//input[contains(@class, 'form-control-min')]")
    private WebElement minPriceField;

    @FindBy(xpath = "//div[@class='row-m']//input[contains(@class, 'form-control-max')]")
    private WebElement maxPriceField;

    @FindBy(xpath = "//a[@class='tags__item']")
    private WebElement priceTag;

    public MonitorsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnMonitors() { monitorsButton.click(); }

    public WebElement getMonitorsButton() { return monitorsButton; }

    public  void clickOnMinPriceField() { minPriceField.click(); }

    public  void clickOnMaxPriceField() { maxPriceField.click(); }

    public  void clearMinPriceField() { minPriceField.clear(); }

    public  void clearMaxPriceField() { maxPriceField.clear(); }

    public void enterMinPrice(final String minPrice) { minPriceField.sendKeys(minPrice, Keys.ENTER);
    }

    public void enterMaxPrice(final String maxPrice) { maxPriceField.sendKeys(maxPrice, Keys.ENTER);
    }

    public WebElement getPriceField() { return minPriceField; }

    public String getTextFromPriceTag() { return priceTag.getText(); }

}
