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
    And I enter 'THLLY' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I click on add stock
    Then I should see an error message stating that it is an invalid ticker
    Examples:
      | quantity |
      | '1'      |

  Scenario Outline: adding invalid number of stocks
    Given I am signed in
    And I click on add stock modal
    And I enter 'AAPL' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I click on add stock
    Then I should see an error message stating that it is an invalid quantity
    Examples:
      | quantity |
      | '0'      |
      | '-1'     |

  Scenario Outline: adding date sold before date purchased
    Given I am signed in
    And I click on add stock modal
    And I enter 'AAPL' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I enter <purchased> into 'date-purchased'
    And I enter <sold> into 'date-sold'
    And I click on add stock
    Then I should see an error message stating that these are invalid dates
    Examples:
      | quantity | purchased        | sold           |
      | '1'      | '01/15/2020'     | '01/14/2020'   |
      | '10'     | '08/20/2020'     | '07/28/2020'   |

  Scenario Outline: Adding date purchased more than 1 year ago
    Given I am signed in
    And I click on add stock modal
    And I enter 'AAPL' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I enter <purchased> into 'date-purchased'
    And I click on add stock
    Then I should see an error message stating that this date is invalid
    Examples:
      | quantity | purchased        |
      | '1'      | '01/15/2019'     |
      | '10'     | '08/20/2019'     |

  Scenario Outline: Adding date purchased more than 1 year ago
    Given I am signed in
    And I click on add stock modal
    And I enter 'AAPL' into 'ticker'
    And I enter <quantity> into 'quantity'
    And I enter <purchased> into 'date-purchased'
    And I click on add stock
    Then I should see an error message stating that this date is invalid
    Examples:
      | quantity | purchased        |
      | '1'      | '01/15/2021'     |
      | '10'     | '08/20/2021'     |
      
  Scenario Outline: Adding date sold but not date purchased
   	 Given I am signed in
   	 And I click on add stock modal
   	 And I enter 'AAPL' into 'ticker'
   	 And I enter <quantity> into 'quantity'
   	 And I enter <sold> into 'date-sold'
   	 And I click on add stock
   	 Then I should see an error message stating to enter a purchase date
   	 Examples:
   		 | quantity | sold             |
      	 | '1'      | '01/15/2020'     |
      	 | '10'     | '08/20/2020'     |
      	 
   Scenario Outline: Using calendar picker to select dates
      Given I am signed in
      And I click on add stock modal
      And I enter 'AAPL' into 'ticker'
      And I enter '1' into 'quantity'
      And I select 1 week ago from the calendar picker for date purchased
      Then date purchased should equal 1 week ago