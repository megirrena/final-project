package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.RegisterPage;

public class LogInTest extends BaseTest {

    private HomePage homePage;
    private LogInPage logInPage;
    private RegisterPage registerPage;


    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        logInPage = new LogInPage(driver);
    }

    @Test
    public void testLogIn() {
        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi99@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();

        String actualUserName = logInPage.getUserName();
        Assert.assertTrue(actualUserName.toUpperCase().contains("MEGI"), "User name is not displayed correctly after login. Found: " + actualUserName);

        homePage.clickAccountButton();
        registerPage.clickLogOutButton();

    }
}
