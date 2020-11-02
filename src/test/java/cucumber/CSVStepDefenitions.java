package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class CSVStepDefenitions {
    private static final String ROOT_URL = "http://localhost:8081/";
    WebDriver driver = RunCucumberTests.driver;
    WebDriverWait wait = RunCucumberTests.wait;

    @And("I click on upload csv modal")
    public void i_click_on_upload_csv_modal() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("upload-btn"))).click();
    }

    @And("I choose {string}")
    public void i_choose(String path) {
        String virtual_path = System.getProperty("user.dir") + "/" + path;
        wait.until(ExpectedConditions.elementToBeClickable(By.id("csv-file"))).sendKeys(virtual_path);
    }

    @And("I click upload csv")
    public void i_click_upload_csv() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("CSV_add_button"))).click();
    }

    @Then("I should {string} on the profile")
    public void iShouldAsmOnTheProfile(String ticker) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ticker))).click();
        assertTrue(driver.findElement(By.id(ticker)).isDisplayed());
    }
}
