
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCardPage {
    WebDriver driver;
    private WebDriverWait wait;

    private By myWishList = By.xpath("//a[contains(@title,'My Wishlist')]");

    private By addToCartButton1 = By.xpath("//tr[@id='item_2169']//button[@title='Add to Cart' and @class='button btn-cart']");
    private By selectColor1 = By.xpath("//img[@alt='Pink']");
    private By selectSize1 = By.xpath("//span[@class='swatch-label'][normalize-space()='S']");
    private By finalAddButton = By.cssSelector("button[onclick='productAddToCartForm.submit(this)'] span span");
    private By addedCartSuccess = By.cssSelector("li.success-msg span");

    private By addToCartButton2 = By.xpath("//tr[@id='item_2170']//td[@class='wishlist-cell4 customer-wishlist-item-cart']//div[@class='cart-cell']//button[@title='Add to Cart']//span//span[contains(text(),'Add to Cart')]");
    private By selectColor2 = By.xpath("//img[@alt='White']");
    private By selectSize2 = By.xpath("//span[@class='swatch-label'][normalize-space()='M']");

    private By editLink = By.cssSelector("tr td.product-cart-actions a[title='Edit item parameters']");
    private By updateQuantityField = By.id("qty");
    private By updateButton = By.xpath("//span[contains(text(),'Update Cart')]");


    private By subtotalPrice1 = By.xpath("//span[contains(text(),'$300.00')]");
    private By subtotalPrice2 = By.xpath("//td[@class='product-cart-total']//span[@class='price'][normalize-space()='$185.00']");
    private By grandTotalPrice = By.cssSelector("strong span[class='price']");

    public ShoppingCardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickMyWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(myWishList)).click();
    }

    public void addToCartFirstItem() {

        driver.findElement(addToCartButton1).click();
    }

    public void selectColor1() {
        wait.until(ExpectedConditions.elementToBeClickable(selectColor1)).click();
    }

    public void selectSize1() {
        wait.until(ExpectedConditions.elementToBeClickable(selectSize1)).click();
    }


    public void addToCartSecondItem() {
        driver.findElement(addToCartButton2).click();

    }

    public void selectColor2() {
        wait.until(ExpectedConditions.elementToBeClickable(selectColor2)).click();
    }

    public void selectSize2() {
        wait.until(ExpectedConditions.elementToBeClickable(selectSize2)).click();
    }

    public void clickFinalAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finalAddButton)).click();
    }

    public String getAddedCartSuccess() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addedCartSuccess)).getText();
    }

    public void clickEditLink() {
        wait.until(ExpectedConditions.elementToBeClickable(editLink)).click();
    }

    public void setUpdatedQuantity(String quantity) {
        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(updateQuantityField));
        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    public void clickUpdateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }

    public boolean verifyPricesSumToGrandTotal() {
        // Get text values of the subtotal prices
        String priceText1 = driver.findElement(subtotalPrice1).getText().replace("$", "").trim();
        String priceText2 = driver.findElement(subtotalPrice2).getText().replace("$", "").trim();
        String grandTotalText = driver.findElement(grandTotalPrice).getText().replace("$", "").trim();

        // Convert text to double
        double price1 = Double.parseDouble(priceText1);
        double price2 = Double.parseDouble(priceText2);
        double expectedTotal = price1 + price2;

        double actualGrandTotal = Double.parseDouble(grandTotalText);

        // Compare the expected total with the actual grand total, allowing for minor precision differences
        return Math.abs(expectedTotal - actualGrandTotal) < 0.01;
    }

}
