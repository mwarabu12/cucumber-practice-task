package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class ProductsListingSteps {
    private final WebDriver driver = Hooks.driver;
    @Given("I am on the landing page of AskOmDch ecommerce")
    public void i_am_on_the_landing_page_of_ask_om_dch_ecommerce() {
        driver.get("https://askomdch.com/");
    }

    @When("I click the Store button")
    public void i_click_the_store_button() {
        driver.findElement(By.xpath("//a[normalize-space(text())='Store']")).click();
    }

    @Then("I should be taken to the Store product listing page")
    public void i_should_be_taken_to_the_store_product_listing_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[normalize-space(text())='Store']"))));
    }

    @Then("I should see a list of available products")
    public void i_should_see_a_list_of_available_products() {
        WebElement productContainer = driver.findElement(By.cssSelector(".products.columns-4"));
        assertTrue(productContainer.findElements(By.tagName("li")).size()>1);
    }
}
