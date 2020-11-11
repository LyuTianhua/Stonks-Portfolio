Feature: Graph displayed on home page

  Scenario: Blank Graph on home page
    Given I am signed in
    Then I should see a graph

  Scenario: Stock data on graph
    Given I am signed in
    And I have added stock
    Then I should see graphed stock data

  Scenario: Graph Shows Spy
    Given I am signed in
    Then I should see SPY graphed

  Scenario:
    Given I am signed in
    And I click 'SPY' button
    Then I shouldn't see SPY graph