package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;

/**
 * Run all the cucumber tests in the current package.
 */
@RunWith(Cucumber.class)
@CucumberOptions()
//@CucumberOptions(features = {"src/test/resources/cucumber/loginpage.feature"})

public class RunCucumberTests {

	public static ChromeOptions options;
	public static ChromeDriver driver;
	public static WebDriverWait wait;

	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		options = new ChromeOptions();
//		options.setHeadless(true);
		//for the mobile acceptance tests
		if(System.getProperty("mobile") != null && System.getProperty("mobile").equals("true")) {
			System.out.println("Running with mobile resolution");
			options.addArguments("--window-size=360,640");
		}
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		driver = new ChromeDriver(RunCucumberTests.options);
		wait = new WebDriverWait(driver, 5);
	}

	@AfterClass
	public static void tearDown() { driver.quit(); }

}
