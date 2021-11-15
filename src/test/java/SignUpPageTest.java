import ServicePack.ServiceClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpPageTest extends BaseTest {

    @BeforeMethod
    void signUpPageSetUp() {
        driver.get(PAGE_LINK + "simpleregister/");
    }

    @Test
    void signUpInvalidTest(){
        serviceClass.signUpData(LOGIN, PASSWORD);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='pp_phone-error']")).getText(), "Такой номер уже зарегистрирован!\n" + "×");
    }
    @Test
    void confirmPassFieldTest(){
        driver.findElement(By.xpath("//input[@id='register_confirm_password']")).sendKeys(WRONG_PASSWORD);
        String errorText = driver.findElement(By.xpath("//div[@class='simplecheckout-error-text simplecheckout-rule'][@data-equal='register_password']")).getText();
        Assert.assertEquals(errorText, "Подтверждение пароля не соответствует паролю!");
    }
    @Test
    void signUpEmptyPhoneTest(){
        serviceClass.signUpData("", WRONG_PASSWORD);
        String errorPhone = driver.findElement(By.xpath("//div[@class='simplecheckout-error-text simplecheckout-rule'][@data-length-min='11']")).getText();
        Assert.assertEquals(errorPhone, "Номер телефона указан не верно!");
    }

    @Test
    void signUpInvalidPasswordTest(){
        serviceClass.signUpData(LOGIN, SHORT_PASSWORD);
        String shortPhone = driver.findElement(By.xpath("//div[@class='simplecheckout-error-text simplecheckout-rule'][@data-length-min='4']")).getText();
        Assert.assertEquals(shortPhone, "Пароль должен быть от 4 до 20 символов!");
     }
}
