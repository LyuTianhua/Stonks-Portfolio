package cucumber;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Cucumber tests.
 */
public class addRemoveStockStepDefenitions {

	private static final String ROOT_URL = new Configurations().url;

	private final WebDriver driver = new ChromeDriver();

	@Given("I am signed in")
	public void i_am_signed_in() {
		driver.get(ROOT_URL + "index.jsp");
		WebElement queryBox = driver.findElement(By.name("email"));
		queryBox.sendKeys("tu1@email.com");
		queryBox = driver.findElement(By.name("password"));
		queryBox.sendKeys("tu1pass");
		driver.findElement(By.name("signin")).click();
	}

	
	@Given("I enter {string} into ticker")
	public void i_enter_into_ticker(String string) {
		WebElement queryBox = driver.findElement(By.id("ticker"));
		queryBox.sendKeys(string);
	}
	
	
	@Given("I enter {string} into quantity")
	public void i_enter_into_quantity(String string) {
		WebElement queryBox = driver.findElement(By.id("quantity"));
		queryBox.sendKeys(string);
	}
	
	@Given("I enter {string} into company")
	public void i_enter_into_company(String string) {
		WebElement queryBox = driver.findElement(By.id("company-name"));
		queryBox.sendKeys(string);
	}
	
	@Given("I click the add stock button")
	public void i_click_the_add_stock_button() {
		driver.findElement(By.id("add-btn")).click();;
	}
	
	@Then("I should see 10 TSLA stock on the portfolio")
	public void i_should_see_TSLA_stock_on_the_portfolio() {
	   
		List<WebElement> elements = driver.findElements(By.cssSelector("#portfolio-stocks td:nth-child(3)"));
	      assert(elements.size() > 0);
	}


	
	
	
	

}

	


