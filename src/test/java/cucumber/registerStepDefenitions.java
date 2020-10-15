package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @When("I enter {string} in the Email Address field")
    public void iEnterTuEmailComInTheEmailAddressField(String email) {
        driver.findElement(By.id("exampleInputEmail1")).sendKeys(email);
    }

    @When("I press the cancel button")
    public void i_press_the_cancel_button() {
    	driver.findElement(By.id("cancel")).click();
    }

    @And("I enter {string} in the Password field")
    public void iEnterTuPassInThePasswordField(String password) {
        driver.findElement(By.id("exampleInputPassword1")).sendKeys(password);
    }

    @And("I enter {string} in the Verify Password field")
    public void iEnterTuPassInTheVerifyPasswordField(String verify) {
        driver.findElement(By.id("exampleInputPassword2")).sendKeys(verify);
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

}
