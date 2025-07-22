@generalStore
Feature: Shopping preferences selection in General Store app

  As a user
  I want to select my shopping preferences
  So that I can proceed to shop in the General Store app

  Background:
    Given the General Store app is launched

  @CountrySelection
  Scenario Outline: User selects country and proceeds to shop
    When I select "<country>" from the country dropdown
    And I enter my name as "<name>"
    And I select gender "<gender>"
    And I tap on the "Let's Shop" button
    Then I should be navigated to the shopping page

    Examples:
      | country  | name     | gender |
      | Andorra  | Jerome   | Male   |
      | Argentina| Maria    | Female |

  @NameValidation
  Scenario: User tries to proceed without entering a name
    When I select "Andorra" from the country dropdown
    And I leave the name field empty
    And I select gender "Male"
    And I tap on the "Let's Shop" button
    Then I should see an error message "Please enter your name"
