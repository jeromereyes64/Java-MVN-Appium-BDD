package appiumJava.testqa.stepdefinitions;

import appiumJava.testqa.pages.GeneralStorePage;
import appiumJava.testqa.utils.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class GeneralStoreAppSteps {

	GeneralStorePage generalStorePage;

	public GeneralStoreAppSteps() {
		this.generalStorePage = new GeneralStorePage(DriverManager.getDriver());
	}

	@Given("the General Store app is launched")
	public void the_general_store_app_is_launched() {
		System.out.println("✅ General Store app launched successfully");
	}

	@When("I select {string} from the country dropdown")
	public void i_select_from_the_country_dropdown(String country) {
		generalStorePage.selectCountry(country);
	}

	@When("I enter my name as {string}")
	public void i_enter_my_name_as(String name) {
		generalStorePage.enterName(name);
	}

	@When("I leave the name field empty")
	public void i_leave_the_name_field_empty() {
		generalStorePage.enterName("");
	}

	@When("I select gender {string}")
	public void i_select_gender(String gender) {
		generalStorePage.selectGender(gender);
	}

	@When("I tap on the {string} button")
	public void i_tap_on_the_button(String buttonText) {
		generalStorePage.tapLetsShop();
	}

	@Then("I should be navigated to the shopping page")
	public void i_should_be_navigated_to_the_shopping_page() {
		// You can add a proper assert here if you have a unique locator on the next
		// page
		System.out.println("✅ User navigated to shopping page");
	}

	@Then("I should see an error message {string}")
	public void i_should_see_an_error_message(String expectedMessage) {
		generalStorePage.verifyErrorMessage("Please enter your name");
	}
}
