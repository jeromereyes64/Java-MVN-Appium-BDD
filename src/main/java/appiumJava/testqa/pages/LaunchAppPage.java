package appiumJava.testqa.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import appiumJava.testqa.utils.CommonActions;

public class LaunchAppPage {

	private final CommonActions actions;

	public LaunchAppPage(AppiumDriver driver) {
		this.actions = new CommonActions(driver); // ✅ Inject CommonActions
	}

	// element locators
	public static final By SEARCH_FIELD = By.xpath("//android.widget.TextView[@text=\"Search Wikipedia\"]");

	// methods
	public void assertVisible() {
		actions.assertElementVisible(SEARCH_FIELD);
	}

	public void waitForAppToOpen() {
		try {
			System.out.println("📱 Launching AoFrio app...");
			Thread.sleep(5000); // Simulate observation time
			System.out.println("✅ AoFrio app is launched.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
