Feature: Add and remove Historical Stock

  Scenario Outline: adding stock to view
    Given I am signed in view
    And I click on add view modal
    And I enter 'TSLA' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I enter '10/11/2020' into 'date-purchased-view'
    And I enter '10/21/2020' into 'date-sold-view'
    And I click on view stock
    Then I should see <shares> 'TSLA' stock on the view
    Examples:
      | quantity | shares |
      | '10'     | '10'   |  
      
  Scenario: check add view stock confirm
    Given I am signed in view
    And I click on add view modal
    Then I should see view stock button
      
  Scenario: zRemove stock from home page
    Given I am signed in view
    When I press the view remove stock button
    And I click confirm on the pop up modal
    Then I should not see that stock in the view

  Scenario Outline: Error message for stocks not on NYSE or NASDAQ (invalid ticker) view
    Given I am signed in view
    And I click on add view modal
    And I enter 'THLLY' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I click on view stock
    Then I should see an view error message stating that it is an invalid ticker
    Examples:
      | quantity |
      | '1'      |

  Scenario Outline: adding invalid number of stocks view
    Given I am signed in view
    And I click on add view modal
    And I enter 'AAPL' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I click on view stock
    Then I should see an view error message stating that it is an invalid quantity
    Examples:
      | quantity |
      | '0'      |
      | '-1'     |

  Scenario Outline: adding date sold before date purchased view
    Given I am signed in view
    And I click on add view modal
    And I enter 'AAPL' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I enter <purchased> into 'date-purchased-view'
    And I enter <sold> into 'date-sold-view'
    And I click on view stock
    Then I should see an view error message stating that these are invalid dates
    Examples:
      | quantity | purchased        | sold           |
      | '1'      | '01/15/2020'     | '01/14/2020'   |
      | '10'     | '08/20/2020'     | '07/28/2020'   |

  Scenario Outline: Adding date purchased more than 1 year ago view
    Given I am signed in view
    And I click on add view modal
    And I enter 'AAPL' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I enter <purchased> into 'date-purchased-view'
    And I click on view stock
    Then I should see an view error message stating that this date is invalid
    Examples:
      | quantity | purchased        |
      | '1'      | '01/15/2019'     |
      | '10'     | '08/20/2019'     |

  Scenario Outline: Adding date purchased more than 1 year ago view
    Given I am signed in view
    And I click on add view modal
    And I enter 'AAPL' into 'ticker-view'
    And I enter <quantity> into 'quantity-view'
    And I enter <purchased> into 'date-purchased-view'
    And I click on view stock
    Then I should see an view error message stating that this date is invalid
    Examples:
      | quantity | purchased        |
      | '1'      | '01/15/2021'     |
      | '10'     | '08/20/2021'     |
      
  Scenario Outline: Adding date sold but not date purchased view
   	 Given I am signed in view
    And I click on add view modal
   	 And I enter 'AAPL' into 'ticker-view'
   	 And I enter <quantity> into 'quantity-view'
   	 And I enter <sold> into 'date-sold-view'
   	 And I click on view stock
   	 Then I should see an error message stating to enter a purchase date
   	 Examples:
   		 | quantity | sold             |
      	 | '1'      | '01/15/2020'     |
      	 | '10'     | '08/20/2020'     |

  Scenario: Using calendar picker to select dates view
      Given I am signed in view
    And I click on add view modal
      And I enter 'AAPL' into 'ticker-view'
      And I enter '1' into 'quantity-view'
      And I select 1 week ago from the calendar picker for date purchased view
      Then view date purchased should equal 1 week ago