@generalStore
Feature: Checkout product from General Store

  As a user
  I want to add a product to the cart and checkout
  So that I can complete a purchase

  @checkout
  Scenario Outline: Checkout a product successfully
    Given I launch the General Store app
    And I select country "<country>"
    And I enter my name "<name>"
    And I choose gender "<gender>"
    And I click the "Let's Shop" button
    When I add the product "<productName>" to the cart
    And I proceed to checkout
    Then I should see the product "<productName>" in the checkout page
    And I should see the correct total purchase amount in the checkout page
    And I should be able to place the order successfully

    Examples:
      | country      | name     | gender | productName        |
      | Argentina    | Jerome   | Male   | Air Jordan 4 Retro |
      | New Zealand  | Michelle | Female | PG 3               |