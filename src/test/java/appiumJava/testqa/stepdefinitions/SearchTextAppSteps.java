package appiumJava.testqa.stepdefinitions;

import appiumJava.testqa.pages.SearchTextAppPage;
import appiumJava.testqa.utils.DriverManager;
import io.cucumber.java.en.*;

public class SearchTextAppSteps {

    SearchTextAppPage searchPage;

    @When("I tap on the search bar")
    public void i_tap_on_the_search_bar() {
        searchPage = new SearchTextAppPage(DriverManager.getDriver());
        searchPage.tapSearchBar();
    }

    @When("I enter {string} in the search field")
    public void i_enter_in_the_search_field(String text) {
        searchPage.enterSearchText(text);
    }

    @Then("I should see results related to {string}")
    public void i_should_see_results_related_to(String text) {
        searchPage.verifySearchResultsContain("New Zealand");
    }
}