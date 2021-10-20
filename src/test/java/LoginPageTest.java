import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    WebDriver driver;
    MainPage mainPage;

    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.get("https://vkitae.kz/login/");
        LoginPage loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
