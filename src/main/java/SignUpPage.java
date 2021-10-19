import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By registrationSign = By.xpath("//div[@id='content']/h1");
    private By phoneInputField = By.xpath("//input[@id='register_telephone']");
    private By newPasswordField = By.xpath("//input[@id='register_password']");
    private By confirmPasswordField = By.xpath("//input[@id='register_confirm_password']");
    private By registrationButton = By.xpath("//a[@id='simpleregister_button_confirm']");

    private By phoneError = By.xpath("//div[@class='simplecheckout-error-text simplecheckout-rule'][@data-length-min='11']");
    private By passwordError = By.xpath("//div[@class='simplecheckout-error-text simplecheckout-rule'][@data-length-min='4']");
    private By passwordConfirmError = By.xpath("//div[@class='simplecheckout-error-text simplecheckout-rule'][@data-equal='register_password']");
    private By existingPhone = By.xpath("//*[@id='pp_phone-error']");


    public SignUpPage inputPhone(String username){ //insert username to login field
        driver.findElement(phoneInputField).sendKeys(username);
        return this;
    }
    public SignUpPage inputPassword(String password){  //insert password to password field
        driver.findElement(newPasswordField).sendKeys(password);
        return this;

    }public SignUpPage confirmPassword(String password){  //insert password to password field
        driver.findElement(confirmPasswordField).sendKeys(password);
        return this;
    }
    public SignUpPage registrationWithInvalidCreds(String username, String password){
        this.inputPhone(username);
        this.inputPassword(password);
        this.confirmPassword(password);
        driver.findElement(registrationButton).click();
        return new SignUpPage(driver);
    }
    public String getHeaderText(){
        return driver.findElement(registrationSign).getText();
    }
    public String phoneErrorText(){
        return driver.findElement(phoneError).getText();
    }
    public String passwordErrorText(){
        return driver.findElement(passwordError).getText();
    }
    public String passwordConfirmErrorText(){
        return driver.findElement(passwordConfirmError).getText();
    }
    public String existingPhoneErrorText(){
        return driver.findElement(existingPhone).getText();
    }

}
