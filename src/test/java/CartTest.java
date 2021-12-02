import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CartTest extends BaseTest {

    private static final By CONTINUE_BUTTON = By.xpath("//*[text()='Продолжить покупки']");

    private static final String ITEMS_AMOUNT = "8";
    private static final String ITEM_1 = "https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3670-bilayt";
    private static final String ITEM_2 = "https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3624-kapsuly-l-karnitin";
    private static final String ITEM_3 = "https://vkitae.kz/rossiyskaya-kosmetika/rus-uhod-za-telom/rus-skraby-i-pilingi/6297-compliment";
    private static String[] locatorItems = {ITEM_1, ITEM_2, ITEM_3};

    private static void cartPopupWindow() {
        driver.findElement(By.xpath("//span[@id='cart-total']/i")).click();
    }

    private static void cartContinueButtonClick() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    private static void checkoutButtonClick() {
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/simplecheckout/']")).click();
    }

    private static void addToCart(String item) {
        driver.get(item);
        driver.findElement(By.xpath("//a[@id='button-cart']")).click();
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
        addToCart(ITEM_1);
        cartContinueButtonClick();
        String items = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(items, "1");
    }

    @Test
    public void addSeveralSameItemsToCartTest() throws InterruptedException {
        addToCart(ITEM_1);
        driver.findElement(By.xpath("//input[@name='quantity'][@class='form-control']")).sendKeys(Keys.BACK_SPACE, ITEMS_AMOUNT, Keys.ENTER);
        Thread.sleep(500);
        String itemsInList = driver.findElement(By.xpath("//*[@class='popup-text']//span[1]")).getText();
        String itemsInCart = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(itemsInList, ITEMS_AMOUNT + " товаров");
        Assert.assertEquals(itemsInCart, ITEMS_AMOUNT);
    }

    @Test
    public void transferToCheckoutPageTest() {
        addToCart(ITEM_1);
        checkoutButtonClick();
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Оформление заказа");
    }

    @Test
    public void addItemsToCheckoutPageTest(){
        for (String i : locatorItems) {
            addToCart(i);
         }
        checkoutButtonClick();
        for (String i : locatorItems) {
            String path = "//*[@href='" + i + "']";
            Assert.assertTrue(driver.findElements(By.xpath(path)).size() == 3);
        }
    }

}
