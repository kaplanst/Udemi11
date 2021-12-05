package ServicePack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart_Service {
    WebDriver driver;

    public By CONTINUE_BUTTON = By.xpath("//*[text()='Продолжить покупки']");

    public Cart_Service(WebDriver driver) {
        this.driver = driver;
    }

    public int getPrice(WebElement price){
        String priceTotal = price.getText();
        priceTotal = priceTotal.replace("тг.", "");
        priceTotal = priceTotal.replace("руб.", "");
        priceTotal = priceTotal.replace(" ", "");
        return Integer.valueOf(priceTotal);
    }

    public void cartPopupWindow() {
        driver.findElement(By.xpath("//span[@id='cart-total']/i")).click();
    }

    public void cartContinueButtonClick() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void checkoutButtonClick() {
        driver.findElement(By.xpath("//a[@href='https://vkitae.kz/simplecheckout/']")).click();
    }

    public void addToCart(String item) {
        driver.get(item);
        driver.findElement(By.xpath("//a[@id='button-cart']")).click();
    }


}
