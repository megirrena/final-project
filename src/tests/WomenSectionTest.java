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
        // Hover over "Women" and click "View All Women"
        womenSectionPage.hoverOverWomenMenu();
        womenSectionPage.clickViewAllWomen();

        // Check if hover effect applies the correct border color
        womenSectionPage.hoverOverFirstProduct();

        // Assert that the border color changes to #3399cc (or rgb(51, 153, 204))
        Assert.assertTrue(womenSectionPage.hasHoverBorderColor(),
                "Product hover effect not applying expected border color (#3399cc)");
    }
}