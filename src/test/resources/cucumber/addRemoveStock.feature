Feature: add stock to portfolio

  Scenario Outline: adding stock
    Given I am signed in
    And I click on add stock modal
    And I enter 'TSLA' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I enter '10/11/2020' into 'date-purchased'
    And I click on add stock
    Then I should see <shares> 'TSLA' stock on the portfolio
    Examples:
      | quantity | shares |
      | '10'     | '10'   |
      | '10'     | '20'   |
      | '0'      | '20'   |

  Scenario Outline: Remove stock from home page
    Given I am signed in
    And I enter <quantity> into 'TSLA' remove input
    And I click on the 'TSLA' remove button
    Then I should see <shares> 'TSLA' stock on the portfolio
    Examples:
      | quantity | shares |
      | '5'      | '15'   |
      | '15'     | '0'    |
      
  Scenario Outline: Error message for stocks not on NYSE or NASDAQ (invalid ticker)
    Given I am signed in
    And I click on add stock modal
    And I enter 'AZN' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I click on add stock
    Then I should see no changes to the page
    Examples:
      | quantity |
      | '1'      |

  Scenario Outline: adding invalid number of stocks
    Given I am signed in
    And I click on add stock modal
    And I enter 'AAPL' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I click on add stock
  	Then I should see an error message stating that it is an invalid ticker
    Examples:
      | quantity | 
      | '0'      |
      | '-1'     | 