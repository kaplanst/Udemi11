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

    @Test
    void loginPopupWindowTest(){
        MainPage mp = mainPage.clickLoginButton();
        Assert.assertEquals(mp.getPopupWindowText(), "Введите логин и пароль");
    }
    @Test
    void loginLinkTest(){
        LoginPage loginPage = mainPage.clickSignUpLink();
        Assert.assertEquals(loginPage.getRegisteredUserText(), "Я уже зарегистрирован");
    }
    @Test
    void signUpInvalidTest(){
        SignUpPage signUpPage = mainPage.clickSignUpButton();
        signUpPage.registrationWithInvalidCreds("7773989836", "Geretere1");
        Assert.assertEquals(signUpPage.existingPhoneErrorText(), "Такой номер уже зарегистрирован!\n" + "×");
    }
    @Test
    void popUpLoginWindowErrorTest(){
        MainPage mp = mainPage.signInPopupWindow("77777777777", "DDDDDDD");
        Assert.assertEquals(mp.getErrorText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }
    @Test
    void popUpLoginWindowTest(){
        MainPage mp = mainPage.signInPopupWindow("77773989836", "General1");
        Assert.assertTrue(mp.findQuitButton());
    }

    @AfterMethod
    void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
