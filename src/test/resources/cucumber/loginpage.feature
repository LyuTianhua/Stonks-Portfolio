Feature: login functionality

  Scenario: Open register page from login page
    Given I am on the login page
    When I click the Register tab
    Then I should be on page 'register.jsp'

  Scenario: Open home page from login page after successful login
    Given I am on the login page
    When I enter 'tu1@email.com' in Email Address input field
    And I enter 'tu1pass' in Password input field
    And I click the Sign In button
    Then I should be on page 'home.jsp'

  Scenario: Remain on login page after unsuccessful login
    Given I am on the login page
    When I enter 'wrong@usc.edu' in Email Address input field
    And I enter 'wrong' in Password input field
    And I click the Sign In button
    Then an error message "Password and Email don't match" should show up
