package appiumJava.testqa.pages;

import org.openqa.selenium.By;

import appiumJava.testqa.utils.CommonActions;
import io.appium.java_client.AppiumDriver;

public class ScrollSearchListAppPage {
	
	private final CommonActions actions;
	
	public ScrollSearchListAppPage(AppiumDriver driver) {
		this.actions = new CommonActions(driver);
	}
	
    // Element locators
    public static final By SCROLL_ELEMENT = By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"Scroll Serpent\"]");
    
    /**
     * Scrolls until "Scroll Serpent" is visible in the search list.
     */
    public void scrollUntilScrollSerpentVisible() {
        actions.scrollToElement(SCROLL_ELEMENT, 40); // max 5 scrolls
    }

    /**
     * Verifies that "Scroll Serpent" is displayed on the screen.
     */
    public void assertScrollSerpentIsVisible() {
        actions.assertElementVisible(SCROLL_ELEMENT);
    }
    
}
