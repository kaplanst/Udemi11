import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MenuTest extends BaseTest{

    Menu menu;
    public static final By HEADER_TEXT = By.xpath("//div[@class='col-sm-12']/h1");

    @BeforeMethod
    public void setUpPage() {
        driver.get(PAGE_LINK);
        menu = new Menu(driver);

    }
    @Test
    public void itemMenuTest() {
//        Menu m = new Menu(driver);
        menu.popularItemsBullet();
        Assert.assertEquals(menu.getHeaderText(), "Новинки одежды");


    }
    @Test
    public void popularItemsTest(){
        menu.ItemsBullet();
        Assert.assertEquals(menu.getHeaderText(), "Популярные товары");
    }
    @Test
    public void menuTest() throws InterruptedException {
        List<WebElement> allMenu = driver.findElements(By.xpath("//ul[@class='nav navbar-nav flex menu']/li/a"));
        for (int i = 2; i <= 8; i++) {
            allMenu.get(i).click();
            Thread.sleep(2000);

            System.out.println(driver.getTitle());
            Thread.sleep(2000);
        }
    }
}
