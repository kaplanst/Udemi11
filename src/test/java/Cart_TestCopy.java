import ServicePack.BaseTest;
import ServicePack.Cart_Service;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class Cart_TestCopy extends BaseTest {

    Cart_Service cart_service;

    private static final String ITEMS_AMOUNT = "8";
    private static final String ITEM_1 = "https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3670-bilayt";
    private static final String ITEM_2 = "https://vkitae.kz/sredstva-dlya-pohudeniya/kapsulyi-dlya-pohudeniya/3624-kapsuly-l-karnitin";
    private static final String ITEM_3 = "https://vkitae.kz/rossiyskaya-kosmetika/rus-uhod-za-telom/rus-skraby-i-pilingi/6297-compliment";
    private static String[] locatorItems = {ITEM_1, ITEM_2, ITEM_3};


    @BeforeMethod
    public void SetUpLoginPage() {
        cart_service = new Cart_Service(driver);
        driver.get(PAGE_LINK);
    }

    @Test
    public void cartPopupWindowTest() {
        cart_service.cartPopupWindow();
        Assert.assertTrue(driver.findElement(cart_service.CONTINUE_BUTTON).isDisplayed());
    }

    @Test
    public void cartPopupWindowCloseTest() {
        cart_service.cartPopupWindow();
        cart_service.cartContinueButtonClick();
        Assert.assertFalse(driver.findElement(cart_service.CONTINUE_BUTTON).isDisplayed());
    }

    @Test
    public void addItemToCartTest() {
        cart_service.addToCart(ITEM_1);
        cart_service.cartContinueButtonClick();
        String items = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(items, "1");
    }

    @Test
    public void addSeveralSameItemsToCartTest() throws InterruptedException {
        cart_service.addToCart(ITEM_1);
        driver.findElement(By.xpath("//input[@name='quantity'][@class='form-control']")).sendKeys(Keys.BACK_SPACE, ITEMS_AMOUNT, Keys.ENTER);
        Thread.sleep(2000);
        String itemsInList = driver.findElement(By.xpath("//*[@class='popup-text']//span[1]")).getText();
        String itemsInCart = driver.findElement(By.xpath("//*[@class='count-quantity']")).getText();
        Assert.assertEquals(itemsInList, ITEMS_AMOUNT + " товаров", "Items mount in Popup window is FAILED");
        Assert.assertEquals(itemsInCart, ITEMS_AMOUNT, "Items mount in cart is FAILED");
    }

    @Test
    public void transferToCheckoutPageTest() {
        cart_service.addToCart(ITEM_2);
        cart_service.checkoutButtonClick();
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Оформление заказа");
    }

    @Test
    public void addItemsToCheckoutPageTest(){
        for (String i : locatorItems) {
            cart_service.addToCart(i);
         }
        cart_service.checkoutButtonClick();
        for (String i : locatorItems) {
            String path = "//*[@href='" + i + "']";
            Assert.assertTrue(driver.findElements(By.xpath(path)).size() == 3);
        }
    }

    @Test
    public void checkOutWithEmptyCredsTest() {
        cart_service.addToCart(ITEM_3);
        cart_service.checkoutButtonClick();
        driver.findElement(By.xpath("//a[@id='simplecheckout_button_confirm']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Фамилия должна быть от 2 до 32 символов!']")).isDisplayed(), "Last name alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Имя должно быть от 2 до 32 символов!']")).isDisplayed(), "First name alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Номер телефона указан не верно!']")).isDisplayed(), "Phone alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Некорректный адрес электронной почты!']")).isDisplayed(), "email alert is failed");
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Укажите область!']")).isDisplayed(), "District alert is failed");
    }

    @Test
    public void fakeEmailCheckoutTest() {
        cart_service.addToCart(ITEM_1);
        cart_service.checkoutButtonClick();
        driver.findElement(By.xpath("//a[@onclick='PutEmail()']")).click();
        String fakeEmail = driver.findElement(By.xpath("//input[@id='customer_email']")).getAttribute("value");
        Assert.assertEquals(fakeEmail, "client@vkitae.kz");
    }

    @Test
    public void totalPriceTest() {
        int totalPrice = 0;
        for (String i : locatorItems) cart_service.addToCart(i);
        cart_service.checkoutButtonClick();
        List<WebElement> priceList = driver.findElements(By.xpath("//td[@class='total']"));
        for (WebElement elem: priceList) totalPrice += cart_service.getPrice(elem);
        Assert.assertEquals(totalPrice, cart_service.getPrice(driver.findElement(By.xpath("//*[@id='total_sub_total']/span[2]"))));
    }

}
