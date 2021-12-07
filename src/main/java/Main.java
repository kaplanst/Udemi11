import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://vkitae.kz/popular_products");
        driver.findElement(By.xpath("//button[@id='grid-view']")).click();

        driver.findElement(By.xpath("//button[@id='list-view']")).click();
        if (driver.findElements(By.xpath("//button[@class='btn btn-default tooltipstered active'][@id='grid-view']")).size() > 0) System.out.println("Yes");
        else System.out.println("No");
        if (driver.findElements(By.xpath("//button[@class='btn btn-default tooltipstered active'][@id='list-view']")).size() > 0) System.out.println("Yes");
        else System.out.println("No");


        driver.quit();
    }
}
