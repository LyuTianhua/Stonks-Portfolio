package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.logging.Level;

/**
 * Run all the cucumber tests in the current package.
 */
@RunWith(Cucumber.class)
@CucumberOptions()
//@CucumberOptions(features = {"src/test/resources/cucumber/loginpage.feature"})

public class RunCucumberTests {

	public static ChromeOptions options;

	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		options = new ChromeOptions();
		options.setHeadless(true);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
	}

}
