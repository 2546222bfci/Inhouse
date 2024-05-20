@smoke
Feature: F01_Register | users could register with new accounts //test suit(collection)

  Background:
    Given User is on the login page
    Given the user click on Signup button
    Given SignUp page is opened

  Scenario: guest user could signup with valid data successfully
    When The user enters the data
    And  click the create account button
    Then signup must be successful.

  Scenario: guest user enters an existing email during signup
    When The user enters the data with existing email
    And  click the create account button
    Then An error message should be displayed

  Scenario: guest user enters mismatched passwords during signup
    When The user enters the data with invalid confirmation pass
    And  click the create account button
    Then validation message for confirm pass field should be displayed

  Scenario: guest user leaves a required field blank during signup
    When The user doesn't enter the data
    And click the create account button
    Then validation messages should be displayed



