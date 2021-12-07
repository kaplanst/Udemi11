import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Filter extends BaseTest {

    private static final int ITEM_AMOUNT = 25;

    @BeforeMethod
    public void SetUpLoginPage() {
        driver.get(PAGE_LINK + "popular_products/");
    }

    @Test
    public void itemAmountDropDownTest(){
        Select select = new Select(driver.findElement(By.xpath("//select[@id='input-limit']")));
        select.selectByVisibleText(Integer.toString(ITEM_AMOUNT));
        List<WebElement> itemsList = driver.findElements(By.xpath("//div[@class='h4 col-md-12 col-sm-12 col-xs-12']"));
        Assert.assertEquals(itemsList.size(), ITEM_AMOUNT);
    }

    @Test
    public void arrangeItemsByPriceTest() {
        Select select = new Select(driver.findElement(By.xpath("//select[@id='input-sort']")));
        select.selectByVisibleText("Цена, по возрастанию");
        List<WebElement> iList = driver.findElements(By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-6']//span[@class='common-price']"));
        for (int i = 1; i < iList.size(); i++) {
            Assert.assertTrue(serviceClass.getPrice(iList.get(i-1)) <= serviceClass.getPrice(iList.get(i)));
            System.out.println(serviceClass.getPrice(iList.get(i-1)));
        }
    }
    @Test
    public void gridListSwitchTest() {
        driver.findElement(By.xpath("//button[@id='list-view']")).click();
        driver.findElement(By.xpath("//button[@id='grid-view']")).click();
        Assert.assertTrue(driver.findElements(By.xpath("//button[@class='btn btn-default tooltipstered active'][@id='grid-view']")).size() > 0);
        Assert.assertFalse(driver.findElements(By.xpath("//button[@class='btn btn-default tooltipstered active'][@id='list-view']")).size() > 0);
        driver.findElement(By.xpath("//button[@id='list-view']")).click();
        Assert.assertFalse(driver.findElements(By.xpath("//button[@class='btn btn-default tooltipstered active'][@id='grid-view']")).size() > 0);
        Assert.assertTrue(driver.findElements(By.xpath("//button[@class='btn btn-default tooltipstered active'][@id='list-view']")).size() > 0);
    }

}
