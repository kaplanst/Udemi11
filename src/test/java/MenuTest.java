import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest{

    Menu menu;

    @BeforeMethod
    void setUpMainPage() {
        driver.get(PAGE_LINK);
        menu = new Menu(driver);

    }
    @Test
    void itemMenuTest() throws InterruptedException{
        menu.popularItemsBullet();
    }
    @Test
    void popularItemsTest(){
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/popular_products/']")).click();
    }
}
