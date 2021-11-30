import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class CartTest extends BaseTest {

    private static final By CONTINUE_BUTTON = By.xpath("//*[text()='Продолжить покупки']");

    private static final String ITEMS_AMOUNT = "8";

    private static void cartPopupWindow(){
        driver.findElement(By.xpath("//span[@id='cart-total']/i")).click();
    }

    private static void cartContinueButtonClick(){
        driver.findElement(CONTINUE_BUTTON).click();
    }


    @BeforeMethod
    public void SetUpLoginPage() {
        driver.get(PAGE_LINK);
    }
    @Test
    public void cartPopupWindowTest() {
        cartPopupWindow();
        Assert.assertTrue(driver.findElement(CONTINUE_BUTTON).isDisplayed());
    }

    @Test
    public void cartPopupWindowCloseTest() {
        cartPopupWindow();
        cartContinueButtonClick();
        Assert.assertFalse(driver.findElement(CONTINUE_BUTTON).isDisplayed());
    }

    @Test
    public void addItemToCartTest() {
        driver.get("https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3670-bilayt");
        driver.findElement(By.xpath("//a[@id='button-cart']")).click();
        cartContinueButtonClick();
        String items = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(items, "1");
    }

    @Test
    public void addSeveralSameItemsToCartTest() throws InterruptedException {
        driver.get("https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3670-bilayt");
        driver.findElement(By.xpath("//a[@id='button-cart']")).click();
        driver.findElement(By.xpath("//input[@name='quantity'][@class='form-control']")).sendKeys(Keys.BACK_SPACE, ITEMS_AMOUNT, Keys.ENTER);
        Thread.sleep(1000);

        cartContinueButtonClick();



        String items = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(items, ITEMS_AMOUNT);
    }
}
