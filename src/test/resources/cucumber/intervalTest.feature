Feature: change interval using select underneath the graph

	Scenario: Choose day interval
		Given I am signed in
		And I choose the day select under the graph
		Then I should see the graph with day interval
	
	Scenario: Choose week interval
		Given I am signed in
		And I choose the week select under the graph
		Then I should see the graph with week interval

	Scenario: Choose month interval
		Given I am signed in
		And I choose the month select under the graph
		Then I should see the graph with month interval