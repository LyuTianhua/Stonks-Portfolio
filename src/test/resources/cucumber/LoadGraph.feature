Feature: Graph displayed on home page

  Scenario: Blank Graph on home page
    Given I am signed in
    Then I should see a graph

  Scenario: Stock data on graph
    Given I am signed in
    And I have added stock
    Then I should see graphed stock data
  
  Scenario Outline: Adding date from after date to
  	Given I am signed in
  	And I enter <from> into 'fromGraph'
    And I enter <to> into 'toGraph'
    Then I should see an error message stating that these are invalid dates below the graph
    Examples:
      | from             | to             |
      | '01/15/2020'     | '01/14/2020'   |
      | '08/20/2020'     | '07/28/2020'   |
  
  Scenario Outline: Adding date "from" more than 1 year ago
    Given I am signed in
    And I enter <from> into 'fromGraph'
    Then I should see an error message stating that these are invalid dates below the graph
    Examples:
      | from             |
      | '01/15/2019'     |
      | '08/20/2019'     |
  
  Scenario Outline: Adding date "to" in the future
    Given I am signed in
    And I enter <to> into 'toGraph'
    Then I should see an error message stating that these are invalid dates below the graph
    Examples:
      | to               |
      | '01/15/2021'     |
      | '08/20/2021'     |
      
  Scenario: Using calendar picker to select dates in date from
      Given I am signed in
      And I select 1 week ago from the calendar picker for date from
      Then date from should equal 1 week ago
      
  Scenario: Using calendar picker to select dates in date to
      Given I am signed in
      And I select 1 week ago from the calendar picker for date to
      Then date to should equal 1 week ago
      
  Scenario: Portfolio value is red or green with an arrow signaling gains or losses
  	Given I am signed in
  	And I see my portfolio value
  	Then the portfolio value should be green or red with an up or down arrow by it
  	
  
  