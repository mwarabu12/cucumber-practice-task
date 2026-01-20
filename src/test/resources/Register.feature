Feature: User Registration
  In order to access my account
  As a new customer
  I want to register on the Askomdch application

  Background:
    Given I am on the user registration page

  @registration @positive
  Scenario Outline: Successful user registration
    When I submit the registration form with:
      | username | <username> |
      | email    | <email>    |
      | password | <password> |
    Then I should be redirected to my account page

    Examples:
      | username | email           | password |
      | eric170  | eric27@test.com | 12345    |
      | john170  | john17@test.com | 12345    |

  @registration @negative
  Scenario Outline: Registration fails due to invalid input
    When I submit the registration form with:
      | username | <username> |
      | email    | <email>    |
      | password | <password> |
    Then I should see the registration error message "<error_message>"

    Examples:
      | username | email            | password | error_message                          |
      |          | eric270@test.com | 12345    | Please enter a valid account username. |
      | eric17   | invalid-email    | 12345    | Please provide a valid email address.  |
      | eric17   | eric27@test.com  |          | Please enter an account password.      |
      | existing | admin@test.com   | 12345    | An account is already registered.      |
