package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WomenSectionPage extends BasePage {

    private By womenLink = By.xpath("//a[@class='level0 has-children'][normalize-space()='Women']");
    private By viewAllWomenLink = By.xpath("//a[normalize-space()='View All Women']");
    private By firstProduct = By.xpath("//img[@id='product-collection-image-428']");

    public WomenSectionPage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverWomenMenu() {
        WebElement womenMenu = waitUtils.waitForElementToBeClickable(womenLink);
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
    }

    public void clickViewAllWomen() {
        click(viewAllWomenLink);
    }

    public boolean hasHoverBorderColor() {
        WebElement product = waitUtils.waitForElementToBeVisible(firstProduct);

        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();

        String borderColor = product.getCssValue("border-color");
        return borderColor != null && borderColor.equals("rgb(51, 153, 204)");
    }
}
