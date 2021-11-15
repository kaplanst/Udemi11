import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest{

    @BeforeMethod
    public void SetUpLoginPage(){
        driver.get(PAGE_LINK + "login/");

    }

    @Test
    public void loginWithEmptyCreds(){
        driver.findElement(By.xpath("//input[@class='oct-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=' Неправильно заполнены поля E-Mail и/или пароль!']")).isDisplayed());
    }
    @Test
    public void loginWithIncorrectCreds(){
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(WRONG_LOGIN);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(WRONG_PASSWORD);
        driver.findElement(By.xpath("//input[@class='oct-button']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()=' Неправильно заполнены поля E-Mail и/или пароль!']")).isDisplayed());
    }

    @Test
    public void signUpPageRedirectTest(){
        driver.findElement(By.xpath("//a[@class='oct-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Быстрая регистрация");
    }

    @Test
    public void loginTest(){
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(LOGIN);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//input[@class='oct-button']")).click();
        Assert.assertTrue((driver.findElements(By.xpath("//*[text()='Выйти']")).size() > 0));
    }

}
