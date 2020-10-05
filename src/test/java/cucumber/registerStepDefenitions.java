package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class registerStepDefenitions {

    private static final String ROOT_URL = "http://localhost:8080/";

    private final WebDriver driver = new ChromeDriver();

    @Given("I am on the register page")
    public void iAmOnTheRegisterPage() {
        driver.get(ROOT_URL + "register.jsp");
    }

    @When("I enter {string} in the Email Address field")
    public void iEnterTuEmailComInTheEmailAddressField(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
    }

    @And("I enter {string} in the Password field")
    public void iEnterTuPassInThePasswordField(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @And("I enter {string} in the Verify Password field")
    public void iEnterTuPassInTheVerifyPasswordField(String verify) {
        driver.findElement(By.name("verifyPassword")).sendKeys(verify);
    }

    @And("I click the Register link")
    public void iClickTheRegisterLink() {
        driver.findElement(By.name("registerButton")).click();
    }

    @Then("I should be on {string}")
    public void iShouldBeOnIndexJsp(String url) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(driver.getCurrentUrl().contains(url));
    }

    @After()
    public void cleanup() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
