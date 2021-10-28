import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://vkitae.kz/login/");
        loginPage = new LoginPage(driver);
    }
    @Test
    public void loginWithEmptyCreds(){
        loginPage.incorrectLoginCreds("", "");
        Assert.assertEquals(loginPage.getErrorText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }
    @Test
    public void loginWithIncorrectCreds(){
        loginPage.incorrectLoginCreds("DDDDD", "DDDDD");
        Assert.assertEquals(loginPage.getErrorText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }
    @Test
    public void signUpPageRedirectTest(){
        SignUpPage signUpPage = loginPage.createAccount();
        Assert.assertEquals(signUpPage.getHeaderText(), "Быстрая регистрация");
    }
    @AfterMethod
    public void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
