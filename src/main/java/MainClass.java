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
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//a[@href='javascript:void(0);']"))).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//li[@class=\"second-level-li has-child\"]/a[text()='Новинки']"))).build().perform();
        driver.findElement(By.xpath("//a[@href=\"https://vkitae.kz/new_product/novinki-odezhdy/\"]")).click();
    }

}
