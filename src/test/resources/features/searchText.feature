@Regression @smoke
Feature: Wikipedia Search Feature

  Scenario: Search for a term using the Wikipedia search bar
    Given I open the Wikipedia mobile app
    When I tap on the search bar
    And I enter "New Zealand" in the search field
    Then I should see results related to "New Zealand"