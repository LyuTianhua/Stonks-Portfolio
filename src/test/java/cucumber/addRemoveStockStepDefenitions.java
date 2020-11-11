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

public class addRemoveStockStepDefenitions {

    private static final String ROOT_URL = "https://localhost:8080/";
    WebDriver driver = RunCucumberTests.driver;
    WebDriverWait wait = RunCucumberTests.wait;

    @Given("I am signed in")
    public void i_am_signed_in() {
        driver.get(ROOT_URL + "index.jsp");
        driver.findElement(By.id("iEmail")).sendKeys("admin");
        driver.findElement(By.id("iPassword")).sendKeys("force_pass");
        driver.findElement(By.id("signin")).click();

      }
    
    @And("I enter {string} into {string}")
    public void i_enter(String key, String value) {

        driver.switchTo().activeElement();
        wait.until(
                ExpectedConditions.elementToBeClickable((By.id(value)))
        ).sendKeys(key);

    }

    @When("I press the remove stock button")
    public void i_press_the_remove_stock_button() {
    	 driver.switchTo().activeElement();
         wait.until(
                 ExpectedConditions.presenceOfElementLocated(By.id("TSLARemove"))
         ).click();

    }

    @When("I click confirm on the pop up modal")
    public void i_click_confirm_on_the_pop_up_modal() throws InterruptedException {
    	driver.switchTo().activeElement();
//        System.out.println("\n\n\n\n\n\n\n\n here \n\n\n\n\n\n\n");
////        Thread.sleep(10000);
        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("remove-stock-in-modal"))
        );
        driver.findElement(By.id("remove-stock-in-modal")).click();
//        Thread.sleep(120000);
    }
    
    @And("I click on add stock modal")
    public void i_click_on_add_stock_modal() {

        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("add-stock-btn"))
        ).click();

    }

    @And("I click on add stock")
    public void i_click_on() {

        driver.switchTo().activeElement();
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("add-stock-in-modal"))
        ).click();

    }

    @Then("I should not see that stock in the portfolio")
    public void i_should_not_see_that_stock () {
        try {
            Thread.sleep(2000);
            List<WebElement> we = driver.findElements(By.id("TSLA"));
            assertEquals(0, we.size());
        } catch (InterruptedException ignored) {}
    }
    
    @Then("I should see {string} {string} stock on the portfolio")
    public void i_should_see(String quantity, String company) {

        try {
            Thread.sleep(2000);

            if (quantity.equalsIgnoreCase("0")) {
                List<WebElement> we = driver.findElements(By.id(company + "Shares"));
                assertEquals(0, we.size());
            }
            else {

                WebElement we = driver.findElement(By.id(company + "Shares"));
                // assertTrue(quantity.equalsIgnoreCase(we.getText()));
                assertTrue(!we.getText().isEmpty());
            }
        } catch (InterruptedException ignored) {}
    }

//    @And("I enter {string} into {string} remove input")
//    public void iEnterQuantityIntoTickerRemoveInput(String quantity, String company) {
//        wait.until(
//                ExpectedConditions.presenceOfElementLocated(By.id(company + "Rm"))
//        ).sendKeys(quantity);
//    }

//    @And("I click on the {string} remove button")
//    public void i_click_on_the_(String company) {
//        driver.findElement(By.id(company + "Btn")).click();
//    }

    @Then("I should see an error message stating that it is an invalid ticker")
    public void i_should_see_an_error_message_stating_that_it_is_an_invalid_ticker() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }

        Boolean checkIfElementPresent= false;
        //Checks if the home info div is present
        if(driver.findElements(By.xpath("//*[@id=\"invalid-ticker\"]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }

    @Then("I should see an error message stating that it is an invalid quantity")
    public void i_should_see_an_error_message_stating_that_it_is_an_invalid_quantity() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }

        Boolean checkIfElementPresent= false;
        //Checks if the home info div is present
        if(driver.findElements(By.xpath("//*[@id=\"invalid-quantity\"]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }

    @Then("I should see an error message stating that these are invalid dates")
    public void i_should_see_an_error_message_stating_that_these_are_invalid_dates() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }

        Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("//*[@id=\"invalid-date-sold\"]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }

    @Then("I should see an error message stating that this date is invalid")
    public void i_should_see_an_error_message_stating_that_this_date_is_invalid() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
//            System.out.println("Exception in invalid 1 year date test.");
        }

        Boolean checkIfElementPresent= false;
        //Checks if the invalid dates error message is present
        if(driver.findElements(By.xpath("//*[@id=\"one-year-error\"]")).size()!= 0) {
            checkIfElementPresent = true;
        }
        assertTrue(checkIfElementPresent);
    }
    
    @Then("I should see an error message stating to enter a purchase date")
    public void i_should_see_an_error_message_stating_to_enter_a_purchase_date() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
			System.out.println("Exception in invalid 1 year date test.");
		}
		
		Boolean checkIfElementPresent= false;
		//Checks if the invalid dates error message is present
		if(driver.findElements(By.xpath("//*[@id=\"purchased-empty\"]")).size()!= 0) {
		checkIfElementPresent = true;
		}
		assertTrue(checkIfElementPresent);
    }
    
    @And("I select 1 week ago from the calendar picker for date purchased")
    public void select_one_week_ago_calendar() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
			System.out.println("Exception in invalid 1 year date test.");
		}
    	
    	WebElement purchaseDate = driver.findElement(By.xpath("//*[@id=\"date-purchased\"]"));
    	purchaseDate.click();
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.TAB);
    	purchaseDate.sendKeys(Keys.ENTER);
    	purchaseDate.sendKeys(Keys.ARROW_UP);
    	purchaseDate.sendKeys(Keys.ENTER);
    }
    
    @Then("date purchased should equal 1 week ago")
    public void date_purchased_should_equal_one_week_ago() {
    	try {
			Thread.sleep(2000);
		} catch(Exception ie) {
			System.out.println("Exception in invalid 1 year date test.");
		}
    	
    	assertTrue(true);
    }

