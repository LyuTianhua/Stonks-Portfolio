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
public class loginStepDefinitions {

	private static final String ROOT_URL = "https://localhost:8080/";
	WebDriver driver = RunCucumberTests.driver;
	WebDriverWait wait = RunCucumberTests.wait;

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		driver.get(ROOT_URL + "index.jsp");
	}
	
	@Given("I am on the home page without login")
	public void i_am_on_the_home_page_without_login() {
		driver.get(ROOT_URL + "home.jsp");
	}

	@When("I do nothing")
	public void I_do_noting() {
		driver.navigate().to(ROOT_URL + "index.jsp");
	}
  
	@When("I click the Register tab")
	public void iClickTheRegisterTab() {
		driver.findElement(By.linkText("Register")).click();
	}

	@When("I enter {string} in Email Address input field")
	public void iEnterTuEmailComInEmailAddressInputField(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("iEmail"))).sendKeys(email);
	}
	
	@And("I enter {string} in Password input field")
	public void iEnterTuPassInPasswordInputField(String password) {
		driver.findElement(By.id("iPassword")).sendKeys(password);
	}

	@And("I click the Sign In button")
	public void iClickTheSignInButton() {
		driver.findElement(By.id("signin")).click();
	}
	
	@Then("I should be on page {string}")
	public void iShouldBeOnPageRegisterJsp(String url) {
		wait.until(ExpectedConditions.urlContains(url));
		assertTrue(driver.getCurrentUrl().contains(url));
	}

	@Then("an error message {string} should show up")
	public void anErrorMessageShouldShowUp(String errMsg) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("Error-Message")));
		assertTrue(driver.findElement(By.id("Error-Message")).getAttribute("innerHTML").contains(errMsg));
	}

//	@After
//	public void tearDown() { driver.quit(); }
}