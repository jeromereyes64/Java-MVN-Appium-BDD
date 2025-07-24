package appiumJava.testqa.stepdefinitions;

import io.cucumber.java.en.*;
import appiumJava.testqa.pages.GeneralStorePage;
import appiumJava.testqa.pages.CartPage;
import appiumJava.testqa.utils.DriverManager;

public class GeneralStoreCheckoutAppSteps {

    GeneralStorePage generalStorePage;
    CartPage cartPage;

    @Given("I launch the General Store app")
    public void i_launch_the_general_store_app() {
    	this.generalStorePage = new GeneralStorePage(DriverManager.getDriver());
    	this.cartPage = new CartPage(DriverManager.getDriver());
    }

    @Given("I select country {string}")
    public void i_select_country(String country) {
        generalStorePage.selectCountry(country);
    }

    @Given("I enter my name {string}")
    public void i_enter_my_name(String name) {
        generalStorePage.enterName(name);
    }

    @Given("I choose gender {string}")
    public void i_choose_gender(String gender) {
        generalStorePage.selectGender(gender);
    }

    @Given("I click the \"Let's Shop\" button")
    public void i_click_the_lets_shop_button() {
        generalStorePage.tapLetsShop();
    }

    @When("I add the product {string} to the cart")
    public void i_add_the_product_to_the_cart(String productName) {
    	cartPage.addProductToCart(productName);
    }

    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
    	cartPage.goToCart();
    }

    @Then("I should see the product {string} in the checkout page")
    public void i_should_see_the_product_in_the_checkout_page(String productName) {
        cartPage.verifyProductInCart(productName);
    }
    
    @Then("I should see the correct total purchase amount in the checkout page")
    public void i_should_see_the_correct_total_purchase_amount_in_the_checkout_page() {
        cartPage.verifyPurchasedAmount();
    }

    @Then("I should be able to place the order successfully")
    public void i_should_be_able_to_place_the_order_successfully() {
        cartPage.tapPlaceOrder();
        cartPage.verifyOrderPlaced("Thank you for your order"); // Replace with actual toast/confirmation message
    }
}