package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddProductToCartSteps {
    private final WebDriver driver =Hooks.driver;


    @Given("I am on the AskOmDch homepage")
    public void i_am_on_the_ask_om_dch_homepage() {
        driver.get("https://askomdch.com/");
    }

    @When("I click the checkout button")
    public void i_click_the_checkout_button() {
        new Actions(driver).moveToElement(
                driver.findElement(By.xpath("//h2[normalize-space(text())='Featured Products']"))
        ).perform();
        List<WebElement> buttons = driver.findElements(By.cssSelector(".wp-block-button__link.has-white-color.has-text-color"));
        buttons.get(buttons.size()-1).click();

    }

    @Then("I should be taken to the accessory product page")
    public void i_should_be_taken_to_the_accessory_product_page() {
        new WebDriverWait(driver, Duration.ofMillis(500)).until(
                ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")))
        );
    }

    @When("I select  products")
    public void i_select_product() {
        List<WebElement> add_to_cart_buttons  = driver.findElements(
                By.cssSelector(".button.product_type_simple.add_to_cart_button.ajax_add_to_cart"));
        for(WebElement button: add_to_cart_buttons){
            button.click();
        }
    }

    @Then("the product should be added to the shopping cart")
    public void the_product_should_be_added_to_the_shopping_cart() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector(".ast-cart-menu-wrap"))).perform();
        String products_number = driver.findElement(By.cssSelector(".ast-cart-menu-wrap")).findElement(By.tagName("span")).getText();
        assertEquals("The added products are less or more","3",products_number);
    }
}
