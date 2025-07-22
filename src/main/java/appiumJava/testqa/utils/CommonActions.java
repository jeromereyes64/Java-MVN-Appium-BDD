package appiumJava.testqa.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;

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

//import io.appium.java_client.android.nativekey.KeyEvent;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.AndroidDriver;

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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			System.out.println("✅ Clicked on element: " + locator);
		} catch (Exception e) {
			System.err.println("❌ Failed to click element: " + locator);
			throw e;
		}

//		AndroidDriver driver = (AndroidDriver) this.driver;
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
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
	 * Performs a long click (long press) on the element identified by the given
	 * locator.
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
			longPress.addAction(
					finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.fromElement(element), 0, 0));
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
	 * Verifies that the web element identified by the given locator is visible on
	 * the page.
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

	/**
	 * Asserts that the element identified by the locator has the expected text.
	 *
	 * @param locator      the By locator of the element
	 * @param expectedText the exact text expected
	 */
	public void assertElementTextEquals(By locator, String expectedText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		String actualText = element.getText();
		System.out.println("🔍 Actual Text: " + actualText);

		Assert.assertEquals(actualText.trim(), expectedText.trim(), "❌ Text does not match for element: " + locator);
		System.out.println("✅ Text assertion passed: " + expectedText);
	}

	/**
	 * Hides the keyboard if it is visible.
	 */
	public void hideKeyboardIfVisible() {
		try {
			if (driver instanceof HidesKeyboard) {
				((HidesKeyboard) driver).hideKeyboard();
				System.out.println("✅ Keyboard hidden successfully.");
			} else {
				System.out.println("ℹ️ Driver does not support hiding keyboard.");
			}
		} catch (Exception e) {
			System.out.println("ℹ️ Keyboard was not visible or already hidden.");
		}
	}

	/**
	 * Scrolls vertically until the element is visible or until max scroll attempts
	 * are reached.
	 */
	public void scrollToElement(By locator, int maxScrolls) {
		// ✅ Get screen dimensions
		int screenHeight = driver.manage().window().getSize().height; // e.g., 2400 px
		int screenWidth = driver.manage().window().getSize().width; // e.g., 1080 px
		int scrollCount = 0; // ✅ Track how many scrolls have been performed

		while (scrollCount < maxScrolls) {
			try {
				// ✅ If the element is found and displayed, stop scrolling
				WebElement element = driver.findElement(locator);
				if (element.isDisplayed()) {
					System.out.println("✅ Element found after scrolling: " + locator);
					return;
				}
			} catch (Exception ignored) {
				// Element not visible yet, continue scrolling
			}

			// ✅ Calculate swipe coordinates (vertical scroll)
			int startY = (int) (screenHeight * 0.7); // Start near bottom (70% of screen)
			int endY = (int) (screenHeight * 0.3); // End near top (30% of screen)
			int centerX = screenWidth / 2; // Swipe in the middle of the screen

			// ✅ Perform swipe action using W3C actions
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 1);

			swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
			swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			swipe.addAction(
					finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, endY));
			swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(swipe));

			scrollCount++;
			System.out.println("🔄 Scrolling attempt " + scrollCount + " for element: " + locator);

			// ✅ Give the list time to refresh before next scroll
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
			}
		}

		throw new AssertionError("❌ Element not found after " + maxScrolls + " scrolls: " + locator);
	}

	/**
	 * Drags an element from the source locator and drops it onto the target
	 * locator.
	 *
	 * @param sourceLocator the By locator of the element to drag
	 * @param targetLocator the By locator of the element to drop onto
	 */
	public void dragAndDrop(By sourceLocator, By targetLocator) {
		try {
			WebElement source = driver.findElement(sourceLocator);
			WebElement target = driver.findElement(targetLocator);

			// ✅ Get source and target center points
			int startX = source.getRect().getX() + (source.getSize().getWidth() / 2);
			int startY = source.getRect().getY() + (source.getSize().getHeight() / 2);

			int endX = target.getRect().getX() + (target.getSize().getWidth() / 2);
			int endY = target.getRect().getY() + (target.getSize().getHeight() / 2);

			// ✅ Create a swipe gesture (drag → drop)
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence dragAndDrop = new Sequence(finger, 1);

			dragAndDrop
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
			dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			dragAndDrop.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(500)));
			dragAndDrop.addAction(
					finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
			dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(dragAndDrop));

			System.out.println("✅ Dragged element from " + sourceLocator + " to " + targetLocator);
		} catch (Exception e) {
			System.err.println("❌ Failed to drag and drop from " + sourceLocator + " to " + targetLocator);
			throw e;
		}
	}

	/**
	 * Scrolls the view until the given visible text is found. Uses Android
	 * UiScrollable with UiAutomator.
	 *
	 * @param visibleText The exact text to scroll to
	 */
	public void scrollToText(String visibleText) {
		try {
			driver.findElement(
					AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
							+ "new UiSelector().text(\"" + visibleText + "\"))"));
			System.out.println("✅ Scrolled to text: " + visibleText);
		} catch (Exception e) {
			System.err.println("❌ Failed to scroll to text: " + visibleText);
			throw e;
		}
	}

	/**
	 * Clicks on an element by its visible text. Uses Android UiSelector.
	 *
	 * @param visibleText The exact text of the element to click
	 */
	public void clickByText(String visibleText) {
		try {
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + visibleText + "\")")).click();
			System.out.println("✅ Clicked element with text: " + visibleText);
		} catch (Exception e) {
			System.err.println("❌ Failed to click element with text: " + visibleText);
			throw e;
		}
	}

	/**
	 * Gets the text from a Toast message on Android.
	 *
	 * @return The toast message text
	 */
	public String getToastMessage() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement toastElement = wait.until(
	                ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[1]"))
	        );
	        String message = toastElement.getAttribute("text"); 
	        System.out.println("✅ Toast message captured: " + message);
	        return message;
	    } catch (Exception e) {
	        throw new AssertionError("❌ Failed to capture toast message: " + e.getMessage());
	    }
	}


}
