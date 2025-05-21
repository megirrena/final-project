package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.SortingPage;
import pages.WomenSectionPage;


public class SortingTest extends BaseTest {
    private HomePage homePage;
    private LogInPage logInPage;
    private WomenSectionPage womenSectionPage;
    private SortingPage sortingPage;

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
    public void testAddTwoItemsToWishlist() throws InterruptedException {
        sortingPage = new SortingPage(driver);

        womenSectionPage.hoverOverWomenMenu();
        womenSectionPage.clickViewAllWomen();

        sortingPage.selectSortByPrice();
        Assert.assertTrue(sortingPage.isPriceSorted(true), "Prices are not sorted in ascending order.");


        sortingPage.clickAddWishlistFirstItem();

        womenSectionPage.hoverOverWomenMenu();
        womenSectionPage.clickViewAllWomen();
        Thread.sleep(3000);

        sortingPage.selectSortByPrice();

        sortingPage.clickAddWishlistSecondItem();
        Thread.sleep(3000);

        homePage.clickAccountButton();
        String wishlistCount = sortingPage.getWishlistItemCount();
        Assert.assertEquals(wishlistCount, "My Wishlist (2 items)");
    }
}
