package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WomenSectionPage {
    WebDriver driver;
    private WebDriverWait wait;

    private By womenLink = By.xpath("//a[@class='level0 has-children'][normalize-space()='Women']");
    private By viewAllWomenLink = By.xpath("//a[normalize-space()='View All Women']");
    private By firstProduct = By.xpath("//img[@id='product-collection-image-428']");
    private By productContainer = By.cssSelector(".product-image:hover");

    public WomenSectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverOverWomenMenu() {
        WebElement womenMenu = wait.until(ExpectedConditions.elementToBeClickable(womenLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
    }

    public void clickViewAllWomen() {
        WebElement viewAllWomen = wait.until(ExpectedConditions.elementToBeClickable(viewAllWomenLink));
        viewAllWomen.click();
    }

    public void hoverOverFirstProduct() {
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
    }

    public String getBorderColorAfterHover() {
        WebElement product = driver.findElement(firstProduct);

        // Use JavaScript to get computed style after hover
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
                "var item = arguments[0];" +
                        "var hover = new MouseEvent('mouseover', {bubbles: true});" +
                        "item.dispatchEvent(hover);" +
                        "return window.getComputedStyle(item).getPropertyValue('border-color');",
                product);
    }

    public boolean hasHoverBorderColor() {
        String borderColor = getBorderColorAfterHover();
        // The CSS in your screenshot uses #3399cc
        return borderColor != null &&
                (borderColor.equals("rgb(51, 153, 204)") ||
                        borderColor.toLowerCase().equals("#3399cc"));
    }
}