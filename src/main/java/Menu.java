import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Menu {

    WebDriver driver;
    public Menu(WebDriver driver) {this.driver = driver;    }
    public static String PAGE_LINK = "https://vkitae.kz/";


    public By items = By.xpath("//a[@href='javascript:void(0);']");
    public By popularItems = By.xpath("//a[@href='https://vkitae.kz/popular_products/']");

    public By newItems = By.xpath("//li[@class=\"second-level-li has-child\"]/a[text()='Новинки']");
    public By clothes = By.xpath("//a[@href=\"https://vkitae.kz/new_product/novinki-odezhdy/\"]");
    public static final By HEADER_TEXT = By.xpath("//div[@class='col-sm-12']/h1");


    public void popularItemsBullet () {
       Actions actions = new Actions(driver);
       actions.moveToElement(driver.findElement(items)).build().perform();
       actions.moveToElement(driver.findElement(newItems)).build().perform();
       driver.findElement(clothes).click();

    }
    public void ItemsBullet () {
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/popular_products/']")).click();

    }
    public String getHeaderText() {
        return driver.findElement(HEADER_TEXT).getText();
    }
    public boolean menuCheck(String[] buttons, By link) {
        for (int i = 0; i < buttons.length; i++) {
            List<WebElement> topMenu = driver.findElements(link);
            topMenu.get(i).click();
            System.out.println(driver.getCurrentUrl() +"  "+ PAGE_LINK + buttons[i]);
            if (!driver.getCurrentUrl().equals(PAGE_LINK + buttons[i])) return false;
        }
        return true;
    }
}
