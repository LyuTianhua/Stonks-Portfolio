package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class addRemoveStockStepDefenitions {

    private static final String ROOT_URL = "http://localhost:8080/";
    private final WebDriver driver = new ChromeDriver(RunCucumberTests.options);
    private final WebDriverWait wait = new WebDriverWait(driver, 3);

    @Given("I am signed in")
    public void i_am_signed_in() {

        driver.get(ROOT_URL + "index.jsp");

        driver.findElement(By.id("iEmail")).sendKeys("tu1@email.com");

        driver.findElement(By.id("iPassword")).sendKeys("tu1pass");

        driver.findElement(By.id("signin")).click();

    }

    @And("I enter {string} into {string}")
    public void i_enter(String key, String value) {

        driver.switchTo().activeElement();
        wait.until(
                ExpectedConditions.elementToBeClickable((By.id(value)))
        ).sendKeys(key);

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


    @Then("I should see {string} {string} stock on the portfolio")
    public void i_should_see(String quantity, String company) {

        try {
            Thread.sleep(500);

            if (quantity.equalsIgnoreCase("0")) {
                List<WebElement> we = driver.findElements(By.id(company + "Shares"));
                assertEquals(0, we.size());
            }
            else {

                WebElement we = driver.findElement(By.id(company + "Shares"));
                assertTrue(quantity.equalsIgnoreCase(we.getText()));
            }
        } catch (InterruptedException ignored) {}
    }

    @And("I enter {string} into {string} remove input")
    public void iEnterQuantityIntoTickerRemoveInput(String quantity, String company) {
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(company + "Rm"))
        ).sendKeys(quantity);
    }

    @And("I click on the {string} remove button")
    public void i_click_on_the_(String company) {
        driver.findElement(By.id(company + "Btn")).click();
    }

    @After
    public void tearDown() { driver.quit(); }

}