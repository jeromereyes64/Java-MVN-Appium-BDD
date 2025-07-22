package appiumJava.testqa.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import appiumJava.testqa.utils.CommonActions;

public class SearchTextAppPage {
	
    private final CommonActions actions;

    public SearchTextAppPage(AppiumDriver driver) {
		this.actions = new CommonActions(driver);
    }

    // Element locators
    public static final By SEARCH_BAR = By.xpath("//android.widget.TextView[@text=\"Search Wikipedia\"]");
    public static final By SEARCH_FIELD = By.id("org.wikipedia:id/search_src_text");
    public static final By SEARCH_RESULT_ITEMS = By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']");

    // Tap on search bar
    public void tapSearchBar() {
        actions.click(SEARCH_BAR);
    }

    // Enter search text
    public void enterSearchText(String searchTerm) {
        actions.sendKeys(SEARCH_FIELD, searchTerm);
        actions.hideKeyboardIfVisible();
    }

    public void verifySearchResultsContain(String expectedText) {
    	actions.assertElementsContainText(SEARCH_RESULT_ITEMS, expectedText);
    }


}