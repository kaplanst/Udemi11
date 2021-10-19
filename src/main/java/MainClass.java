import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://vkitae.kz/");


        MainPage mainPage = new MainPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        mainPage.clickSignUpButton();
        signUpPage.registrationWithInvalidCreds("77773989836", "General1");
  //      driver.quit();

    }
}
