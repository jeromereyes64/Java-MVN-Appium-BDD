package appiumJava.testqa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

/**
 * DriverManager handles the setup and teardown of the AppiumDriver instance. It
 * initializes an Android driver using UiAutomator2 with configuration for
 * installed apps.
 */
public class DriverManager {

	// Shared AppiumDriver instance
	public static AppiumDriver driver;

	/**
	 * Initializes the Appium driver for Android using UiAutomator2. This
	 * configuration assumes the app is already installed on the device.
	 *
	 * @throws MalformedURLException if the Appium server URL is malformed
	 */
	public static void initDriver() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();

		// Required Capabilities
		options.setPlatformName("Android");
		options.setDeviceName("0A241JEC218864"); // Replace with your real device/emulator ID
		options.setAutomationName("UiAutomator2");

		// Installed app details (Wikipedia in this case)
		options.setAppPackage("org.wikipedia");
		options.setAppActivity("org.wikipedia.main.MainActivity");

		// Optional wait settings
		options.setAppWaitForLaunch(true); // Wait for app to fully launch
		options.setAppWaitActivity("*"); // Accept any post-launch activity
		options.setAppWaitDuration(Duration.ofSeconds(10)); // Wait time before timeout

		// Additional Configs
		options.setNoReset(true);
		options.setFullReset(false);

		// Initialize the Android driver
		driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
	}

	/**
	 * Returns the current Appium driver instance.
	 *
	 * @return AppiumDriver instance
	 */
	public static AppiumDriver getDriver() {
		return driver;
	}

	/**
	 * Quits the driver if it's active. Used for cleanup after test execution.
	 */
	public static void quitDriver() {
		if (driver != null) {
			((AndroidDriver) driver).terminateApp("org.wikipedia");
			driver.quit();
			
		}
	}
}
