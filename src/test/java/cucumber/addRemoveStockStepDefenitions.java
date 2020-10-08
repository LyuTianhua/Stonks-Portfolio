//package cucumber;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import static org.junit.Assert.assertEquals;
//
//public class addRemoveStockStepDefenitions {
//
//    private static final String ROOT_URL = new Configurations().url;
//    private final WebDriver driver = new ChromeDriver();
//
//    @Given("I am signed in")
//    public void i_am_signed_in() {
//
//        driver.get(ROOT_URL + "index.jsp");
//
//    }
//
//    @Given("I click on {string}")
//    public void i_click_on(String addStock) {
//
//        driver.findElement(By.name(addStock)).click();
//
//    }
//
//    @Given("I enter {string} into {string}")
//    public void i_enter(String key, String value) {
//
//        driver.findElement(By.name(value)).sendKeys(key);
//
//    }
//
//    @Then("I should see {double} TSLA stock on the portfolio")
//    public void i_should_see(double quantity) {
//        WebElement we = driver.findElement(By.name("quantity"));
//        assertEquals(quantity, Double.parseDouble(we.getText()), 0.0);
//    }
//
//    @When("I remove {int} stock from {string}")
//    public void i_remove_stock_from(int quantity, String company) {
//
//
//    }
//}


//#Feature: add stock to portfolio
//        #
//        #  Scenario: Add stock from home page
//        #    Given I am signed in
//        #    When I click on the add stock button
//        #    And I enter 'Tesla' into company
//        #    And I enter 'TSLA' into ticker
//        #    And I enter 10 into quantity
//        #    Then I should see 10 TSLA stock on the portfolio
//        #
//        #  Scenario:  Add more stock to existing stock
//        #    Given I am signed in
//        #    When I click on the add stock button
//        #    And I enter 'Tesla' into company
//        #    And I enter 'TSLA' into ticker
//        #    And I enter 10 into quantity
//        #    Then I should see 20 TSLA stock on the portfolio
//        #
//        #  Scenario: Remove stock from home page
//        #    Given I am signed in
//        #    When I remove 10 stock from 'TSLA'
//        #    Then I should see 10 stock left for 'TSLA'