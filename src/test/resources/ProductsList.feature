Feature: Product Navigation Functionality
  In order to browse available products
  As a visitor of AskOmDch ecommerce
  I want to access the product listing page successfully

  Scenario: Navigate to product listing page
    Given I am on the landing page of AskOmDch ecommerce
    When I click the Store button
    Then I should be taken to the Store product listing page
    And I should see a list of available products
