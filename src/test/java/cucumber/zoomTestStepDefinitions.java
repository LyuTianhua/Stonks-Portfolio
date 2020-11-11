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

public class zoomTestStepDefinitions {
	
	private static final String ROOT_URL = "https://localhost:8080/";
	WebDriver driver = RunCucumberTests.driver;
	WebDriverWait wait = RunCucumberTests.wait;

	@And("I click the + button under the graph")
    public void i_click_zoom_in() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        
        WebElement zoomButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/label[1]"));
        zoomButton.click();
    }
	
	@Then("I should see the graph zoom in")
    public void i_see_the_graph_zoom_in() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        
        WebElement dateFrom = driver.findElement(By.xpath("//*[@id=\"fromGraph\"]"));
        assertTrue(!dateFrom.getAttribute("value").isEmpty());
    }
	
	@And("I click the - button under the graph")
    public void i_click_zoom_out() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        
        WebElement zoomButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/label[2]"));
        zoomButton.click();
    }
	
	@Then("I should see the graph zoom out")
    public void i_see_the_graph_zoom_out() {
        try {
            Thread.sleep(2000);
        } catch(Exception ie) {
        }
        
        WebElement dateFrom = driver.findElement(By.xpath("//*[@id=\"fromGraph\"]"));
        assertTrue(!dateFrom.getAttribute("value").isEmpty());
    }
    
}