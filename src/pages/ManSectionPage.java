package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ManSectionPage  extends BasePage {

    private By manLink = By.xpath("//a[@class='level0 has-children'][normalize-space()='Men']");
    private By viewAllMenLink = By.xpath("//a[normalize-space()='View All Men']");
    private By blackColorLink = By.xpath("//img[@title='Black']");
    private By borderedProducts = By.cssSelector(".option-black.is-media.filter-match.selected .swatch-link");
    private By blackProducts = By.xpath(".//li[contains(@class, 'option-black') and contains(@class, 'selected')]");
    private By priceSelect = By.xpath("//dd[2]//ol[1]//li[1]//a[1]");
    private By products = By.cssSelector(".products-grid .item");


    public ManSectionPage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverManSection() {
        WebElement manSection = waitUtils.waitForElementToBeClickable(manLink);
        Actions actions = new Actions(driver);
        actions.moveToElement(manSection).perform();
    }

    public void viewAllMenSection() {
        click(viewAllMenLink);
    }
    public void selectBlackColor() {
        scrollToElement(blackColorLink);
        click(blackColorLink);
    }

    public boolean isSelectedColorBorderBlue(WebElement product) {
        try {
            WebElement selectedSwatch = product.findElement(borderedProducts);

            String borderColor = selectedSwatch.getCssValue("border-color");
            return borderColor.contains("51, 153, 204");

        } catch (NoSuchElementException e) {
            System.out.println("Selected swatch not found for product.");
            return false;
        }
    }

    public List<WebElement> getDisplayedProducts() {
        return waitUtils.waitForElementsToBeVisible(blackProducts);
    }

    public void selectPrice() {
        click(priceSelect);
    }

    public int getNumberOfDisplayedProducts() {
        List<WebElement> productElements = driver.findElements(products);
        return productElements.size();
    }


    public List<Double> getDisplayedProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector("span.price"));
        return priceElements.stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replace("$", "").trim())
                .map(Double::parseDouble)
                .toList();
    }

}
