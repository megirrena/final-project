package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ShoppingCardTest extends BaseTest {
    private HomePage homePage;
    private LogInPage logInPage;
    private WomenSectionPage womenSectionPage;
    private SortingPage sortingPage;
    private ShoppingCardPage shoppingCardPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        womenSectionPage = new WomenSectionPage(driver);
        sortingPage = new SortingPage(driver);
        shoppingCardPage = new ShoppingCardPage(driver);

        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi9@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();

        homePage.clickAccountButton();

    }

    @Test
    public void testShoppingCart() {
        shoppingCardPage.clickMyWishList();
        shoppingCardPage.addToCartFirstItem();
        shoppingCardPage.selectColor1();
        shoppingCardPage.selectSize1();
        shoppingCardPage.clickFinalAddButton();

        String successMessage1 = shoppingCardPage.getAddedCartSuccess();
        Assert.assertTrue(successMessage1.contains("was added to your shopping cart."), "First product was not added to cart.");

        homePage.clickAccountButton();
        shoppingCardPage.clickMyWishList();

        shoppingCardPage.addToCartSecondItem();
        shoppingCardPage.selectColor2();
        shoppingCardPage.selectSize2();
        shoppingCardPage.clickFinalAddButton();

        String successMessage2 = shoppingCardPage.getAddedCartSuccess();
        Assert.assertTrue(successMessage2.contains("was added to your shopping cart."), "Second product was not added to cart.");

        shoppingCardPage.clickEditLink();
        shoppingCardPage.setUpdatedQuantity("2");
        shoppingCardPage.clickUpdateButton();

        boolean pricesMatch = shoppingCardPage.verifyPricesSumToGrandTotal();
        Assert.assertTrue(pricesMatch, "The sum of individual item prices does not match the grand total");
    }
}
