package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SaleSectionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By saleLink = By.xpath("//a[normalize-space()='Sale']");
    private By viewAllSaleLink = By.xpath("//a[normalize-space()='View All Sale']");
    private By saleProducts = By.cssSelector(".products-grid .item");

    public SaleSectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateSale() {
        WebElement saleMenu = wait.until(ExpectedConditions.elementToBeClickable(saleLink));
        new Actions(driver).moveToElement(saleMenu).perform();

    }
    public void  clickSaleProducts() {
        WebElement viewAll = wait.until(ExpectedConditions.elementToBeClickable(viewAllSaleLink));
        viewAll.click();
    }

    public List<WebElement> getSaleProducts() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(saleProducts));
    }

    public boolean isSaleStyleCorrect(WebElement product) {
        try {
            WebElement original = product.findElement(By.cssSelector(".old-price .price"));
            WebElement discounted = product.findElement(By.cssSelector(".special-price .price"));

            String originalColor = getRgbString(original.getCssValue("color"));
            String originalDecoration = original.getCssValue("text-decoration-line");

            if (originalDecoration == null || originalDecoration.isEmpty()) {
                originalDecoration = original.getCssValue("text-decoration");
            }

            String discountedColor = getRgbString(discounted.getCssValue("color"));
            String discountedDecoration = discounted.getCssValue("text-decoration-line");

            boolean isOriginalCorrect = originalColor.equals("rgb(160, 160, 160)")
                    && originalDecoration.contains("line-through");

            boolean isDiscountedCorrect = (discountedColor.equals("rgb(51, 153, 204)") || discountedColor.equals("#3399cc"))
                    && (discountedDecoration == null || !discountedDecoration.contains("line-through"));

            return isOriginalCorrect && isDiscountedCorrect;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String getRgbString(String color) {
        if (color.startsWith("rgba")) {
            return color.replace("rgba", "rgb").replaceAll(",\\s*\\d+\\.?\\d*\\)", ")");
        }
        return color.trim();
    }
}

