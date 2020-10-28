package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class LoadGraphStepDefenitions {

    private static final String ROOT_URL = "http://localhost:8081/";
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
}
