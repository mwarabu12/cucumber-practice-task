Feature: Place an order
  As a customer
  I want to log in and checkout
  So that I can place my order successfully

  Scenario: Login and place an order successfully
    Given products already exist in my cart
    When I click checkout button
    And I fill in the shipping information to place an order
      | firstName | lastName | streetAddress | city   | zip  |
      | Umuringa      | Ineza      | KG 123        | Kigali | 00000 |
    Then the order should be placed successfully
