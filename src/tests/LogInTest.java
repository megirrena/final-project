package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

public class LogInTest extends BaseTest {

    private HomePage homePage;
    private LogInPage logInPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
    }

    @Test
    public void testLogIn() {
        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi9@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();

        String actualUserName = logInPage.getUserName();
        Assert.assertTrue(actualUserName.toUpperCase().contains("WELCOME"), "User name is not displayed correctly after login. Found: " + actualUserName);
    }
}
