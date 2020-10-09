Feature: add stock to portfolio
Scenario: Add stock from home page
	Given I am signed in
	And I enter 'TSLA' into ticker
	And I enter '10' into quantity
	And I enter 'Tesla' into company
	And I click the add stock button
	Then I should see 10 TSLA stock on the portfolio