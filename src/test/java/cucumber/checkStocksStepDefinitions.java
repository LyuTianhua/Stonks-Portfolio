package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class checkStocksStepDefinitions {
    private static final String ROOT_URL = "https://localhost:8080/";
    WebDriver driver = RunCucumberTests.driver;
    WebDriverWait wait = RunCucumberTests.wait;

    @And("I click the checker for {string}")
    public void iClickTheCheckerFor(String ticker) {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ticker+"portfolio"))).click();
    }

    @And("I click the historical checker for {string}")
    public void iClickTheHistoricalCheckerFor(String ticker) {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ticker+"Historical"))).click();
    }

    @And("I click the checker called {string}")
    public void iClickTheCheckerCalled(String ticker) {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ticker))).click();
    }

    @Then("{string} should not be rendered on the graph")
    public void shouldNotBeRenderedOnTheGraph(String ticker) {
        String checked = driver.findElement(By.id(ticker+"portfolio")).getAttribute("checked");
        assertTrue(checked == null);
    }

    @Then("{string} should be rendered on the graph for view stock")
    public void shouldBeRenderedOnTheGraphForViewStock(String ticker) {
        String checked = driver.findElement(By.id(ticker+"Historical")).getAttribute("checked");
        assertTrue(checked.equals("true"));
    }
    
    @Then("{string} should not be rendered on the graph for view stock")
    public void shouldNotBeRenderedOnTheGraphForViewStock(String ticker) {
        String checked = driver.findElement(By.id(ticker+"Historical")).getAttribute("checked");
        assertTrue(checked == null);
    }
}
