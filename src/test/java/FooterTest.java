import ServicePack.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class FooterTest extends BaseTest {

    static void subscribeWindow() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@sp-show-form='198899']")).click();
    }

    static void subscribeSubmitButton() {
        driver.findElement(By.xpath("//*[@class='sp-form-outer sp-popup-outer sp-show']//*[@id='sp-b3bb3680-4457-4017-87fd-53acaa6fbcbe']")).click();
    }

    static void subscribeLoginField(String email) {
        driver.findElement(By.xpath("//*[@class='sp-form-outer sp-popup-outer sp-show']//*[@placeholder='Введите ваш Email']")).sendKeys(email);
    }

    @BeforeMethod
    public void SetUpLoginPage() {
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
            int links = driver.findElements(By.xpath("//*[text()='" + tempo + "']")).size();
            Assert.assertTrue(links > 3);
        }
    }

    @Test
    void headersMenuTest() {
        String[] headersTemplanes = {"", "ИНФОРМАЦИЯ", "ЛИЧНЫЙ КАБИНЕТ", "НАШИ КОНТАКТЫ", "УЗНАЙТЕ О СКИДКА И АКЦИЯХ ПЕРВЫМИ!", "МЫ В СОЦСЕТЯХ"};
        List<WebElement> headers = driver.findElements(By.xpath("//*[@class='h5']"));
        for (int i = 0; i < headers.size(); i++) {
            Assert.assertEquals(headers.get(i).getText(), headersTemplanes[i]);
        }
    }

    @Test
    void subscribeWindowTest() throws InterruptedException {
        subscribeWindow();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='sp-form-198899']/div/form/div[1]/input")).isDisplayed());
    }

    @Test
    void subscribeWindowEmptyTest() throws InterruptedException {
        subscribeWindow();
        subscribeSubmitButton();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Обязательное поле']")).isDisplayed());
    }

    @Test
    void subscribeWindowWrongTest() throws InterruptedException {
        subscribeWindow();
        subscribeLoginField(WRONG_LOGIN);
        subscribeSubmitButton();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Неверный email-адрес']")).isDisplayed());
    }

    @Test
    void subscribeWindowFakeTest() throws InterruptedException {
        subscribeWindow();
        subscribeLoginField("toyumba36@gmail.com");
        subscribeSubmitButton();
        Assert.assertTrue(driver.findElement(By.xpath("//h3")).isDisplayed());
    }

    @Test
    void watsappButtonTest() {
        String url = newTab("//*[@href='//wa.me/77765440055']");
        Assert.assertEquals(url, "https://api.whatsapp.com/send/?phone=77765440055&text&app_absent=0");
    }

    @Test
    void telegramButtonTest() {
        String url = newTab("//a[@href='//t.me/vkitae_bot']");
        Assert.assertEquals(url, "https://t.me/vkitae_bot");
    }

    public String newTab(String url) {
        driver.findElement(By.xpath(url)).click();
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        return driver.getCurrentUrl();
    }
}
