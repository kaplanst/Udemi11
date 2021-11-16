import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class MainPageTest extends BaseTest {

    @BeforeMethod
    void setUpMainPage(){
        driver.get(PAGE_LINK);
    }

    @Test
    void loginPopupWindowTest(){
        serviceClass.loginPopupWindow();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-text']/p")).getText(), "Введите логин и пароль");
    }

    @Test
    void popUpLoginWindowEmptyTest(){
        serviceClass.loginPopupWindow();
        driver.findElement(By.xpath("//button[@id='popup-login-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }

    @Test
    void popUpLoginWindowIncorrectTest(){
        serviceClass.popUpLoginWindowInput(WRONG_LOGIN, WRONG_PASSWORD);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }

    @Test
    void popUpLoginWindowTest(){
        serviceClass.popUpLoginWindowInput(LOGIN, PASSWORD);
        Assert.assertTrue((driver.findElements(By.xpath("//*[text()='Выйти']")).size() > 0));
    }

    @Test
    void currencySwitchTest(){
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle  btn-currency']")).click();
        driver.findElement(By.xpath("//button[@name='RUB']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='cart-total-price']")).getText(), "0.00руб.");
    }

    @Test
    void searchItemTest() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='search']")).sendKeys(ITEM);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='oct-search-button']")).click();
        List<WebElement> serCheck = driver.findElements(By.xpath("//div[@id='content']//h4/a"));
        Assert.assertTrue(serCheck.size() > 0);
        for (WebElement i : serCheck) Assert.assertTrue(i.getText().toLowerCase().contains(ITEM));
    }

    @Test
    public void itemMenuTest() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@href='javascript:void(0);']"))).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//li[@class='second-level-li has-child']/a[text()='Новинки']"))).build().perform();
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/new_product/novinki-odezhdy/']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-sm-12']/h1")).getText(), "Новинки одежды");
    }

    @Test
    public void popularItemsTest(){
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/popular_products/']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-sm-12']/h1")).getText(), "Популярные товары");
    }

    @Test
    public void menuTest() {
        String[] arr = {"Популярные товары", "Новинки товара-vkitae.kz", "Все акции",
                "Уцененный товар", "Секреты долголетия - vkitae.kz", "Как оформить заказ на сайте vkitae.kz"};
        for (int i = 0; i < arr.length; i++) {
            List<WebElement> allMenu = driver.findElements(By.xpath("//ul[@class='nav navbar-nav flex menu']/li/a"));
            allMenu.get(i+2).click();
            Assert.assertTrue(arr[i].equals(driver.getTitle()));
        }
    }

    @Test
    public void topMenuTest() {
        String[] urls = {"tovary-optom-iz-kitaya", "dostavka", "oplata", "contact-us/", "reviews/"};
        for (int i = 0; i < urls.length; i++) {
            List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='list-inline top-left-info-links']/li/a"));
            topMenu.get(i).click();
            Assert.assertEquals(driver.getCurrentUrl(), PAGE_LINK + urls[i]);
        }
      }

    @Test
    void loginLinkTest(){
        driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[@href='https://vkitae.kz/index.php?route=account/account']")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='Я уже зарегистрирован']")).size() > 0);
    }
}