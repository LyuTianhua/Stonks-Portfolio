Feature: 3 Failed login attempts
  Scenario: I should be unable to log in on the 4th attempt
    Given I am on the login page to test failed attempts
    When I enter 'failed2@email.com' in Email Address input field to fail
    And I enter 'wrong' in Password input field to fail
    And I click the Sign In button to fail
    And I click the Sign In button to fail
    And I click the Sign In button to fail
    And I clear the password field to fail
    And I enter 'password' in Password input field to fail
    And I click the Sign In button to fail
    Then I see the error message 'Already attempted to login 3 times in the last 1 minutes. Account is locked temporarily'