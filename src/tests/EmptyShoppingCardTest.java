package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.EmptyShoppingCardPage;

public class EmptyShoppingCardTest extends BaseTest {
    private HomePage homePage;
    private LogInPage logInPage;
    private EmptyShoppingCardPage emptyShoppingCardPage;

    @BeforeMethod(dependsOnMethods = "setUp") // This ensures BaseTest.setUp() runs first
    public void setUpShoppingCart() { // Renamed method
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        emptyShoppingCardPage = new EmptyShoppingCardPage(driver);

        // Sign in
        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickLoginButton();

        logInPage.setEmailAdress("megi9@test.com");
        logInPage.setPassword("Test@1234");
        logInPage.setLoginButton();

        // Navigate to cart
        homePage.clickCartIcon();
    }

    @Test
    public void testEmptyShoppingCart() throws InterruptedException {
        int previousCount = emptyShoppingCardPage.getCartItemCount();

        while (previousCount > 0) {
            emptyShoppingCardPage.deleteFirstItem();
            Thread.sleep(1000);  // Consider using WebDriverWait instead of Thread.sleep

            int currentCount = emptyShoppingCardPage.getCartItemCount();
            Assert.assertEquals(currentCount, previousCount - 1, "Item was not deleted properly.");
            previousCount = currentCount;
        }

        Assert.assertTrue(emptyShoppingCardPage.isCartEmptyMessageDisplayed(), "Cart empty message not displayed!");
    }
}
