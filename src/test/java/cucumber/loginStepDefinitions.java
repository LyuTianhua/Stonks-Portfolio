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

/**
 * Step definitions for Cucumber tests.
 */
public class loginStepDefinitions {

	private static final String ROOT_URL = "http://localhost:8080/";
	private final WebDriver driver = new ChromeDriver(RunCucumberTests.options);
	private final WebDriverWait wait = new WebDriverWait(driver, 3);

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		driver.get(ROOT_URL + "index.jsp");
	}

	@When("I click the Register tab")
	public void iClickTheRegisterTab() {
		driver.findElement(By.linkText("Register")).click();
	}

	@Then("I should be on page {string}")
	public void iShouldBeOnPageRegisterJsp(String url) {
		wait.until(ExpectedConditions.urlContains(url));
		assertTrue(driver.getCurrentUrl().contains(url));
	}

	@When("I enter {string} in Email Address input field")
	public void iEnterTuEmailComInEmailAddressInputField(String email) {
		wait.until(
				ExpectedConditions.elementToBeClickable(By.id("iEmail"))
		).sendKeys(email);

	}

	@And("I enter {string} in Password input field")
	public void iEnterTuPassInPasswordInputField(String password) {
		driver.findElement(By.id("iPassword")).sendKeys(password);
	}

	@And("I click the Sign In button")
	public void iClickTheSignInButton() {
		driver.findElement(By.id("signin")).click();
	}

	@Then("an error message {string} should show up")
	public void anErrorMessageShouldShowUp(String errMsg) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Error-Message")));
		assertTrue(driver.findElement(By.id("Error-Message")).getAttribute("innerHTML").contains(errMsg));
	}

	@After
	public void tearDown() { driver.quit(); }
}