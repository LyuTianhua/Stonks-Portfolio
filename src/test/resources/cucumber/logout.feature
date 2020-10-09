Feature: logout functionality
Scenario: Logout redirecting to login page
 	Given I am on 'home.jsp'
    When I press the log out button
    Then I should be on the 'index.jsp' page