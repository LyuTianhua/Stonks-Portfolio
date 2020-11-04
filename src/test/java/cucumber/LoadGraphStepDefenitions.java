package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class LoadGraphStepDefenitions {

    private static final String ROOT_URL = "https://localhost:8080/";
    WebDriver driver = RunCucumberTests.driver;
    WebDriverWait wait = RunCucumberTests.wait;

//    @Given("I am signed in")
//    public void i_am_signed_in() {
//        driver.get(ROOT_URL + "index.jsp");
//        driver.findElement(By.id("iEmail")).sendKeys("admin");
//        driver.findElement(By.id("iPassword")).sendKeys("force_pass");
//        driver.findElement(By.id("signin")).click();
//    }

    @Then("I should see a graph")
    public void i_should_see_a_graph() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myChart")));
        assertTrue(driver.findElement(By.id("myChart")).isDisplayed());
    }

    @And("I have added stock")
    public void i_have_added_stock() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-stock-btn"))).click();

        driver.switchTo().activeElement();
        wait.until(ExpectedConditions.elementToBeClickable((By.id("ticker")))).sendKeys("TSLA");
        wait.until(ExpectedConditions.elementToBeClickable((By.id("quantity")))).sendKeys("10");
        wait.until(ExpectedConditions.elementToBeClickable((By.id("date-purchased")))).sendKeys("10/11/2020");
    }

    @Then("I should see graphed stock data")
    public void i_should_see_graphed_stock_data() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myChart")));
        assertTrue(driver.findElement(By.id("myChart")).isDisplayed());
    }
   
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
    	
    	WebElement portfolioValue = driver.findElement(By.xpath("//*[@id=\"portfolio-value-number\"]"));
    	assertTrue(!portfolioValue.getAttribute("value").isEmpty());
    }
    
    @Then("the portfolio value should be green or red with an up or down arrow by it")
    public void the_portfolio_value_should_be() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
		}
    	
    	WebElement portfolioValue = driver.findElement(By.xpath("//*[@id=\"portfolio-value-number\"]"));
    	assertTrue(!portfolioValue.getCssValue("color").equals("black"));
    }

}
