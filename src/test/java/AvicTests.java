import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.Keys.TAB;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AvicTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();//создаем экзаемпляр хром драйвера
        driver.manage().window().maximize();//открыли браузер на весь экран
        driver.get("https://avic.ua/");//открыли сайт
    }

    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11", Keys.ENTER);//вводим в поиск iPhone 11
        assertTrue(driver.getCurrentUrl().contains("query=iPhone"));//проверяем что урла содержит кверю
    }

    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("iPhone 11", ENTER);//вводим в поиск iPhone 11
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//неявное ожидание 30 сек
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));//собрали элементы поиска в лист
        int actualElementsSize = elementList.size();//узнали количество элементов в листе
        assertEquals(actualElementsSize,12);//сравнили количество элементов актуальное с тем которое ожидаем
    }

    @Test(priority = 3)
    public void checkThatSearchResultsContainsSearchWord() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("iPhone 11", ENTER);//вводим в поиск iPhone 11
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));//собрали элементы поиска в лист
        for (WebElement webElement : elementList) { //прошлись циклом и проверили что каждый элемент листа содержит текс iPhone 11
            assertTrue(webElement.getText().contains("iPhone 11"));
        }
    }

    @Test(priority = 4)
    public void checkAddToCart() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")).click();//Apple Store
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'iphone')]")).click();//iphone
        new WebDriverWait(driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(xpath("//a[contains(@data-ecomm-cart,'MWLT2')]")).click();//add to cart iphone
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап с товаром добавленным в корзину
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")).click();//продолжить покупки
        String actualProductsCountInCart =
            driver.findElement(xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();//получили 1 которя в карте (один продукт)
        assertEquals(actualProductsCountInCart, "1");
    }
    @Test(priority = 5)
    public void checkDeletingElementFromCart()
    {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")).click();//Apple Store
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'iphone')]")).click();//iphone
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(xpath("//a[contains(@data-ecomm-cart,'MWLT2')]")).click();//add to cart iphone
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап с товаром добавленным в корзину
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")).click();//продолжить покупки
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/div[contains(@class, 'header-bottom__cart active-cart')]")));
        driver.findElement(xpath("//a/div[contains(@class, 'header-bottom__cart active-cart')]")).click();// нажать на кнопу корзины
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item']/i[@data-cart-remove]")));
        driver.findElement(xpath("//div[@class='item']/i[@data-cart-remove]")).click();// удалить выбраный товар с корзины

        driver.findElement(xpath("//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")).click();//продолжить покупки
        driver.navigate().refresh();// перезагрузка страницы, иначе корзина не обновиться
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        String actualProductsCountInCart = driver.findElement(xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();
        assertEquals(actualProductsCountInCart, "0");// проверка что продуктов в корзине 0

    }
    @Test(priority = 6)
    public void checkEmptyCartMessage()
    {
        driver.findElement(xpath("//a/div[contains(@class, 'header-bottom__cart active-cart')]")).click();// нажать на кнопу корзины
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап корзины
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        driver.findElement(xpath("//div[@class='btns-cart-holder']//a[contains(@class,'btn--green')]")).click();//нажать "оформить заказ"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalAlert")));

        assertTrue(driver.findElement(xpath("//div[contains(text(), 'Корзина пустая!')]")).getText().contains("Корзина пустая!"), "Нет сообщения что корзина пуста.");// проверка что сообщение содержит строку "корзина пустая!"
    }
    @Test(priority = 7)
    public void checkPriceLimits()
    {
        String MIN = "1266";
        String MAX = "32612";

        driver.findElement(xpath("//div[@class='sidebar']//a[contains(text(), 'Компьютеры')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится элемент'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand-box__info']/div/a[contains(text(), 'Мониторы')]")));
        driver.findElement(xpath("//div[@class='brand-box__info']/div/a[contains(text(), 'Мониторы')]")).click();
        new WebDriverWait(driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(By.xpath("//div[@class='row-m']//input[contains(@class, 'form-control-min')]")).click();
        driver.findElement(By.xpath("//div[@class='row-m']//input[contains(@class, 'form-control-min')]")).clear();
        driver.findElement(xpath("//div[@class='row-m']//input[contains(@class, 'form-control-min')]")).sendKeys(MIN);//вводим нижний ценовой лимит
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//a[@class='filter-tooltip js_filters_accept']")));
        driver.findElement(xpath("//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//a[@class='filter-tooltip js_filters_accept']")).click();
        new WebDriverWait(driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(By.xpath("//div[@class='row-m']//input[contains(@class, 'form-control-max')]")).click();
        driver.findElement(By.xpath("//div[@class='row-m']//input[contains(@class, 'form-control-max')]")).clear();
        driver.findElement(xpath("//div[@class='row-m']//input[contains(@class, 'form-control-min')]")).sendKeys(TAB, MAX);//вводим верхний ценовой лимит
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//a[@class='filter-tooltip js_filters_accept']")));
        driver.findElement(xpath("//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//a[@class='filter-tooltip js_filters_accept']")).click();


        String x = driver.findElement(xpath("//div[contains(@data-ecomm, 'Acer Z301CBMIPHZX')]")).getText();
        //div[@class='prod-cart__descr']
        int min = Integer.parseInt(MIN);
        int max = Integer.parseInt(MAX);
        int s = Integer.parseInt(x);
        assertEquals(min, MIN);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
