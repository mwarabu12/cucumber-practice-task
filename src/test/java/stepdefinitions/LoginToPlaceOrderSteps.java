package stepdefinitions;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LoginToPlaceOrderSteps {
    private String username = "Isaro";
    private String password = "I$ar0_2026!Rocks#";
    private final WebDriver driver = Hooks.driver;


    @Given("products already exist in my cart")
    public void products_already_exist_in_my_cart() {
        openAccountPageToLogin();
        openCartPage();
        List<WebElement> items = driver.findElements(By.className("product-name"));
        new WebDriverWait(driver, Duration.ofMillis(900)).until(
                ExpectedConditions.visibilityOf(items.get(items.size()-1))
        );
        System.out.println("No prduct: "+(items.size()-1));
        assertEquals("The products in cart are less or more to the actual ones",items.size()-1,4);

    }

    private void openAccountPageToLogin(){
        driver.get("https://askomdch.com/account/");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(
                By.cssSelector(".woocommerce-button.button.woocommerce-form-login__submit")
        ).click();

    }

    private void openCartPage(){
        driver.get("https://askomdch.com/cart/");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I click checkout button")
    public void i_click_checkout_button() {
        List<WebElement> bottomTitles = driver.findElements(By.className("widget-title"));
        new Actions(driver).moveToElement(bottomTitles.get(2));
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();
    }

    @And("I fill in the shipping information to place an order")
    public void i_fill_in_the_shipping_information(DataTable dataTable) {

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);

        driver.findElement(By.id("billing_first_name")).clear();
        driver.findElement(By.id("billing_first_name")).sendKeys(data.get("firstName"));
        driver.findElement(By.id("billing_last_name")).clear();
        driver.findElement(By.id("billing_last_name")).sendKeys(data.get("lastName"));
        driver.findElement(By.id("billing_address_1")).clear();
        driver.findElement(By.id("billing_address_1")).sendKeys(data.get("streetAddress"));
        driver.findElement(By.id("billing_city")).clear();
        driver.findElement(By.id("billing_city")).sendKeys(data.get("city"));
        driver.findElement(By.id("billing_postcode")).clear();
        driver.findElement(By.id("billing_postcode")).sendKeys(data.get("zip"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        placeOrder();


    }

    private void placeOrder() {
        new WebDriverWait(driver,Duration.ofMillis(500)).until(
                ExpectedConditions.elementToBeClickable(driver.findElement(By.id("place_order")))
        );
        System.out.println("this button :"+driver.findElement(By.id("place_order")).getText());
        driver.findElement(By.id("place_order")).click();
    }

    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(500));
        String orderSuccess1 = wait.until(
                ExpectedConditions.visibilityOf(
                        driver.findElement(By.cssSelector(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received")))
        ).getText();

        System.out.println("Order message: "+orderSuccess1);

        assertEquals("Order is not placed",
                orderSuccess1,
                "Thank you. Your order has been received.");
    }

}
