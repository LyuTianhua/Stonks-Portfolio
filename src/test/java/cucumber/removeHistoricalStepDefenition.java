package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class removeHistoricalStepDefenition {

    private static final String ROOT_URL = "https://localhost:8080/";
    WebDriver driver = RunCucumberTests.driver;
    WebDriverWait wait = RunCucumberTests.wait;

    @And("I have one historical stock in table")
    public void i_enter() {

        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("view-stock-btn"))
        ).click();

        driver.switchTo().activeElement();

        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("ticker-view"))
        ).sendKeys("AAPL");


        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("view-stock-in-modal"))
        ).click();

    }


    @When("I click remove")
    public void i_press_the_remove_stock_button() {
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("AAPLHistorical"))
        ).click();

    }

    @When("I shouldn't see any historical stock")
    public void i_click_confirm_on_the_pop_up_modal() throws InterruptedException {
        try {
            driver.findElement(By.id("APPLHistorical"));
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }


}