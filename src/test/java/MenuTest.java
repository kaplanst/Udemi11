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

//    Menu menu;
//    public static final By HEADER_TEXT = By.xpath("//div[@class='col-sm-12']/h1");
//    public static final By TOP_MENU = By.xpath("//ul[@class='list-inline top-left-info-links']/li/a");
//
//
//    @BeforeMethod
//    public void setUpPage() {
//        driver.get(PAGE_LINK);
//        menu = new Menu(driver);
//
//    }
//    @Test
//    public void itemMenuTest() {
////        Menu m = new Menu(driver);
//        menu.popularItemsBullet();
//        Assert.assertEquals(menu.getHeaderText(), "Новинки одежды");
//
//
//    }
//    @Test
//    public void popularItemsTest(){
//        menu.ItemsBullet();
//        Assert.assertEquals(menu.getHeaderText(), "Популярные товары");
//    }
//    @Test
//    public void menuTest() {
//        String[] arr = {"Популярные товары",
//                "Новинки товара-vkitae.kz",
//                "Все акции",
//                "Уцененный товар",
//                "Секреты долголетия - vkitae.kz",
//                "Как оформить заказ на сайте vkitae.kz"};
//        for (int i = 0; i < arr.length; i++) {
//            List<WebElement> allMenu = driver.findElements(By.xpath("//ul[@class='nav navbar-nav flex menu']/li/a"));
//            allMenu.get(i+2).click();
//            Assert.assertTrue(arr[i].equals(driver.getTitle()));
//        }
//
//    }
//    @Test
//    public void topMenuTest() {
//        String[] urls = {"tovary-optom-iz-kitaya", "dostavka", "oplata", "contact-us/", "reviews/"};
//        Assert.assertTrue(menu.menuCheck(urls, TOP_MENU));
////        for (int i = 0; i < urls.length; i++) {
////            List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='list-inline top-left-info-links']/li/a"));
////            topMenu.get(i).click();
////            Assert.assertEquals(driver.getCurrentUrl(), PAGE_LINK + urls[i]);
////
////        }
//
  //  }
}
