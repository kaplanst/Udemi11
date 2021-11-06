import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Menu {

    WebDriver driver;
    public Menu(WebDriver driver) {this.driver = driver;    }


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
}
