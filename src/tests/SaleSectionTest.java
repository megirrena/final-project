package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.SaleSectionPage;

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

        saleSectionPage.getSaleProducts();

        boolean allHavePrices = saleSectionPage.allSaleProductsHaveOriginalAndDiscountedPrice();
        Assert.assertTrue(allHavePrices, "All sale products should have both original and discounted prices");

        boolean originalPriceStyleCorrect = saleSectionPage.checkOriginalPriceStyle();
        Assert.assertTrue(originalPriceStyleCorrect, "Original price should be gray and have strikethrough");

        boolean discountedPriceStyleCorrect = saleSectionPage.checkDiscountedPriceStyle();
        Assert.assertTrue(discountedPriceStyleCorrect, "Discounted price should be blue and not have strikethrough");
    }
}
