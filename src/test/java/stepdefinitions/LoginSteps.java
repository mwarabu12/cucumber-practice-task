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

public class LoginSteps {

    private final WebDriver driver = Hooks.driver;
    private final WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://askomdch.com/account/");
    }

    @When("I log in with:")
    public void i_log_in_with(DataTable dataTable) {

        Map<String, String> data = dataTable.asMap(String.class, String.class);

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys(data.get("username"));

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(data.get("password"));

        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {

        WebElement logoutLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[normalize-space()='Logout']")
                )
        );

        Assert.assertTrue("Login was not successful",
                logoutLink.isDisplayed());
    }

    @Then("I should see the login error message {string}")
    public void i_should_see_the_login_error_message(String expectedMessage) {

        WebElement errorNotice = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".woocommerce-error")
                )
        );

        String actualMessage = errorNotice.getText();

        Assert.assertTrue(
                "Expected login error message not shown",
                actualMessage.contains(expectedMessage)
        );
    }
}
