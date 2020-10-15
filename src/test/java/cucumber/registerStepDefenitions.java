package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class registerStepDefenitions {

    private static final String ROOT_URL = "http://localhost:8080/";
    private final WebDriver driver = new ChromeDriver(RunCucumberTests.options);
    private final WebDriverWait wait = new WebDriverWait(driver, 3);

    @Given("I am on the register page")
    public void iAmOnTheRegisterPage() {
        driver.get(ROOT_URL + "register.jsp");
    }


    @And("I enter {string} in the {string} field")
    public void iEnterStringInField(String value, String key) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("r" + key))).sendKeys(value);
    }

    @When("I press the cancel button")
    public void i_press_the_cancel_button() {
        driver.findElement(By.id("cancel")).click();
    }

    @And("I click the Register link")
    public void iClickTheRegisterLink() {
        driver.findElement(By.id("register")).click();
    }

    @Then("I should be on {string}")
    public void iShouldBeOnIndexJsp(String url) {
        wait.until(ExpectedConditions.urlContains(url));
        assertTrue(driver.getCurrentUrl().contains(url));
    }

    @After
    public void tearDown() { driver.quit(); }
}