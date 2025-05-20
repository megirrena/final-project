package tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;
import utils.TestListener;

public class RegisterTest extends BaseTest{
    private HomePage homePage;
    private RegisterPage registerPage;

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testRegister() {
        homePage.acceptCookies();
        homePage.acceptCookiesButton();
        homePage.clickAccountButton();
        homePage.clickRegisterButton();

        Assert.assertEquals(registerPage.getTitleRegisterPage(), "CREATE AN ACCOUNT");

        registerPage.fillRegisterForm("Megi", "Rrena", "megi9@test.com", "Test@1234");
        registerPage.clickRegister();

        Assert.assertEquals(registerPage.getMessageAfterRegister(), "Thank you for registering with Tealium Ecommerce.");
        homePage.clickAccountButton();
        registerPage.clickLogOutButton();
    }
}

