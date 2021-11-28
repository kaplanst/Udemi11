package ServicePack;

import ServicePack.ServiceClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    public static final String LOGIN = "77773989836";
    public static final String PASSWORD = "General1";
    public static final String WRONG_LOGIN = "77777777754";
    public static final String WRONG_PASSWORD = "Generaly12";
    public static final String SHORT_PASSWORD = "Gen";
    public static String PAGE_LINK = "https://vkitae.kz/";
    public static String ITEM = "бальзам";

    public ServiceClass serviceClass;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        serviceClass = new ServiceClass(driver);

    }

    @AfterMethod
    public void ScreenShot(ITestResult result) {
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = result.getName() + "-" + format.format(dateNow) + ".png";
        System.out.println(fileName);
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                // Call method to capture screenshot
                File src = screenshot.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location
                // result.getName() will return name of test case so that screenshot name will be same as test case name
                FileUtils.copyFile(src, new File("C:\\Screenshots\\" + fileName));
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }

        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
