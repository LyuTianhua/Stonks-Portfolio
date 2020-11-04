Feature: Register for siteFeature: Register for site

  Scenario Outline: Trying to register for site
    Given I am on the register page
    And I enter <email> in the 'Email' field
    And I enter <password> in the 'Password' field
    And I enter <confirm> in the 'Confirm' field
    And I click the Register link
    Then I should be on <url>
    Examples:
      | email    | password  | confirm   | url            |
      | 'tu3'    | 'tu3pass' | 'tu3pass' | 'index.jsp'    |
      | 'tu3'    | 'tu3pass' | ''        | 'register.jsp' |
      | 'tu3'    | 'tu3pass' | 'wrong'   | 'register.jsp' |

  Scenario: Trying to register for site with duplicated user name
    Given I am on the register page
    And I enter 'admin' in the 'Email' field
    And I enter 'tu1pass' in the 'Password' field
    And I enter 'tu1pass' in the 'Confirm' field
    And I click the Register link
    Then I should see error message 'Username already exists.'

  Scenario: Pressing cancel button takes you to login page
    Given I am on the register page
    When I press the cancel button
    Then I should be on 'index.jsp'