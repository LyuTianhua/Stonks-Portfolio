Feature: add stock to portfolio

  Scenario Outline: adding stock
    Given I am signed in
    And I click on add stock modal
    And I enter 'TSLA' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I click on add stock
    Then I should see <shares> 'TSLA' stock on the portfolio
    Examples:
      | quantity | shares |
      | '10'     | '10'   |
      | '10'     | '20'   |

  Scenario Outline: Remove stock from home page
    Given I am signed in
    And I enter <quantity> into 'TSLA' remove input
    And I click on the 'TSLA' remove button
    Then I should see <shares> 'TSLA' stock on the portfolio
    Examples:
      | quantity | shares |
      | '5'      | '15'   |
      | '15'     | '0'    |
