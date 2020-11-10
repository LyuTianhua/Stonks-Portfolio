package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class inactiveStepDefinition {

	private static final String ROOT_URL = "https://localhost:8080/";
	WebDriver driver = RunCucumberTests.driver;
	WebDriverWait wait = RunCucumberTests.wait;

	@Given("I am on the home page")
	public void i_am_on_the_home_page() {

		driver.get(ROOT_URL + "index.jsp");
		WebElement queryBox = driver.findElement(By.id("iEmail"));
		queryBox.sendKeys("tu1@email.com");

		queryBox = driver.findElement(By.id("iPassword"));
		queryBox.sendKeys("tu1pass");

		driver.findElement(By.id("signin")).click();

	}

	@When("I do nothing for 120 seconds")
	public void iDoNothingForSeconds() {
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Then("I should be at page {string}")
	public void iShouldBeAtPage(String url) {
		wait.until(ExpectedConditions.urlContains(url));
		assertTrue(driver.getCurrentUrl().contains(url));
	}

}