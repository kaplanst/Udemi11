import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignUpPageTest {
    WebDriver driver;
    SignUpPage signUpPage;

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://vkitae.kz/simpleregister/");
        signUpPage = new SignUpPage(driver);

    }
    @Test
    void confirmPassFieldTest(){
        signUpPage.inpuConfirmPassword("GGGGGGGG");
        Assert.assertEquals(signUpPage.passwordConfirmErrorText(), "Подтверждение пароля не соответствует паролю!");
    }
    @Test
    void signUpEmptyPhoneTest(){
        signUpPage.registrationWithInvalidCreds("", "Geretere1");
        Assert.assertEquals(signUpPage.phoneErrorText(), "Номер телефона указан не верно!");
    }
    @Test
    void signUpInvalidPasswordTest(){
        signUpPage.registrationWithInvalidCreds("7773989836", "Ger");
        Assert.assertEquals(signUpPage.passwordErrorText(), "Пароль должен быть от 4 до 20 символов!");
    }

    @AfterMethod
    void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
