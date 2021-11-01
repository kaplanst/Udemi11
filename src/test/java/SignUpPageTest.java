import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpPageTest extends BaseTest{
      SignUpPage signUpPage;

    @BeforeMethod
    void signUpPageSetUp() {
        driver.get("https://vkitae.kz/simpleregister/");
        signUpPage = new SignUpPage(driver);
    }

    @Test
    void confirmPassFieldTest(){
        signUpPage.inpuConfirmPassword(WRONG_PASSWORD);
        Assert.assertEquals(signUpPage.passwordConfirmErrorText(), "Подтверждение пароля не соответствует паролю!");
    }
    @Test
    void signUpEmptyPhoneTest(){
        signUpPage.registrationWithInvalidCreds("", WRONG_PASSWORD);
        Assert.assertEquals(signUpPage.phoneErrorText(), "Номер телефона указан не верно!");
    }
    @Test
    void signUpInvalidPasswordTest(){
        signUpPage.registrationWithInvalidCreds(LOGIN, SHORT_PASSWORD);
        Assert.assertEquals(signUpPage.passwordErrorText(), "Пароль должен быть от 4 до 20 символов!");
    }
    @AfterMethod
    void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
