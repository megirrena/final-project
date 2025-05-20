package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class RegisterPage {
    private WebDriver driver;

    private By titleRegisterPage = By.xpath("//h1[normalize-space()='Create an Account']");
    private By firstName = By.id("firstname");
    private By lastName = By.id("lastname");
    private By email = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmation");
    private By registerButton = By.xpath("//span[contains(text(),'Register')]");
    private By messageAfterRegister = By.xpath("//li[@class='success-msg']//ul//li");
    private By logOutButton = By.xpath("//a[@title='Log Out']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleRegisterPage() {
        return driver.findElement(titleRegisterPage).getText();
    }

    public void fillRegisterForm(String firstNameText, String lastNameText, String emailText, String passwordText) {
        driver.findElement(firstName).sendKeys(firstNameText);
        driver.findElement(lastName).sendKeys(lastNameText);
        driver.findElement(email).sendKeys(emailText);
        driver.findElement(password).sendKeys(passwordText);
        driver.findElement(confirmPassword).sendKeys(passwordText);
    }

    public void clickRegister() {
        WebElement registerBtn = driver.findElement(registerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerBtn);
        registerBtn.click();

    }

    public String getMessageAfterRegister() {
        return driver.findElement(messageAfterRegister).getText();
    }
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}
