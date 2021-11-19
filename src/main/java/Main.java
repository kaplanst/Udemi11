import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
        String link;
        for (int i = 0; i < 4; i++) {

            link = String.format("//footer//div[2]/div[1]/ul/li[%S]/a", i);
            WebElement dr = driver.findElement(By.xpath(link));
            clicker(dr);
            System.out.println(driver.findElement(By.xpath("//h1")).getText());


        }
   }
   public static String clicker(WebElement linka){
        linka.click();
       System.out.println(linka.getText());
       return linka.getText();
       }
}
