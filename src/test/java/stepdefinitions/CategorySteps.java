package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CategorySteps {

    private final WebDriver driver = Hooks.driver;
    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver.get("https://askomdch.com/");
    }

    @When("I select the {string} category from the product dropdown")
    public void i_select_the_category_from_the_product_dropdown(String category) {

        WebElement categoryDropdown =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".dropdown_product_cat")
                ));

        Select select = new Select(categoryDropdown);
        select.selectByValue(category);
    }

    @Then("I should see only products related to the {string} category")
    public void i_should_see_only_products_related_to_the_category(String category) {

        WebElement pageTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1.woocommerce-products-header__title")
                )
        );

        String titleText = pageTitle.getText().toLowerCase();

        Assert.assertTrue(
                "Expected category page for: " + category + " but got: " + titleText,
                titleText.contains(category.replace("-", " "))
                        || titleText.contains(category.split("-")[0])
        );
    }
}
