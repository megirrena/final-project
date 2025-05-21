package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCardPage extends BasePage {

    private By myWishList = By.xpath("//a[contains(@title,'My Wishlist')]");
    private By addToCartButton1 = By.xpath("//tr[@id='item_2383']//button[@title='Add to Cart' and @class='button btn-cart']");
    private By selectColor1 = By.xpath("//img[@alt='Pink']");
    private By selectSize1 = By.xpath("//span[@class='swatch-label'][normalize-space()='S']");
    private By finalAddButton = By.cssSelector("button[onclick='productAddToCartForm.submit(this)'] span span");
    private By addedCartSuccess = By.cssSelector("li.success-msg span");

    private By addToCartButton2 = By.xpath("//tr[@id='item_2384']//td[@class='wishlist-cell4 customer-wishlist-item-cart']//div[@class='cart-cell']//button[@title='Add to Cart']//span//span[contains(text(),'Add to Cart')]");
    private By selectColor2 = By.xpath("//img[@alt='White']");
    private By selectSize2 = By.xpath("//span[@class='swatch-label'][normalize-space()='M']");

    private By editLink = By.cssSelector("tr td.product-cart-actions a[title='Edit item parameters']");
    private By updateQuantityField = By.id("qty");
    private By updateButton = By.xpath("//span[contains(text(),'Update Cart')]");

    private By subtotalPrice1 = By.xpath("//span[contains(text(),'$300.00')]");
    private By subtotalPrice2 = By.xpath("//td[@class='product-cart-total']//span[@class='price'][normalize-space()='$185.00']");
    private By grandTotalPrice = By.cssSelector("strong span[class='price']");

    public ShoppingCardPage(WebDriver driver) {
        super(driver);
    }

    public void clickMyWishList() {
        waitUtils.waitForElementToBeClickable(myWishList).click();
    }

    public void addToCartFirstItem() {
        waitUtils.waitForElementToBeClickable(addToCartButton1).click();
    }

    public void selectColor1() {
        waitUtils.waitForElementToBeClickable(selectColor1).click();
    }

    public void selectSize1() {
        waitUtils.waitForElementToBeClickable(selectSize1).click();
    }

    public void addToCartSecondItem() {
        waitUtils.waitForElementToBeClickable(addToCartButton2).click();
    }

    public void selectColor2() {
        waitUtils.waitForElementToBeClickable(selectColor2).click();
    }

    public void selectSize2() {
        waitUtils.waitForElementToBeClickable(selectSize2).click();
    }

    public void clickFinalAddButton() {
        waitUtils.waitForElementToBeClickable(finalAddButton).click();
    }

    public String getAddedCartSuccess() {
        return waitUtils.waitForElementToBeVisible(addedCartSuccess).getText();
    }

    public void clickEditLink() {
        waitUtils.waitForElementToBeClickable(editLink).click();
    }

    public void setUpdatedQuantity(String quantity) {
        WebElement quantityField = waitUtils.waitForElementToBeVisible(updateQuantityField);
        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    public void clickUpdateButton() {
        waitUtils.waitForElementToBeClickable(updateButton).click();
    }

    public boolean verifyPricesSumToGrandTotal() {
        double price1 = Double.parseDouble(waitUtils.waitForElementToBeVisible(subtotalPrice1).getText().replace("$", "").trim());
        double price2 = Double.parseDouble(waitUtils.waitForElementToBeVisible(subtotalPrice2).getText().replace("$", "").trim());
        double actualGrandTotal = Double.parseDouble(waitUtils.waitForElementToBeVisible(grandTotalPrice).getText().replace("$", "").trim());

        double expectedTotal = price1 + price2;
        return Math.abs(expectedTotal - actualGrandTotal) < 0.01;
    }
}
