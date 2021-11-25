import ServicePack.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void SetUpLoginPage() {
        driver.get(PAGE_LINK);
    }
    @Test
    public void itemMenuTest() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@href='javascript:void(0);']"))).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//li[@class='second-level-li has-child']/a[text()='Новинки']"))).build().perform();
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/new_product/novinki-odezhdy/']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-sm-12']/h1")).getText(), "Новинки одежды");
    }

    @Test
    public void popularItemsTest(){
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/popular_products/']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-sm-12']/h1")).getText(), "Популярные товары");
    }

    @Test
    public void menuTest() {
        String[] arr = {"Популярные товары", "Новинки товара-vkitae.kz", "Все акции",
                "Уцененный товар", "Секреты долголетия - vkitae.kz", "Как оформить заказ на сайте vkitae.kz"};
        for (int i = 0; i < arr.length; i++) {
            List<WebElement> allMenu = driver.findElements(By.xpath("//ul[@class='nav navbar-nav flex menu']/li/a"));
            allMenu.get(i+2).click();
            Assert.assertTrue(arr[i].equals(driver.getTitle()));
        }
    }

    @Test
    public void topMenuTest() {
        String[] urls = {"tovary-optom-iz-kitaya", "dostavka", "oplata", "contact-us/", "reviews/"};
        for (int i = 0; i < urls.length; i++) {
            List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='list-inline top-left-info-links']/li/a"));
            topMenu.get(i).click();
            if (driver.getCurrentUrl().equals(PAGE_LINK + urls[i])) serviceClass.screenshot("topMenuTest");
            Assert.assertEquals(driver.getCurrentUrl(), PAGE_LINK + urls[i]);
        }
    }

    @Test
    void loginLinkTest(){
        driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[@href='https://vkitae.kz/index.php?route=account/account']")).click();
        if (!(driver.findElements(By.xpath("//*[text()='Я уже зарегистрирован']")).size() > 0)) serviceClass.screenshot("loginLinkTest");
        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='Я уже зарегистрирован']")).size() > 0);

    }

}
