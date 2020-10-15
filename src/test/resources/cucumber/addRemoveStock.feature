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
      
   Scenario: Add a stock with an invalid ticker (not on NYSE or NASDAQ)
   	Given I am signed in
   	And I click on add stock modal
   	And I enter 'RLFTF' into 'ticker'
   	And I click on add stock
   	Then I should see an error message that lets me know it's an invalid ticker
   	
   Scenario: View a stock with an invalid ticker (not on NYSE or NASDAQ)
   	Given I am signed in
   	And I click on 'View Stock'
   	And I enter 'RLFTF' into 'ticker'
   	And I click 'View Stock'
   	Then I should see an error message that lets me know it's an invalid ticker
   	
   	Scenario: Add a stock with an invalid quantity
   	 Given I am signed in
   	 And I click on add stock modal
   	 And I enter -1 into 'quantity'
   	 And I click on add stock
   	 Then I should see an error message that lets me know I've entered an invalid quantity
   	
   	Scenario: Add stock with sold date before purchase date
   	 Given I am signed in
   	 And I click on add stock modal
   	 And I enter 1/1/2020 into 'Date Purchased'
   	 And I enter 12/31/2019 into 'Date Sold'
   	 And I click on add stock
   	 Then I should see an error message that lets me know I've entered a date sold before date purchased
   	 


