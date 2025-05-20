package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SortingPage {
    WebDriver driver;
    private WebDriverWait wait;

    private By sortDropdown = By.xpath("//body/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']/div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select[1]");
    private By addWishlistFirstItem = By.cssSelector(".link-wishlist[href='#'][data-url='https://ecommerce.tealiumdemo.com/wishlist/index/add/product/417/']");
    private By getAddWishlistSecondItem=By.cssSelector(".link-wishlist[href='#'][data-url='https://ecommerce.tealiumdemo.com/wishlist/index/add/product/420/']");
    private  By numberOfWishlist=By.cssSelector("a[title='My Wishlist (2 items)']");

    public SortingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public void selectSortByPrice() {
        WebElement dropdownElement = driver.findElement(sortDropdown);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Price");
    }


    public boolean isPriceSorted(boolean ascending) {
        // First locate at least one price element to scroll to
        List<WebElement> priceElements = driver.findElements(By.cssSelector("span[id='product-price-426'] span[class='price']"));


        // Scroll to the first price element to ensure all elements are loaded
        WebElement firstPriceElement = priceElements.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstPriceElement);

        // Refresh the list after scrolling (in case of lazy loading)
        priceElements = driver.findElements(By.cssSelector("span[id='product-price-426'] span[class='price']"));

        // Extract prices as doubles
        List<Double> prices = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            try {
                prices.add(Double.parseDouble(priceText));
            } catch (NumberFormatException e) {
                System.out.println("Warning: Could not parse price from text: " + priceText);
            }
        }


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
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(addWishlistFirstItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element1);
        element1.click();

    }

    public void clickAddWishlistSecondItem() {
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(getAddWishlistSecondItem));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element2);
        element2.click();

    }

    public String getWishlistItemCount() {
        By wishlistLocator = By.cssSelector("a[title='My Wishlist (2 items)']");
        WebElement wishlistLink = wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistLocator));
        return wishlistLink.getText();
    }

}