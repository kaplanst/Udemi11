import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.xpath("//a[@onclick='get_oct_popup_login();']");
    private By SignUpButton = By.xpath("//*[text()='Регистрация']");
    private By SignInPageLink = By.xpath("//ul[@class='list-unstyled']//a[@href='https://vkitae.kz/index.php?route=account/account']");

    private By personalAccount = By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']");
    private By loginField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By submitButton = By.xpath("//button[@id='popup-login-button']");
    private By quitButton = By.xpath("//*[text()='Выйти']");

    private By signInPopUpWindowText = By.xpath("//p[@class='blue']");
    private By signInPopUpWindowErrorText = By.xpath("//div[@class='text-danger']");

    public MainPage clickLoginButton(){ // Open login popUp window
        driver.findElement(personalAccount).click();
        driver.findElement(loginButton).click();
        return this;
    }
    public SignUpPage clickSignUpButton(){ // Open signUp popUp window
        driver.findElement(personalAccount).click();
        driver.findElement(SignUpButton).click();
        return new SignUpPage(driver);
    }
    public LoginPage clickSignUpLink(){ // Open login/signUp page
        driver.findElement(SignInPageLink).click();
        return new LoginPage(driver);
    }
    public String getPopupWindowText(){ //get sign in Popup Window Text
        return driver.findElement(signInPopUpWindowText).getText();
    }
    public MainPage inputUserName(String username){ //insert username to login field
        driver.findElement(loginField).sendKeys(username);
        return this;
    }
    public MainPage inputPassword(String password){  //insert password to password field
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }
    public MainPage signInPopupWindow(String username, String password) {  //signIn using Popup Window
        this.clickLoginButton();
        this.inputUserName(username);
        this.inputPassword(password);
        driver.findElement(submitButton).click();
        return this;
    }
    public boolean findQuitButton(){
       if (driver.findElements(quitButton).size() > 0) return true;
       return false;
    }
    public String getErrorText(){
       return driver.findElement(signInPopUpWindowErrorText).getText();
    }

}
