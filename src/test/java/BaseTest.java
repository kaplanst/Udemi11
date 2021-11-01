import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    WebDriver driver;
    public static final String LOGIN = "77773989836";
    public static final String PASSWORD = "General1";
    public static final String WRONG_LOGIN = "77777777756";
    public static final String WRONG_PASSWORD = "Generaly12";
    public static final String SHORT_PASSWORD = "Gen";
    public static String PAGE_LINK = "https://vkitae.kz/";



    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

    }
    @AfterMethod
    public void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
