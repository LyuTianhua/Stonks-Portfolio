Feature: Graph displayed on home page

  Scenario: Blank Graph on home page
    Given I am signed in
    Then I should see a graph

  Scenario: Stock data on graph
    Given I am signed in
    And I have added stock
    Then I should see graphed stock data
    
  Scenario Outline: Selecting pre-determined 1 week date range for graph
      Given I am signed in
      When I click the 1 week button under the graph
      Then the graph should show portfolio values for the past week
      
  Scenario Outline: Selecting pre-determined 3 month date range for graph
      Given I am signed in
      When I click the 3 months button under the graph
      Then the graph should show portfolio values for the past 3 months
      
  Scenario Outline: Selecting pre-determined 1 year date range for graph
      Given I am signed in
      When I click the 1 year button under the graph
      Then the graph should show portfolio values for the past 1 year
  