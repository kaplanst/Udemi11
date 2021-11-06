import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Menu {
    WebDriver driver;
    public Menu(WebDriver driver) {this.driver = driver;    }

    public Actions actions = new Actions(driver);

    public By items = By.xpath("//a[@href='javascript:void(0);']");
    public By popularItems = By.xpath("//a[@href='https://vkitae.kz/popular_products/']");
    public By newItems = By.xpath("//li[@class=\"second-level-li has-child\"]/a[text()='Новинки']");
    public By clothes = By.xpath("//a[@href=\"https://vkitae.kz/new_product/novinki-odezhdy/\"]");


    public void popularItemsBullet () throws InterruptedException {
       actions.moveToElement(driver.findElement(items)).build().perform();
       Thread.sleep(3000);
       actions.moveToElement(driver.findElement(newItems)).build().perform();
       Thread.sleep(3000);
       driver.findElement(clothes).click();


    }
}
