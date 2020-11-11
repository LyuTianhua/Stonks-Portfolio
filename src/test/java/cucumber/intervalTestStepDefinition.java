package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class intervalTestStepDefinition {

	private static final String ROOT_URL = "https://localhost:8080/";
	WebDriver driver = RunCucumberTests.driver;
	WebDriverWait wait = RunCucumberTests.wait;

	@And("I choose the day select under the graph")
    public void i_choose_day_select() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        Select select = new Select(driver.findElement(By.id("interval")));
        select.selectByValue("day");
    }
	
	@And("I choose the week select under the graph")
    public void i_choose_week_select() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        Select select = new Select(driver.findElement(By.id("interval")));
        select.selectByValue("week");
    }
	
	@And("I choose the month select under the graph")
    public void i_choose_month_select() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        Select select = new Select(driver.findElement(By.id("interval")));
        select.selectByValue("month");
    }
	
	@Then("I should see the graph with day interval")
    public void i_see_the_graph_day() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        
        assertTrue(driver.findElements(By.id("myChart")).size() != 0);
    }
	
	@Then("I should see the graph with week interval")
    public void i_see_the_graph_week() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }

        assertTrue(driver.findElements(By.id("myChart")).size() != 0);
    }
	
	@Then("I should see the graph with month interval")
    public void i_see_the_graph_month() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }        

        assertTrue(driver.findElements(By.id("myChart")).size() != 0);
    }
    
}
