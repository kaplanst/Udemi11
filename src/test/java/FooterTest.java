import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FooterTest extends BaseTest {

    @BeforeMethod
    public void SetUpLoginPage(){
        driver.get(PAGE_LINK);
    }

    @Test
    void leftColumnMenuTest() {
        for (int i = 1; i < 5; i++) {
            String str = "//footer//div[2]/div[1]/ul/li[" + i + "]/a";
            WebElement foot1 = driver.findElement(By.xpath(str));
            String tempo = foot1.getText();
            foot1.click();
            Assert.assertEquals(tempo, driver.findElement(By.xpath("//h1")).getText());
        }
    }

    @Test
    void secondColumnMenuTest() throws InterruptedException {
        serviceClass.popUpLoginWindowInput(LOGIN, PASSWORD);
        Thread.sleep(1000);
        for (int i = 1; i < 4; i++) {
            String str = "//footer//div[2]/div[2]/ul/li[" + i + "]/a";
            WebElement foot2 = driver.findElement(By.xpath(str));
            String tempo = foot2.getText();
            foot2.click();
            System.out.println(tempo);
            int links = driver.findElements(By.xpath("//*[text()='"+ tempo + "']")).size();
            Assert.assertTrue(links > 3);
        }
    }
}
