import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeClass
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://vkitae.kz/");
        mainPage = new MainPage(driver);
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


    @AfterClass
    void Bye(){
        driver.quit();
    }

}
