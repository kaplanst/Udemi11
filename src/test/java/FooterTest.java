import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FooterTest extends BaseTest {

    @BeforeMethod
    public void SetUpLoginPage(){
        driver.get(PAGE_LINK);
    }

    @Test
    void leftColumnMenuTest() {
        for (int i = 1; i < 5; i++) {
            String str = "//footer//div[2]/div[1]/ul/li[" + i + "]/a";
            WebElement fo1 = driver.findElement(By.xpath(str));
            String tempo = fo1.getText();
            fo1.click();
            Assert.assertEquals(tempo, driver.findElement(By.xpath("//h1")).getText());
        }
    }
//    public String MenuTest(String menu){
//        WebElement fo1 = driver.findElement(By.xpath(menu));
//        fo1.click();
//        return driver.findElement(By.xpath("//h1")).getText();
//        }
}
