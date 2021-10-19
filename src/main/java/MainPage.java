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

    public loginPage clickLoginButton(){ // Open login popUp window
        driver.findElement(personalAccount).click();
        driver.findElement(loginButton).click();
        return new loginPage(driver);
    }
    public SignUpPage clickSignUpButton(){ // Open signUp popUp window
        driver.findElement(personalAccount).click();
        driver.findElement(SignUpButton).click();
        return new SignUpPage(driver);
    }
    public SignUpPage clickSignUpLink(){ // Open login/signUp page
        driver.findElement(SignInPageLink).click();
        return new SignUpPage(driver);
    }
    public MainPage inputUserName(String username){ //insert username to login field
        driver.findElement(loginField).sendKeys(username);
        return this;
    }
    public MainPage inputPassword(String password){  //insert password to password field
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }
}
