package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EmptyShoppingCardPage extends BasePage {

    private By deleteButtons = By.cssSelector("td[class='a-center product-cart-remove last'] a[title='Remove Item']");
    private By shoppingCartItems = By.cssSelector("table#shopping-cart-table tbody tr");
    private By messageEmptyCart = By.xpath("//div[@class='cart-empty']//p[contains(text(),'You have no items in your shopping cart.')]");

    public EmptyShoppingCardPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(shoppingCartItems);
        return items.size();
    }

    public void deleteFirstItem() {
        List<WebElement> buttons = driver.findElements(deleteButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public boolean isCartEmptyMessageDisplayed() {
        return driver.findElements(messageEmptyCart).size() > 0;
    }
}
