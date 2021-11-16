import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FooterTest extends BaseTest {

    @BeforeMethod
    public void SetUpLoginPage(){
        driver.get(PAGE_LINK);
    }

    @Test
    void leftColumnMenuTest(){
        List<WebElement> left = driver.findElements(By.xpath("//footer//div[2]/div[1]/ul/li/a"));
    }
}
