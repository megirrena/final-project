package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class SortingPage extends BasePage {

    private By sortDropdown = By.xpath("//select[contains(@title, 'Sort By')]");
    private By addWishlistFirstItem = By.cssSelector(".link-wishlist[data-url*='product/417/']");
    private By addWishlistSecondItem = By.cssSelector(".link-wishlist[data-url*='product/420/']");
    private By wishlistLocator = By.cssSelector("a[title='My Wishlist (2 items)']");
    private By priceElementsLocator = By.cssSelector("span[id='product-price-426'] span[class='price']");

    public SortingPage(WebDriver driver) {
        super(driver);
    }

    public void selectSortByPrice() {
        WebElement dropdownElement = waitUtils.waitForElementToBeVisible(sortDropdown);
        new Select(dropdownElement).selectByVisibleText("Price");
    }

    public boolean isPriceSorted(boolean ascending) {
        List<WebElement> priceElements = driver.findElements(priceElementsLocator);

        WebElement firstPriceElement = priceElements.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstPriceElement);

        // Extract prices as doubles
        List<Double> prices = new ArrayList<>();


        // Check if the list is sorted
        for (int i = 0; i < prices.size() - 1; i++) {
            if (ascending) {
                // For ascending order, current price should be <= next price
                if (prices.get(i) > prices.get(i + 1)) {
                    return false;
                }
            } else {
                // For descending order, current price should be >= next price
                if (prices.get(i) < prices.get(i + 1)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void clickAddWishlistFirstItem() {
        WebElement item = waitUtils.waitForElementToBeClickable(addWishlistFirstItem);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
        item.click();
    }

    public void clickAddWishlistSecondItem() {
        WebElement item = waitUtils.waitForElementToBeClickable(addWishlistSecondItem);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
        item.click();
    }

    public String getWishlistItemCount() {
        return waitUtils.waitForElementToBeVisible(wishlistLocator).getText();
    }
}
