import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
        Thread.sleep(2000);
        String itemsInList = driver.findElement(By.xpath("//*[@class='popup-text']//span[1]")).getText();
        String itemsInCart = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(itemsInList, ITEMS_AMOUNT + " товаров", "Items mount in Popup window is FAILED");
        Assert.assertEquals(itemsInCart, ITEMS_AMOUNT, "Items mount in cart is FAILED");
    }

    @Test
    public void transferToCheckoutPageTest() {
        addToCart(ITEM_2);
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

    @Test
    public void checkOutWithEmptyCredsTest() {
        addToCart(ITEM_3);
        checkoutButtonClick();
        driver.findElement(By.xpath("//a[@id='simplecheckout_button_confirm']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Фамилия должна быть от 2 до 32 символов!']")).isDisplayed(), "Last name alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Имя должно быть от 2 до 32 символов!']")).isDisplayed(), "First name alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Номер телефона указан не верно!']")).isDisplayed(), "Phone alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Некорректный адрес электронной почты!']")).isDisplayed(), "email alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Укажите область!']")).isDisplayed(), "District alert is failed");
    }

    @Test
    public void fakeEmailCheckoutTest() {
        addToCart(ITEM_1);
        checkoutButtonClick();
        driver.findElement(By.xpath("//a[@onclick='PutEmail()']")).click();
        String fakeEmail = driver.findElement(By.xpath("//input[@id='customer_email']")).getAttribute("value");
        Assert.assertEquals(fakeEmail, "client@vkitae.kz");
    }

    @Test
    public void totalPriceTest() {

        int total = 0;
        for (String i : locatorItems) {
            addToCart(i);
        }
        checkoutButtonClick();
        List<WebElement> priceList = driver.findElements(By.xpath("//td[@class='total']"));
        for (WebElement elem: priceList) {
            total += getPrice(elem);
        }
        int finalPrice = getPrice(driver.findElement(By.xpath("//*[@id='total_sub_total']/span[2]")));
        Assert.assertEquals(total, finalPrice);
    }

    public int getPrice(WebElement price){
        String priceTotal = price.getText();
        priceTotal = priceTotal.replace("тг.", "");
        priceTotal = priceTotal.replace("руб.", "");
        priceTotal = priceTotal.replace(" ", "");
        return Integer.valueOf(priceTotal);
    }
}
