package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SaleSectionPage extends BasePage {


    private By saleLink = By.xpath("//a[normalize-space()='Sale']");
    private By viewAllSaleLink = By.xpath("//a[normalize-space()='View All Sale']");
    private By saleProducts = By.cssSelector(".products-grid .item");

    private By oldPrice = By.cssSelector(".old-price .price");
    private By specialPrice = By.cssSelector(".special-price .price");


    public SaleSectionPage(WebDriver driver) {
        super(driver);
    }

    public void navigateSale() {
        WebElement saleMenu = waitUtils.waitForElementToBeClickable(saleLink);
        new Actions(driver).moveToElement(saleMenu).perform();
    }

    public void clickSaleProducts() {
        click(viewAllSaleLink);
    }

    public List<WebElement> getSaleProducts() {
        return waitUtils.waitForElementsToBeVisible(saleProducts);
    }

    public boolean allSaleProductsHaveOriginalAndDiscountedPrice() {
      List<WebElement> products = getSaleProducts();

       for (WebElement product : products) {
        List<WebElement> originalPrice = product.findElements(oldPrice);
        List<WebElement> discountedPrice = product.findElements(specialPrice);

          if (originalPrice.isEmpty() || discountedPrice.isEmpty()) {
            System.out.println("Product missing prices. Found - Original: " + !originalPrice.isEmpty() +
                    ", Discounted: " + !discountedPrice.isEmpty());
            return false;
           }
       }

    return true;
}

 public boolean checkOriginalPriceStyle() {
        WebElement originalPrice = waitUtils.waitForElementToBeVisible(oldPrice);

        String color = originalPrice.getCssValue("color");
        String textDecoration = originalPrice.getCssValue("text-decoration");

        return color.contains("160, 160, 160") && textDecoration.contains("line-through");
    }

    public boolean checkDiscountedPriceStyle() {
        WebElement discountedPrice = waitUtils.waitForElementToBeVisible(specialPrice);

        String color = discountedPrice.getCssValue("color");
        String textDecoration = discountedPrice.getCssValue("text-decoration");

        return color.contains("51, 153, 204") && !textDecoration.contains("line-through");
    }

}

