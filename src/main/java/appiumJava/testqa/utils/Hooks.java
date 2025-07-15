package appiumJava.testqa.utils;

import io.cucumber.java.Before;
import io.cucumber.java.After;

/**
 * Hooks class contains setup and teardown methods for each Cucumber scenario.
 * These methods are automatically invoked before and after scenarios run.
 */
public class Hooks {

    /**
     * This method is executed before each Cucumber scenario.
     * It initializes the Appium driver using the DriverManager class.
     *
     * @throws Exception if driver setup fails
     */
    @Before
    public void setUp() throws Exception {
        DriverManager.initDriver();
    }

    /**
     * This method is executed after each Cucumber scenario.
     * It quits the Appium driver to release resources and end the session.
     */
    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
