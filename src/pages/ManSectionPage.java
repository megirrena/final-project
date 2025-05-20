package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ManSectionPage {
    WebDriver driver;
    private WebDriverWait wait;


    private By manLink = By.xpath("//a[@class='level0 has-children'][normalize-space()='Men']");
    private By viewAllMenLink = By.xpath("//a[normalize-space()='View All Men']");
    private By blackColorLink = By.xpath("//img[@title='Black']");
    private By products = By.cssSelector(".products-grid .item");
    private By blackProducts = By.xpath(".//li[contains(@class, 'option-black') and contains(@class, 'selected')]");
    private By priceSelect = By.xpath("//dd[2]//ol[1]//li[1]//a[1]");



    public ManSectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverOverManSection() {
        WebElement ManSection = wait.until(ExpectedConditions.elementToBeClickable(manLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(ManSection).perform();
        driver.findElement(manLink).click();
    }

    public void viewAllMenSection() {
        driver.findElement(viewAllMenLink).click();
    }

    public void selectBlackColor() {
        WebElement blackColor = driver.findElement(blackColorLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blackColor);
        blackColor.click();
    }


    public boolean allProductsHaveBlackSelected() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));

        List<WebElement> productElements = driver.findElements(products);

        for (WebElement product : productElements) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);

                wait.until(ExpectedConditions.visibilityOf(product));

                List<WebElement> selectedBlackSwatches = product.findElements(blackProducts);

                if (selectedBlackSwatches.isEmpty()) {
                    return false;
                }
            } catch (StaleElementReferenceException e) {
                return allProductsHaveBlackSelected();
            }
        }
        return true;
    }



    public void selectPrice() {
        driver.findElement(priceSelect).click();
    }

    public int getNumberOfDisplayedProducts() {
        List<WebElement> productElements = driver.findElements(products);
        return productElements.size();
    }


    public List<Double> getDisplayedProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector("span.price"));
        return priceElements.stream()
                .map(WebElement::getText)
                .map(priceText -> priceText.replace("$", "").trim()) // Remove $ and whitespace
                .map(Double::parseDouble)
                .toList();
    }

}
