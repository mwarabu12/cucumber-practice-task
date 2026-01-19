Feature: User Login
  In order to access my account
  As a registered user
  I want to log in to the Askomdch application

  Background:
    Given I am on the login page

  @login @positive
  Scenario Outline: Successful login with valid credentials
    When I log in with:
      | username | <username> |
      | password | <password> |
    Then I should be logged in successfully

    Examples:
      | username | password |
      | eric17   | 12345    |
      | john17   | 12345    |

  @login @negative
  Scenario Outline: Login fails with invalid credentials
    When I log in with:
      | username | <username> |
      | password | <password> |
    Then I should see the login error message "<error_message>"

    Examples:
      | username | password | error_message                          |
      | eric17   | wrong123 | Error: The password you entered is incorrect. |
      | unknown  | 12345    | Error: No account found with that username.   |
      |          | 12345    | Error: Username is required.                  |
      | eric17   |          | Error: Password is required.                  |
