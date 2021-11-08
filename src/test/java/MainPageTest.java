import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class MainPageTest extends BaseTest{
    MainPage mainPage;

    public By signInPopUpWindowText = By.xpath("//p[@class='blue']");
    public By signInPopUpWindowErrorText = By.xpath("//div[@class='text-danger']");
    public By cartCurrency = By.xpath("//span[@class='cart-total-price']");

    @BeforeMethod
    void setUpMainPage(){
        driver.get(PAGE_LINK);
        mainPage = new MainPage(driver);
    }

    @Test
    void loginPopupWindowTest(){
        mainPage.clickLoginButton();
        Assert.assertEquals(driver.findElement(signInPopUpWindowText).getText(), "Введите логин и пароль");
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
        mainPage.signInPopupWindow(WRONG_LOGIN, WRONG_PASSWORD);
        Assert.assertEquals(driver.findElement(signInPopUpWindowErrorText).getText(), "Неправильно заполнены поля E-Mail и/или пароль!");
    }
    @Test
    void popUpLoginWindowTest(){
        MainPage mp = mainPage.signInPopupWindow(LOGIN, PASSWORD);
        Assert.assertTrue(mp.findQuitButton());
    }
    @Test
    void currencySwitchTest(){
        mainPage.currencySwitch();
        Assert.assertEquals(driver.findElement(cartCurrency).getText(), "0.00руб.");
    }
    @Test
    void searchItemTest() throws InterruptedException {
        mainPage.itemSearch(ITEM);
        List<WebElement> serCheck = driver.findElements(By.xpath("//div[@id='content']//h4/a"));
        Assert.assertTrue(serCheck.size() > 0);
        for (WebElement i : serCheck) Assert.assertTrue(i.getText().toLowerCase().contains(ITEM));
    }




}
