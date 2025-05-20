package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import java.util.List;

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
    public void testBlackColorFilterAndPrice() {

        manSectionPage.hoverOverManSection();
        manSectionPage.viewAllMenSection();
        manSectionPage.selectBlackColor();

        List<WebElement> products = manSectionPage.getDisplayedProducts();
        Assert.assertFalse(products.isEmpty(), "No black color products are displayed.");

        products.forEach(product ->
                Assert.assertTrue(manSectionPage.isSelectedColorBorderBlue(product),
                        "Product does not have a blue border.")
        );

        manSectionPage.selectPrice();
        Assert.assertEquals(manSectionPage.getNumberOfDisplayedProducts(), 3);

        manSectionPage.getDisplayedProductPrices().forEach(price ->
                Assert.assertTrue(price >= 70.00,
                        "Price is below expected minimum of $70.00: $" + price)
        );
    }

}
