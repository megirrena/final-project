package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.WomenSectionPage;

public class WomenSectionTest extends BaseTest {
    private HomePage homePage;
    private LogInPage logInPage;
    private WomenSectionPage womenSectionPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        womenSectionPage = new WomenSectionPage(driver);

        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi9@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();
    }

    @Test
    public void testHoverStyleChangeOnProduct() {
        womenSectionPage.hoverOverWomenMenu();
        womenSectionPage.clickViewAllWomen();

        boolean hasHoverEffect = womenSectionPage.hasHoverBorderColor();
        Assert.assertTrue(hasHoverEffect, "Hover effect did not change border color as expected.");
    }
}