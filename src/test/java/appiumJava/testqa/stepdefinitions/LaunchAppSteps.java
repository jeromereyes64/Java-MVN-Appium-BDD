package appiumJava.testqa.stepdefinitions;

import appiumJava.testqa.pages.LaunchAppPage;
import appiumJava.testqa.utils.DriverManager;
import io.cucumber.java.en.*;

public class LaunchAppSteps {

	LaunchAppPage launchAppPage;
	
    @Given("I open the Wikipedia mobile app")
    public void i_open_the_Wikipedia_mobile_app() {
    	launchAppPage = new LaunchAppPage(DriverManager.getDriver());
    	launchAppPage.waitForAppToOpen();
    }

    @Then("I should be able to see the search bar")
    public void i_should_be_able_to_see_the_search_bar() {
    	launchAppPage.assertVisible();
    }


}
