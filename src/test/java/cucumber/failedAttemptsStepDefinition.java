package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class failedAttemptsStepDefinition {

    private static final String ROOT_URL = "https://localhost:8080/";
    WebDriver driver = RunCucumberTests.driver;
    WebDriverWait wait = RunCucumberTests.wait;

    @Given("I am on the login page to test failed attempts")
    public void iAmOnTheRegisterPageToTestFailedAttempts() {
        driver.get(ROOT_URL + "index.jsp");
    }

    @When("I enter {string} in Email Address input field to fail")
    public void iEnterTuEmailComInEmailAddressInputFieldToFail(String email) {
        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("iEmail"))
        ).sendKeys(email);

    }

    @And("I enter {string} in Password input field to fail")
    public void iEnterTuPassInPasswordInputFieldToFail(String password) {
        driver.findElement(By.id("iPassword")).sendKeys(password);
    }

    @And("I click the Sign In button to fail")
    public void iClickTheSignInButtonToFail() {
        driver.findElement(By.id("signin")).click();
    }

    @When("I clear the password field to fail")
    public void iClearThePasswordFieldToFail() {
        driver.findElement(By.id("iPassword")).clear();
    }

    @Then("I see the error message {string}")
    public void i_see_the_error_message(String message) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Error-Message")));
        assertTrue(driver.findElement(By.id("Error-Message")).getAttribute("innerHTML").contains(message));
    }

//    @After
//    public void tearDown() { driver.quit(); }
}