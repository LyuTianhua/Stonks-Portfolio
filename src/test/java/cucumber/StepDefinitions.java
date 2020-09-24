package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	private static final String ROOT_URL = "http://localhost:8080/";

	private final WebDriver driver = new ChromeDriver();	
	// Login page 
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		driver.get(ROOT_URL + "index.jsp");
	}
	
	@When("I click the Register link")
	public void i_click_the_Register_link() {
		driver.findElement(By.linkText("Register")).click();
	}
	
	@When("I enter {string} into Email Address input field")
	public void i_enter_into_Email_Address_input_field(String string) {
		WebElement queryBox = driver.findElement(By.name("exampleInputEmail1"));
		queryBox.sendKeys(string);
	}
	
	@When("I enter {string} into Password input field")
	public void i_enter_into_Password_input_field(String string) {
		WebElement queryBox = driver.findElement(By.name("exampleInputPassword1"));
		queryBox.sendKeys(string);
	}
	
	@When("I click the Sign In button")
	public void i_click_the_Sign_in_button() {
		WebElement result = driver.findElement(By.name("signin"));
		result.click();
	}
	
	@Then("I should be on page {string}")
	public void i_should_be_on_page(String url) {
		assertTrue(driver.getCurrentUrl().contains(url));
	}
	
	@Then("an error message {string} should show up")
	public void an_error_message_should_show_up(String string) {
		assertTrue(driver.findElement(By.name("Error-Message")).getAttribute("innerHTML").contains(string));
	}
	
	@After()
	public void after() {
		driver.quit();
	}
}
