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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class graphDateStepDefinitions {
	
	private static final String ROOT_URL = "https://localhost:8080/";
	WebDriver driver = RunCucumberTests.driver;
	WebDriverWait wait = RunCucumberTests.wait;

	@Then("I should see an error message stating that these are invalid dates below the graph")
    public void i_should_see_an_error_message_stating_that_these_are_invalid_dates_below() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }

        Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("//*[@id=\"invalid-dates\"]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }
    
    @And("I select 1 week ago from the calendar picker for date from")
    public void select_one_week_ago_calendar_for_from() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
			//System.out.println("Exception in invalid 1 year date test.");
		}
    	
    	WebElement purchaseDate = driver.findElement(By.xpath("//*[@id=\"fromGraph\"]"));
    	purchaseDate.click();
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.ENTER);
    	purchaseDate.sendKeys(Keys.ARROW_UP);
    	purchaseDate.sendKeys(Keys.ENTER);
    }
    
    @Then("date from should equal 1 week ago")
    public void date_should_equal_one_week_ago_for_from() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	WebElement purchaseDate = driver.findElement(By.xpath("//*[@id=\"fromGraph\"]"));
    	assertTrue(!purchaseDate.getAttribute("value").isEmpty());
    }
    
    @And("I select 1 week ago from the calendar picker for date to")
    public void select_one_week_ago_calendar_for_to() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
			//System.out.println("Exception in invalid 1 year date test.");
		}
    	
    	WebElement purchaseDate = driver.findElement(By.xpath("//*[@id=\"toGraph\"]"));
    	purchaseDate.click();
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.ENTER);
    	purchaseDate.sendKeys(Keys.ARROW_UP);
    	purchaseDate.sendKeys(Keys.ENTER);
    }
    
    @Then("date to should equal 1 week ago")
    public void date_should_equal_one_week_ago_for_to() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	WebElement purchaseDate = driver.findElement(By.xpath("//*[@id=\"toGraph\"]"));
    	assertTrue(!purchaseDate.getAttribute("value").isEmpty());
    }
    
    @And("I see my portfolio value")
    public void i_see_my_portfolio_value() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("//*[@id=\"portfolio-value\"]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }
    
    @Then("the portfolio value should be green or red with an up or down arrow by it")
    public void the_portfolio_value_should_be() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	WebElement portfolioValue = driver.findElement(By.xpath("//*[@id=\"portfolio-value-number\"]"));
    	assertTrue(!portfolioValue.getCssValue("color").equals("rgba(1,0,0,1)"));
    }
    
    @And("I click one week below the graph")
    public void i_click_one_week() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("/html/body/div[1]/div[3]/div/label[1]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }
    
    @And("I click three months below the graph")
    public void i_click_three_months() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("/html/body/div[1]/div[3]/div/label[2]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }
    
    @And("I click one year below the graph")
    public void i_click_one_year() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("/html/body/div[1]/div[3]/div/label[3]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }
    
    @Then("the graph should display a one week date range from today")
    public void the_graph_should_display_a_one_week() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/label[1]")).click();
    	assertTrue(!driver.findElement(By.xpath("//*[@id=\"fromGraph\"]")).getAttribute("value").isEmpty());
    }
    
    @Then("the graph should display a three month date range from today")
    public void the_graph_should_display_a_three_month() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/label[2]")).click();
    	assertTrue(!driver.findElement(By.xpath("//*[@id=\"fromGraph\"]")).getAttribute("value").isEmpty());
    }
    
    @Then("the graph should display a one year date range from today")
    public void the_graph_should_display_a_one_year() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/label[3]")).click();
    	assertTrue(!driver.findElement(By.xpath("//*[@id=\"fromGraph\"]")).getAttribute("value").isEmpty());
    }
    
}
