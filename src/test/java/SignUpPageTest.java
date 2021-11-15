import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpPageTest extends BaseTest{

    @BeforeMethod
    void signUpPageSetUp() {
        driver.get(PAGE_LINK + "simpleregister/");
    }

    @Test
    void signUpInvalidTest(){
        driver.findElement(By.xpath("//input[@id='register_telephone']")).sendKeys(LOGIN);
        driver.findElement(By.xpath("//input[@id='register_password']")).sendKeys(WRONG_PASSWORD);
        driver.findElement(By.xpath("//input[@id='register_confirm_password']")).sendKeys(WRONG_PASSWORD);
        driver.findElement(By.xpath("//a[@id='simpleregister_button_confirm']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='pp_phone-error']")).getText(), "Такой номер уже зарегистрирован!\n" + "×");
    }
//    @Test
//    void confirmPassFieldTest(){
//        signUpPage.inpuConfirmPassword(WRONG_PASSWORD);
//        Assert.assertEquals(signUpPage.passwordConfirmErrorText(), "Подтверждение пароля не соответствует паролю!");
//    }
//    @Test
//    void signUpEmptyPhoneTest(){
//        signUpPage.registrationWithInvalidCreds("", WRONG_PASSWORD);
//        Assert.assertEquals(signUpPage.phoneErrorText(), "Номер телефона указан не верно!");
//    }
//    @Test
//    void signUpInvalidPasswordTest(){
//        signUpPage.registrationWithInvalidCreds(LOGIN, SHORT_PASSWORD);
//        Assert.assertEquals(signUpPage.passwordErrorText(), "Пароль должен быть от 4 до 20 символов!");
//     }
}
