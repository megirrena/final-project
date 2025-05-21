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
        List<WebElement> priceElements = waitUtils.waitForElementsToBeVisible(priceElementsLocator);

        if (priceElements.isEmpty()) return true;
        scrollToElement(priceElementsLocator);

        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replaceAll("[^\\d.]", "");
            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + priceText);
            }
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            if (ascending && prices.get(i) > prices.get(i + 1)) {
                return false;
            }
            if (!ascending && prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    public void clickAddWishlistFirstItem() {
        waitUtils.waitForElementToBeVisible(addWishlistFirstItem);
        scrollToElement(addWishlistFirstItem);
        click(addWishlistFirstItem);
    }

    public void clickAddWishlistSecondItem() {
        waitUtils.waitForElementToBeVisible(addWishlistSecondItem);
        scrollToElement(addWishlistSecondItem);
        click(addWishlistSecondItem);
    }

    public String getWishlistItemCount() {
        waitUtils.waitForElementToBeVisible(wishlistLocator);
        return getText(wishlistLocator);
    }
}
