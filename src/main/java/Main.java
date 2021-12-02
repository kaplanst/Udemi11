import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        driver.get("https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3670-bilayt");
        String price = driver.findElement(By.xpath("//div[@id='main-product-price']")).getText();
        price = price.replace("тг.", "");
        price = price.replace("руб.", "");
        price = price.replace(" ", "");

        int total = Integer.parseInt(price);
        System.out.println(total / 2);


        driver.quit();
    }
}
