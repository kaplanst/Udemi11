import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {

    WebDriver driver;
    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By submitButton = By.xpath("//input[@class='oct-button']");
    private By registeredText = By.xpath("//*[text()='Я уже зарегистрирован']");
    private By error = By.xpath("//*[@class='alert alert-danger']");
    private By regisrButton = By.xpath("//a[@class='oct-button']");


    public loginPage inputUserName(String username){
        driver.findElement(loginField).sendKeys(username);
        return this;
    }
    public loginPage inputPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }
    public loginPage incorrectLoginCreds(String username, String password) {
        this.inputUserName(username);
        this.inputPassword(password);
        driver.findElement(submitButton).click();
        return new loginPage(driver);
    }
    public String getRegisteredUserText() {
        return driver.findElement(registeredText).getText();
    }
    public String getErrorText() { //error text when login or password incorrect
        return driver.findElement(error).getText();
    }
    public SignUpPage createAccount() { // redirecting to create account page
        driver.findElement(regisrButton).click();
        return new SignUpPage(driver);
    }

}
