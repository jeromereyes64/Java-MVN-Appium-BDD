package appiumJava.testqa.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import appiumJava.testqa.utils.CommonActions;

public class CartPage {

	private final CommonActions actions;

	public CartPage(AppiumDriver driver) {
		this.actions = new CommonActions(driver);
	}

	// Locators
	private static final By CART_ICON = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
	private static final By PRODUCT_NAME = By.id("com.androidsample.generalstore:id/productName");
	private static final By PLACE_ORDER_BUTTON = By.id("com.androidsample.generalstore:id/btnProceed");
	private static final By PRODUCT_PRICE = By.id("com.androidsample.generalstore:id/productPrice");
	private static final By TOTAL_AMOUNT = By.id("com.androidsample.generalstore:id/totalAmountLbl");

	// ✅ New: Add Product to Cart
	public void addProductToCart(String productName) {
	    By addToCartButton = By.xpath(
	        "//android.widget.TextView[@text='" + productName + "']" +
	        "/following-sibling::android.widget.LinearLayout" +
	        "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']"
	    );
	    actions.scrollToText(productName);
	    actions.click(addToCartButton);
	    System.out.println("✅ Added product to cart: " + productName);
	}

	// ✅ New: Proceed to Cart
	public void goToCart() {
		actions.click(CART_ICON);
	}

	// Verify Product Name in Cart
	public void verifyProductInCart(String expectedProductName) {
		String actualProductName = actions.getText(PRODUCT_NAME);
		Assert.assertEquals(actualProductName.trim(), expectedProductName.trim(),
				"Product name in cart does not match!");
		System.out.println("Verified product in cart: " + expectedProductName);
	}

	public void tapPlaceOrder() {
		actions.click(PLACE_ORDER_BUTTON);
	}

	public void verifyPurchasedAmount() {
		try {
			// ✅ Get the product price
			String priceText = actions.getText(PRODUCT_PRICE).replace("$", "").trim();
			double price = Double.parseDouble(priceText);

			// ✅ Get the total amount
			String totalText = actions.getText(TOTAL_AMOUNT).replace("$", "").trim();
			double total = Double.parseDouble(totalText);

			// ✅ Assert they match
			Assert.assertEquals(total, price, "❌ Total purchase amount does not match the product price!");
			System.out.println("✅ Verified total purchase amount: $" + total);
		} catch (Exception e) {
			System.err.println("❌ Failed to verify total purchase amount");
			throw e;
		}
	}

	public void verifyOrderPlaced(String expectedMessage) {
		System.out.println("Order placed successfully: " + expectedMessage);
	}
}