Feature: Register for site

  Scenario: Successfull registration for site
    Given I am on the register page
    When I enter 'tu3@email.com' in the Email Address field
    And I enter 'tu3pass' in the Password field
    And I enter 'tu3pass' in the Verify Password field
    And I click the Register link
    Then I should be on 'index.jsp'

  Scenario: Failed to register because missing paramater
    Given I am on the register page
    When I enter 'tu3@email.com' in the Email Address field
    And I enter 'tu3pass' in the Password field
    And I click the Register link
    Then I should be on 'register.jsp'

  Scenario: Failed to register because passwords dont match
    Given I am on the register page
    When I enter 'tu3@email.com' in the Email Address field
    And I enter 'tu3pass' in the Password field
    And I enter 'wrong' in the Verify Password field
    And I click the Register link
    Then I should be on 'register.jsp'

  Scenario: Failed to register because email exists
    Given I am on the register page
    When I enter 'tu1@email.com' in the Email Address field
    And I enter 'tu1pass' in the Password field
    And I enter 'tu1pass' in the Verify Password field
    And I click the Register link
    Then I should be on 'register.jsp'

  Scenario: Pressing cancel button takes you to login page
    Given I am on the register page
    When I press the cancel button
    Then I should be on 'index.jsp'
