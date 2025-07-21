package appiumJava.testqa.stepdefinitions;

import appiumJava.testqa.pages.ScrollSearchListAppPage;
import appiumJava.testqa.pages.SearchTextAppPage;
import appiumJava.testqa.utils.DriverManager;
import io.cucumber.java.en.*;

public class ScrollSearchListAppSteps {
	
	SearchTextAppPage searchTextAppPage;
	ScrollSearchListAppPage scrollSearchListAppPage;
	
	@Given("the app is launched")
	public void the_app_is_launched() {
		this.searchTextAppPage = new SearchTextAppPage(DriverManager.getDriver());
		this.scrollSearchListAppPage = new ScrollSearchListAppPage(DriverManager.getDriver());
		
	}
	
	@When("I search for {string}")
	public void i_search_for(String string) {
		searchTextAppPage.tapSearchBar();
		searchTextAppPage.enterSearchText("Scroll");
	}
	
	@When("I scroll until I see {string} in the search list")
	public void i_scroll_until_i_see_in_the_search_list(String string) {
		scrollSearchListAppPage.scrollUntilScrollSerpentVisible();
	}
	
	@Then("I should see {string} displayed on the screen")
	public void i_should_see_displayed_on_the_screen(String string) {
		scrollSearchListAppPage.assertScrollSerpentIsVisible();
	}
	
}
