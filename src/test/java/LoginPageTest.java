import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest {

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
    public void loginWithIncorrectCreds() throws InterruptedException {
        serviceClass.signIn(WRONG_LOGIN, WRONG_PASSWORD);
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).isDisplayed());
    }

    @Test
    public void signUpPageRedirectTest(){
        driver.findElement(By.xpath("//a[@class='oct-button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), "Быстрая регистрация");
    }

    @Test
    public void loginTest(){
        serviceClass.signIn(LOGIN, PASSWORD);
        Assert.assertTrue((driver.findElements(By.xpath("//*[text()='Выйти']")).size() > 0));
    }
}
