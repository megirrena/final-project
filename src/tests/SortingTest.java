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

        // Step 1: Add first item
        womenSectionPage.hoverOverWomenMenu();
        womenSectionPage.clickViewAllWomen();
        Thread.sleep(3000);

        sortingPage.selectSortByPrice();
        Thread.sleep(3000);

        sortingPage.clickAddWishlistFirstItem();
        Thread.sleep(3000);

        // Step 2: Add second item (repeat everything)
        womenSectionPage.hoverOverWomenMenu();
        womenSectionPage.clickViewAllWomen();
        Thread.sleep(3000);

        sortingPage.selectSortByPrice();
        Thread.sleep(3000);

        sortingPage.clickAddWishlistSecondItem();
        Thread.sleep(3000);

        // Step 3: Assert wishlist count
        homePage.clickAccountButton();
        String wishlistCount = sortingPage.getWishlistItemCount();
        Assert.assertEquals(wishlistCount, "My Wishlist (2 items)");
    }
}
