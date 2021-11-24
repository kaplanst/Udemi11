import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://vkitae.kz");
        String mainTab = driver.getWindowHandle();
        List<WebElement> right = driver.findElements(By.xpath("//*[@class='col-md-2 social-box']/ul/li"));
        for (WebElement menu: right) {
            menu.click();
            for (String tab : driver.getWindowHandles()) {
                driver.switchTo().window(tab);
            }
            System.out.println(driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(mainTab);
        }
    }
}
