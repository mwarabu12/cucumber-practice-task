Feature: Add product to cart
  In order to purchase products online
  As a customer of AskOmDch
  I want to browse products and add them to my cart

  Scenario: Add a product to cart from the store page
    Given I am on the AskOmDch homepage
    When I click the checkout button
    Then I should be taken to the accessory product page

    When I select  products
    Then the product should be added to the shopping cart
