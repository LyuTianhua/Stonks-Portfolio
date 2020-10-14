package cucumber;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Cucumber tests.
 */
public class logoutStepDefinitions {

	private static final String ROOT_URL = "http://localhost:8080/";

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on {string}")
	public void i_am_on(String string) {
		driver.get(ROOT_URL + "home.jsp");
	}
	
	@When("I press the log out button")
	public void i_press_the_log_out_button() {
		driver.findElement(By.id("logout")).click();
	}
	
	@Then("I should be on the {string} page")
	public void i_should_be_on_the_page(String url) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(driver.getCurrentUrl().trim());
		System.out.println(driver.getCurrentUrl());
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
