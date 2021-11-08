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
        String[] arr = {"Популярные товары",
                "Новинки товара-vkitae.kz",
                "Все акции",
                "Уцененный товар",
                "Секреты долголетия - vkitae.kz",
                "Как оформить заказ на сайте vkitae.kz"};
        for (int i = 2; i <= 8; i++) {
        List<WebElement> allMenu = driver.findElements(By.xpath("//ul[@class='nav navbar-nav flex menu']/li/a"));
            allMenu.get(i).click();
            if (arr[i-2].equals(driver.getTitle())) System.out.println("Принято");
        }
    }


}
