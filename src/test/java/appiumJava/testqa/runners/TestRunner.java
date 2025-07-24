package appiumJava.testqa.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestRunner class is the entry point for executing Cucumber tests with TestNG.
 * It uses CucumberOptions to configure feature file locations, step definition paths,
 * plugins for reporting, and tags for selective execution.
 */
@CucumberOptions(
    // Path to your .feature files
    features = "src/test/resources/features",

    // Step definitions and Hooks class packages
    glue = {"appiumJava.testqa.stepdefinitions", "appiumJava.testqa.utils"},

    // Plugins to generate test reports
    plugin = {
        "pretty",                                // Clean console output
        "html:target/cucumber-report.html",      // HTML report output
        "json:target/cucumber-report.json"       // JSON report output
    },

    // Makes the console output more readable
    monochrome = true,

    // Only run scenarios tagged with @debug (you can change this tag as needed)
    tags = "@generalStore"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Provides scenarios to TestNG for execution.
     * Setting parallel = false to run scenarios sequentially.
     * 
     * @return a 2D array of scenarios
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
