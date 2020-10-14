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

public class inactiveStepDefinition {
	private static final String ROOT_URL = "http://localhost:8080/";

	private final WebDriver driver = new ChromeDriver();

	@Given("I am on the home page")
	public void i_am_on_the_home_page() {
		driver.get(ROOT_URL + "home.jsp");
	}
	
	@When("I do nothing for 5 seconds")
	public void iDoNothingForSeconds() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@When("I click title")
	public void iClickTitle() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.linkText("USC CS 310 Stock Portfolio Management")).click();
	}
	
	@Then("I should be at page {string}")
	public void iShouldBeAtPage(String url) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(driver.getCurrentUrl().contains(url));
	}
	
	@After()
	public void cleanup() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
