import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainClass {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://vkitae.kz/");

        List<WebElement> allMenu = driver.findElements(By.xpath("//ul[@class='nav navbar-nav flex menu']/li/a"));
        for (int i = 2; i <= 8; i++) {
            System.out.println(clic(allMenu.get(i)));
        }
    }
    public static String clic (WebElement web){
        web.click();
        return driver.getTitle();
    }

}
