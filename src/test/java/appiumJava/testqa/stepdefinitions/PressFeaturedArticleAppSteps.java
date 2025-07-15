package appiumJava.testqa.stepdefinitions;

import appiumJava.testqa.pages.LaunchAppPage;
import appiumJava.testqa.pages.PressFeaturedArticleAppPage;
import appiumJava.testqa.utils.DriverManager;
import io.cucumber.java.en.*;

public class PressFeaturedArticleAppSteps {
	
	private final LaunchAppPage launchAppPage;
    private final PressFeaturedArticleAppPage pressFeaturedArticleAppPage;
    
    public PressFeaturedArticleAppSteps() {
        this.launchAppPage = new LaunchAppPage(DriverManager.getDriver()); // ✅ instantiate here
        this.pressFeaturedArticleAppPage = new PressFeaturedArticleAppPage(DriverManager.getDriver());
    }
	
	@Given("the Wikipedia app is launched")
	public void the_wikipedia_app_is_launched() {
		launchAppPage.waitForAppToOpen();
	}

	@When("I navigate to the home page")
	public void i_navigate_to_the_home_page() {
		System.out.println("Navigated to Wikipedia home page");
	}
	
	@When("I long press the featured article")
	public void i_long_press_the_featured_article() {
		pressFeaturedArticleAppPage.longPressContent();
	}
	
	@Then("I should see the context menu or relevant long-press action")
	public void i_should_see_the_context_menu_or_relevant_long_press_action() {
		pressFeaturedArticleAppPage.verifyContextMenuExist();
	}
	
}
