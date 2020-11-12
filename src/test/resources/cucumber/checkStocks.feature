Feature: Check Stocks

  Scenario: When I click on a portfolio stock after I log in, it should be deselected
    Given I am signed in
    And I click on upload csv modal
    And I choose 'csv.csv'
    And I click upload csv
    And I click the checker for 'PLAY'
    Then 'PLAY' should not be rendered on the graph

  Scenario: I can deselect all portfolio stocks with the Include (All) toggle button
    Given I am signed in
    And I click on upload csv modal
    And I choose 'csv.csv'
    And I click upload csv
    And I click the checker called 'checkAll'
    Then 'PLAY' should not be rendered on the graph

  Scenario Outline: I can select a historical stock and it should appear on graph
    Given I am signed in
    And I click on add view modal
    And I enter 'TSLA' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I enter '10/11/2020' into 'date-purchased-view'
    And I enter '10/21/2020' into 'date-sold-view'
    And I click on view stock
    And I click the historical checker for 'TSLA'
    Then 'TSLA' should be rendered on the graph for view stock
    Examples:
      | quantity | shares |
      | '10'     | '10'   |

  Scenario Outline: I can deselect a historical stock and it should appear on graph
    Given I am signed in
    And I click on add view modal
    And I enter 'AAPL' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I enter '10/11/2020' into 'date-purchased-view'
    And I enter '10/21/2020' into 'date-sold-view'
    And I click on view stock
    And I click the historical checker for 'AAPL'
    And I click the historical checker for 'AAPL'
    Then 'AAPL' should not be rendered on the graph for view stock

    Examples:
      | quantity | shares |
      | '10'     | '10'   |