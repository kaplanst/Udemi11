import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainPageTest extends BaseTest{

    @BeforeMethod
    void setUpMainPage(){
        driver.get(PAGE_LINK);
    }

    @Test
    void loginPopupWindowTest(){
        driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.xpath("//a[@onclick='get_oct_popup_login();']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-text']/p")).getText(), "Введите логин и пароль");
    }

    @Test
    void popUpLoginWindowEmptyTest(){
        driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.xpath("//a[@onclick='get_oct_popup_login();']")).click();
        driver.findElement(By.xpath("//button[@id='popup-login-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }

    @Test
    void popUpLoginWindowIncorrectTest(){
        driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.xpath("//a[@onclick='get_oct_popup_login();']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(WRONG_LOGIN);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(WRONG_PASSWORD);
        driver.findElement(By.xpath("//button[@id='popup-login-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text-danger']")).getText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }


    @Test
    void popUpLoginWindowTest(){
        driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.xpath("//a[@onclick='get_oct_popup_login();']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(LOGIN);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//button[@id='popup-login-button']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//*[text()='Выйти']")).size() > 0));
    }
    @Test
    void currencySwitchTest(){
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle  btn-currency']")).click();
        driver.findElement(By.xpath("//button[@name='RUB']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='cart-total-price']")).getText(), "0.00руб.");
    }
//    @Test
//    void searchItemTest() throws InterruptedException {
//        mainPage.itemSearch(ITEM);
//        List<WebElement> serCheck = driver.findElements(By.xpath("//div[@id='content']//h4/a"));
//        Assert.assertTrue(serCheck.size() > 0);
//        for (WebElement i : serCheck) Assert.assertTrue(i.getText().toLowerCase().contains(ITEM));
//    }




}
