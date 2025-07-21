@smoke
Feature: Long press on Featured Article in Wikipedia Home Page

  As a user
  I want to long press the featured article on the Wikipedia home page
  So that I can open context actions (if available)

  Background:
    Given the Wikipedia app is launched

  Scenario: Long press on the Featured Article section
    When I navigate to the home page
    And I long press the featured article
    Then I should see the context menu or relevant long-press action
