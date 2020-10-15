package cucumber;

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
public class logoutStepDefinitions {

	private static final String ROOT_URL = "http://localhost:8080/";
	private final WebDriver driver = new ChromeDriver(RunCucumberTests.options);
	private final WebDriverWait wait = new WebDriverWait(driver, 3);

	@Given("I am on signed in")
	public void i_am_on_signed_in() {
		driver.get(ROOT_URL + "index.jsp");
		driver.findElement(By.id("iEmail")).sendKeys("tu1@email.com");

		driver.findElement(By.id("iPassword")).sendKeys("tu1pass");

		driver.findElement(By.id("signin")).click();
	}

	@When("I press the log out button")
	public void i_press_the_log_out_button()  {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("logout"))).click();
	}

	@Then("I should be on the {string} page")
	public void i_should_be_on_the_page(String url) {
		wait.until(ExpectedConditions.urlContains(url));
		assertTrue(driver.getCurrentUrl().contains(url));
	}

	@After
	public void tearDown() { driver.quit(); }
}