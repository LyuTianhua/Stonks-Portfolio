Feature: inactive functionality
  Scenario: Logout and directed to login page from home page
    Given I am on the home page
    When I do nothing for 120 seconds
    Then I should be at page 'index.jsp'