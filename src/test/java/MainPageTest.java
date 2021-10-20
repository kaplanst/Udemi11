import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeMethod
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://vkitae.kz/");
        mainPage = new MainPage(driver);
    }

    @Test(priority=1)
    void loginLinkTest(){
        LoginPage loginPage = mainPage.clickSignUpLink();
        Assert.assertEquals(loginPage.getRegisteredUserText(), "Я уже зарегистрирован");
    }
    @Test(priority=0)
    void signUpInvalidTest(){
        SignUpPage signUpPage = mainPage.clickSignUpButton();
        signUpPage.registrationWithInvalidCreds("7773989836", "Geretere1");
        Assert.assertEquals(signUpPage.existingPhoneErrorText(), "Такой номер уже зарегистрирован!\n" + "×");
    }
    @Test
    void signUpEmptyPhoneTest(){
        SignUpPage signUpPage = mainPage.clickSignUpButton();
        signUpPage.registrationWithInvalidCreds("", "Geretere1");
        Assert.assertEquals(signUpPage.phoneErrorText(), "Номер телефона указан не верно!");
    }
    @Test
    void signUpInvalidPasswordTest(){
        SignUpPage signUpPage = mainPage.clickSignUpButton();
        signUpPage.registrationWithInvalidCreds("7773989836", "Ger");
        Assert.assertEquals(signUpPage.passwordErrorText(), "Пароль должен быть от 4 до 20 символов!");
    }
    @AfterMethod
    void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
