package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By acceptCookies = By.xpath("//input[@id='privacy_pref_optin']");
    private By acceptCookiesButton = By.xpath("//div[@id='consent_prompt_submit']");
    private By accountButton = By.xpath("//span[@class='label'][normalize-space()='Account']");
    private By registerButton = By.xpath("//a[@title='Register']");
    private By loginButton = By.cssSelector("a[title='Log In']");
    private By cartIcon = By.xpath("//span[normalize-space()='Cart']");
    private By viewShoppingCart = By.xpath("//a[@class='cart-link']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        click(acceptCookies);
    }

    public void acceptCookiesButton() {
        click(acceptCookiesButton);
    }

    public void clickAccountButton() {
        click(accountButton);
    }

    public void clickRegisterButton() {
        click(registerButton);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickCartIcon() {
        click(cartIcon);
        click(viewShoppingCart);
    }
}