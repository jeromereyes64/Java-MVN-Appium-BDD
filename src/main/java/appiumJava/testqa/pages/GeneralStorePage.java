package appiumJava.testqa.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

import appiumJava.testqa.utils.CommonActions;

public class GeneralStorePage {

	private final CommonActions actions;

	public GeneralStorePage(AppiumDriver driver) {
		this.actions = new CommonActions(driver);
	}

	// Locators
	private static final By COUNTRY_DROPDOWN = By.id("com.androidsample.generalstore:id/spinnerCountry");
	private static final By NAME_FIELD = By.id("com.androidsample.generalstore:id/nameField");
	private static final By MALE_RADIO = By.id("com.androidsample.generalstore:id/radioMale");
	private static final By FEMALE_RADIO = By.id("com.androidsample.generalstore:id/radioFemale");
	private static final By LETS_SHOP_BUTTON = By.id("com.androidsample.generalstore:id/btnLetsShop");
	private static final By TOAST_MESSAGE = By.xpath("//android.widget.Toast[@text='Please enter your name']");

	// Actions
	public void selectCountry(String countryName) {
		actions.click(COUNTRY_DROPDOWN);
		actions.scrollToText(countryName);
		actions.clickByText(countryName);
	}

	public void enterName(String name) {
		actions.sendKeys(NAME_FIELD, name);
		actions.hideKeyboardIfVisible();
	}

	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("Male")) {
			actions.click(MALE_RADIO);
		} else {
			actions.click(FEMALE_RADIO);
		}
	}

	public void tapLetsShop() {
		actions.click(LETS_SHOP_BUTTON);
	}

	public void verifyErrorMessage(String expectedMessage) {
		String actualToast = actions.getToastMessage();
		Assert.assertEquals(actualToast.trim(), expectedMessage.trim(), "Toast message mismatch!");
		System.out.println("Toast message verified: " + expectedMessage);
	}

}