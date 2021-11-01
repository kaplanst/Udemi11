import org.testng.Assert;
import org.testng.annotations.*;

public class MainPageTest extends BaseTest{
    MainPage mainPage;


    @BeforeMethod
    void setUpMainPage(){
        driver.get("https://vkitae.kz/");
        mainPage = new MainPage(driver);
    }

    @Test
    void loginPopupWindowTest(){
        MainPage mp = mainPage.clickLoginButton();
        Assert.assertEquals(mp.getPopupWindowText(), "Введите логин и пароль");
    }
    @Test
    void loginLinkTest(){
        LoginPage loginPage = mainPage.clickSignUpLink();
        Assert.assertEquals(loginPage.getRegisteredUserText(), "Я уже зарегистрирован");
    }
    @Test
    void signUpInvalidTest(){
        SignUpPage signUpPage = mainPage.clickSignUpButton();
        signUpPage.registrationWithInvalidCreds(LOGIN, WRONG_PASSWORD);
        Assert.assertEquals(signUpPage.existingPhoneErrorText(), "Такой номер уже зарегистрирован!\n" + "×");
    }
    @Test
    void popUpLoginWindowErrorTest(){
        MainPage mp = mainPage.signInPopupWindow(WRONG_LOGIN, WRONG_PASSWORD);
        Assert.assertEquals(mp.getErrorText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }
    @Test
    void popUpLoginWindowTest(){
        MainPage mp = mainPage.signInPopupWindow(LOGIN, PASSWORD);
        Assert.assertTrue(mp.findQuitButton());
    }

    @AfterMethod
    void Bye(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
