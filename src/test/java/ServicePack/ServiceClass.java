package ServicePack;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServiceClass {
    public ServiceClass(WebDriver driver) {
        this.driver = driver;
    }
    WebDriver driver;

    public void signIn(String login, String password) {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@class='oct-button']")).click();
    }

    public void signUpData(String login, String password) {
        driver.findElement(By.xpath("//input[@id='register_telephone']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@id='register_password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='register_confirm_password']")).sendKeys(password);
        driver.findElement(By.xpath("//a[@id='simpleregister_button_confirm']")).click();
    }

    public void loginPopupWindow() {
        driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.xpath("//a[@onclick='get_oct_popup_login();']")).click();
    }

    public void popUpLoginWindowInput(String login, String password) {
        loginPopupWindow();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='popup-login-button']")).click();
    }

    public String newTab(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        return driver.getCurrentUrl();
    }

    public void screenshot(String nameofCurrMethod){
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String fileName = nameofCurrMethod + format.format(dateNow) + ".png";

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Screenshots\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
