Feature: Graph displayed on home page

  Scenario: Blank Graph on home page
    Given I am signed in
    Then I should see a graph

  Scenario: Stock data on graph
    Given I am signed in
    And I have added stock
    Then I should see graphed stock data