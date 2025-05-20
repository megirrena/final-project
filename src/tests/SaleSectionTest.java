package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.SaleSectionPage;
import org.openqa.selenium.WebElement;
import utils.TestListener;

public class SaleSectionTest extends BaseTest {
    private HomePage homePage;
    private LogInPage logInPage;
    private SaleSectionPage saleSectionPage;

    @BeforeMethod
    public void init() {
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        saleSectionPage = new SaleSectionPage(driver);

        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi9@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();
    }

    @Test
    public void testSaleProductsStyle() {
        saleSectionPage.navigateSale();
        saleSectionPage.clickSaleProducts();
        for (WebElement product : saleSectionPage.getSaleProducts()) {
            Assert.assertTrue(
                    saleSectionPage.isSaleStyleCorrect(product),
                    "Incorrect price style found on a sale product."
            );
        }
    }
}
