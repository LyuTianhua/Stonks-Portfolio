Feature: Register for site

  Scenario Outline: Trying to register for site
    Given I am on the register page
    When I enter <email> in the Email Address field
    And I enter <password> in the Password field
    And I enter <confirm> in the Verify Password field
    And I click the Register link
    Then I should be on <url>
    Examples:
      | email           | password  | confirm   | url            |
      | 'tu3@email.com' | 'tu3pass' | 'tu3pass' | 'index.jsp'    |
      | 'tu3@email.com' | 'tu3pass' | ''        | 'register.jsp' |
      | 'tu3@email.com' | 'tu3pass' | 'wrong'   | 'register.jsp' |
      | 'tu1@email.com' | 'tu1pass' | 'tu1pass' | 'register.jsp' |

  Scenario: Pressing cancel button takes you to login page
    Given I am on the register page
    When I press the cancel button
    Then I should be on 'index.jsp'