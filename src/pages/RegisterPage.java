package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
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
        super(driver);
    }

    public String getTitleRegisterPage() {
        return getText(titleRegisterPage);
    }

    public void fillRegisterForm(String firstNameText, String lastNameText, String emailText, String passwordText) {
        type(firstName, firstNameText);
        type(lastName, lastNameText);
        type(email, emailText);
        type(password, passwordText);
        type(confirmPassword, passwordText);
    }

    public void clickRegister() {
        scrollToElement(registerButton);
        click(registerButton);
    }

    public String getMessageAfterRegister() {
        return getText(messageAfterRegister);
    }

    public void clickLogOutButton() {
        click(logOutButton);
    }
}