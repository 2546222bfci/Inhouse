@smoke
Feature: F01_Register | users could register with new accounts //test suit(collection)

  Background:
    Given User is on the login page

  Scenario: guest user could signup with valid data successfully
    When the user click on Signup button
    Then SignUp page is opened
    When The user enters "test", "test", "jjj@gmail.com","1234567891","test1234", and "test1234"
    And  click the create account button
    Then signup must be successful.

  Scenario: guest user enters an existing email during signup
    When The user enters "tester", "test", "tester@test.com","0526542875","test1234", and "test1234"
    And  click the create account button
    Then An error message should be displayed

  Scenario: guest user enters mismatched passwords during signup
    When The user enters "tester", "test", "tester1@test.com","4367867867","test1234", and "test123"
    And  click the create account button
    Then An error message should be displayed

  Scenario: guest user leaves a required field blank during signup
    When The user enters "", "test", "adm@test.com","051 656 7766","admin123", and "admin123"
    And click the create account button
    Then An validation messages should be displayed
