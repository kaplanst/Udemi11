import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://vkitae.kz");
        List<WebElement> foot = driver.findElements(By.xpath("//footer//div[2]/div[1]/ul/li/a"));
        for (WebElement i: foot) {
            i.click();
        }



//        driver.findElement(By.xpath("//footer//div[2]/div[1]/ul/li[1]/a")).click();
//        driver.findElement(By.xpath("//footer//div[2]/div[1]/ul/li[2]/a")).click();
//        driver.findElement(By.xpath("//footer//div[2]/div[1]/ul/li[3]/a")).click();
//        driver.findElement(By.xpath("//footer//div[2]/div[1]/ul/li[4]/a")).click();






    }
}
