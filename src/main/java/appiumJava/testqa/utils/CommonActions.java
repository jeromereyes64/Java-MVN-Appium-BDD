package appiumJava.testqa.utils;

import io.appium.java_client.AppiumDriver;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * CommonActions provides reusable methods to interact with mobile elements such
 * as clicking, sending text input, and asserting visibility. These methods wrap
 * basic Appium operations with added logging and error handling.
 */
public class CommonActions {

	// Reference to the shared AppiumDriver instance
	private final AppiumDriver driver;

	/**
	 * Constructor to initialize CommonActions with the provided Appium driver.
	 *
	 * @param driver the active AppiumDriver instance
	 */
	public CommonActions(AppiumDriver driver) {
		this.driver = driver;
	}

	/**
	 * Clicks on an element identified by the given locator.
	 *
	 * @param locator the By locator of the target element
	 */
	public void click(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			element.click();
			System.out.println("✅ Clicked on element: " + locator);
		} catch (Exception e) {
			System.err.println("❌ Failed to click element: " + locator);
			throw e;
		}
	}

	/**
	 * Sends a string input to an element after clearing its content.
	 *
	 * @param locator the By locator of the target input field
	 * @param text    the string to input
	 */
	public void sendKeys(By locator, String text) {
		try {
			WebElement element = driver.findElement(locator);
			element.clear();
			element.sendKeys(text);
			System.out.println("✅ Sent keys to element: " + locator);
		} catch (Exception e) {
			System.err.println("❌ Failed to send keys to element: " + locator);
			throw e;
		}
	}
	
	/**
	 * Performs a long click (long press) on the element identified by the given locator.
	 *
	 * @param locator the By locator of the element to long press
	 * @throws Exception if the element cannot be found or the action fails
	 */
	public void longClick(By locator) {
	    try {
	        WebElement element = driver.findElement(locator);

	        // Create finger input for touch actions
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence longPress = new Sequence(finger, 1);

	        // Move finger to element and press
	        longPress.addAction(finger.createPointerMove(
	                Duration.ofMillis(0),
	                PointerInput.Origin.fromElement(element),
	                0, 0));
	        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

	        // Hold for 2 seconds
	        longPress.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2)));

	        // Release finger
	        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        driver.perform(Collections.singletonList(longPress));

	        System.out.println("Long click performed on element: " + locator);
	    } catch (Exception e) {
	        System.err.println("Failed to perform long click on element: " + locator);
	        throw e;
	    }
	}
	
	
	
	/**
	 * Verifies that the web element identified by the given locator is visible on the page.
	 *
	 * @throws AssertionError if the element is not visible
	 * @throws Exception      if an unexpected error occurs during element lookup
	 */
	public void assertElementVisible(By locator) {
		try {
			boolean isDisplayed = driver.findElement(locator).isDisplayed();
			Assert.assertTrue(isDisplayed, "Element not visible: " + locator);
			System.out.println("Element is visible: " + locator);
		} catch (Exception e) {
			System.err.println("Element not found or not visible: " + locator);
			throw e;
		}
	}

	/**
	 * Asserts that the element identified by the locator is visible.
	 *
	 * @param locator the By locator of the element to check
	 */
	public void assertElementsContainText(By locator, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		System.out.println("🔍 Number of elements found: " + elements.size());

		if (elements.isEmpty()) {
			throw new AssertionError("❌ No elements found for locator: " + locator);
		}

		boolean foundMatch = false;

		for (WebElement el : elements) {
			String text = el.getText();
			System.out.println("📄 Full Title: " + text);

			if (text.toLowerCase().contains(expectedText.toLowerCase())) {
				System.out.println("✅ Match found: " + text);
				foundMatch = true;
			}
		}

		if (!foundMatch) {
			throw new AssertionError("❌ No element text matched expected: " + expectedText);
		}
	}
}
