package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    WebDriver driver;
   private By emailAdress = By.xpath("//input[@id='email']");
   private By password = By.id("pass");
   private By loginButton = By.id("send2");
   private By userName=By.xpath("//p[@class='welcome-msg']");

   public LogInPage(WebDriver driver) {
       this.driver = driver;
   }
   public void setEmailAdress(String emailAdressText) {
       driver.findElement(emailAdress).sendKeys(emailAdressText);

   }
   public void setPassword(String passwordText) {
       driver.findElement(password).sendKeys(passwordText);
   }

   public void setLoginButton() {
       driver.findElement(loginButton).click();
   }

   public String getUserName() {
       return driver.findElement(userName).getText();
   }

}
