package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class loginStepDefinitions {

	private static final String ROOT_URL = new Configurations().url;

	private final WebDriver driver = new ChromeDriver();

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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(driver.getCurrentUrl().trim());
		System.out.println(driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().contains(url));
	}

	@When("I enter {string} in Email Address input field")
	public void iEnterTuEmailComInEmailAddressInputField(String email) {
		WebElement queryBox = driver.findElement(By.name("email"));
		queryBox.sendKeys(email);
	}

	@And("I enter {string} in Password input field")
	public void iEnterTuPassInPasswordInputField(String password) {
		WebElement queryBox = driver.findElement(By.name("password"));
		queryBox.sendKeys(password);
	}

	@And("I click the Sign In button")
	public void iClickTheSignInButton() {
		WebElement result = driver.findElement(By.id("signin"));
		result.click();
	}

	@Then("an error message {string} should show up")
	public void anErrorMessageShouldShowUp(String errMsg) {
		// Fixed not recognizing error message (Assertion error)
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(driver.findElement(By.id("Error-Message")).getAttribute("innerHTML").contains(errMsg));
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