package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import java.util.List;
import static org.testng.Assert.assertTrue;

public class ManSectionTest extends BaseTest {
    private HomePage homePage;
    private LogInPage logInPage;
    private ManSectionPage manSectionPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        manSectionPage = new ManSectionPage(driver);

        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi9@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();
    }

    @Test
    public void testBlackColorFilterHighlightsSelectedSwatch() throws InterruptedException {
        ManSectionPage manSectionPage = new ManSectionPage(driver);

        manSectionPage.hoverOverManSection();
        manSectionPage.viewAllMenSection();

        manSectionPage.selectBlackColor();
        boolean result = manSectionPage.allProductsHaveBlackSelected();
        assertTrue(result, "Not all products have black color swatch selected with blue border.");

        manSectionPage.selectPrice();

        int actualCount = manSectionPage.getNumberOfDisplayedProducts();
        int expectedCount = 3;
        assertTrue(actualCount == expectedCount,
                "Expected " + expectedCount + " products, but found " + actualCount);

        List<Double> prices = manSectionPage.getDisplayedProductPrices();
        for (double price : prices) {
            assertTrue(price >= 70.00, "Found price below $70.00: $" + price);
        }
    }
}
