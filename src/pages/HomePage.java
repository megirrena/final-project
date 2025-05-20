package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    private By acceptCookies = By.xpath("//input[@id='privacy_pref_optin']");
    private By acceptCookiesButton = By.xpath("//div[@id='consent_prompt_submit']");
    private By accountButton = By.xpath("//span[@class='label'][normalize-space()='Account']");
    private By registerButton =By.xpath("//a[@title='Register']");
    private By loginButton = By.cssSelector("a[title='Log In']");
    private  By cardIcon= By.xpath("//span[normalize-space()='Cart']");
    private By viewShoppingCard= By.xpath("//a[@class='cart-link']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void acceptCookies() {
        driver.findElement(acceptCookies).click();
    }
    public void acceptCookiesButton() {
        driver.findElement(acceptCookiesButton).click();
    }
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void clickCartIcon(){
        driver.findElement(cardIcon).click();
        driver.findElement(viewShoppingCard).click();
    }
}
