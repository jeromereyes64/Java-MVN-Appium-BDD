package appiumJava.testqa.pages;

import org.openqa.selenium.By;

import appiumJava.testqa.utils.CommonActions;
import io.appium.java_client.AppiumDriver;

public class PressFeaturedArticleAppPage {

	private final CommonActions actions;

	public PressFeaturedArticleAppPage(AppiumDriver driver) {
		this.actions = new CommonActions(driver);
		;
	}

	// Element Locators
	public static final By FEATURED_CONTENT = By.id("org.wikipedia:id/view_featured_article_card_content_container");
	
	// Context menu options
    public static final By OPEN = By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/title' and @text='Open']");
    public static final By OPEN_IN_NEW_TAB = By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/title' and @text='Open in new tab']");

    // Context menu items by index (3rd, 4th, and 5th items)
    public static final By CONTEXT_MENU_ITEM_3 = By.xpath("(//android.widget.LinearLayout[@resource-id='org.wikipedia:id/content'])[3]");
    public static final By CONTEXT_MENU_ITEM_4 = By.xpath("(//android.widget.LinearLayout[@resource-id='org.wikipedia:id/content'])[4]");
    public static final By CONTEXT_MENU_ITEM_5 = By.xpath("(//android.widget.LinearLayout[@resource-id='org.wikipedia:id/content'])[5]");

	// methods
	public void longPressContent() {
		actions.longClick(FEATURED_CONTENT);
	}
	
	public void verifyContextMenuExist() {
		actions.assertElementVisible(OPEN);
		actions.assertElementVisible(OPEN_IN_NEW_TAB);
		actions.assertElementVisible(CONTEXT_MENU_ITEM_3);
		actions.assertElementVisible(CONTEXT_MENU_ITEM_4);
		actions.assertElementVisible(CONTEXT_MENU_ITEM_5);
	}

}
