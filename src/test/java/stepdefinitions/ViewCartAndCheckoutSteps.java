package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ViewCartAndCheckoutSteps {

    private final WebDriver driver = Hooks.driver;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Then("I navigate to my cart")
    public void i_navigate_to_my_cart() {
        driver.get("https://askomdch.com/cart/");
    }

    @Then("I should see items in my cart")
    public void i_should_see_items_in_my_cart() {
        List<WebElement> cartItems = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart_item"))
        );
        Assert.assertFalse("Cart is empty, but items were expected", cartItems.isEmpty());
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".checkout-button"))
        );
        checkoutButton.click();
    }

    @Then("I should be on the checkout page")
    public void i_should_be_on_the_checkout_page() {
        WebElement placeOrderButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("place_order"))
        );
        Assert.assertEquals("PLACE ORDER", placeOrderButton.getText().trim());
    }
}
