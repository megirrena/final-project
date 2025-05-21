package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage {
    private By emailAddress = By.xpath("//input[@id='email']");
    private By password = By.id("pass");
    private By loginButton = By.id("send2");
    private By userName = By.xpath("//p[@class='welcome-msg']");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void setEmailAdress(String emailAddressText) {
        type(emailAddress, emailAddressText);
    }

    public void setPassword(String passwordText) {
        type(password, passwordText);
    }

    public void setLoginButton() {
        click(loginButton);
    }

    public String getUserName() {
        return getText(userName);
    }

}