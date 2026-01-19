Feature: Product browsing by category
  In order to find products easily
  As an Askomdch customer
  I want to filter products using the category dropdown

  Background:
    Given I am on the homepage

  @dropdown @category
  Scenario Outline: View products by selecting a category
    When I select the "<category>" category from the product dropdown
    Then I should see only products related to the "<category>" category

    Examples:
      | category               |
      | mens-jeans            |
      | purses-and-handbags   |
      | womens-shoes          |
      | accessories           |
