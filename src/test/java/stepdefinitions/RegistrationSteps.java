package stepdefinitions;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class RegistrationSteps {

    private final WebDriver driver = Hooks.driver;
    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the user registration page")
    public void i_am_on_the_user_registration_page() {
        driver.get("https://askomdch.com/account/");
    }

    @When("I submit the registration form with:")
    public void i_submit_the_registration_form_with(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);

        driver.findElement(By.id("reg_username"))
                .sendKeys(data.get("username"));

        driver.findElement(By.id("reg_email"))
                .sendKeys(data.get("email"));

        driver.findElement(By.id("reg_password"))
                .sendKeys(data.get("password"));

        driver.findElement(By.cssSelector("button[name='register']")).click();
    }

    @Then("I should be redirected to my account page")
    public void i_should_be_redirected_to_my_account_page() {

        WebElement logoutLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space()='Logout']")
                )
        );

        Assert.assertTrue("Account page was not displayed",
                logoutLink.isDisplayed());
    }

    @Then("I should see the registration error message {string}")
    public void i_should_see_the_registration_error_message(String expectedMessage) {

        WebElement errorNotice = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".woocommerce-error")
                )
        );

        String actualMessage = errorNotice.getText();

        Assert.assertTrue(
                "Expected error message was not shown",
                actualMessage.contains(expectedMessage)
        );
    }
}
