@smoke
Feature: Scroll through search results until the target element is visible

  As a mobile app user
  I want to scroll through the search results
  So that I can find the "Scroll Serpent" item in the list

  Background:
    Given the app is launched

  Scenario: Scroll until "Scroll Serpent" appears in the search list
    When I search for "Scroll"
    And I scroll until I see "Scroll Serpent" in the search list
    Then I should see "Scroll Serpent" displayed on the screen