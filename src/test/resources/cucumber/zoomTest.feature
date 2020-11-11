Feature: Zoom in and out using buttons underneath the graph

	Scenario: Zoom in using the + button
		Given I am signed in
		And I click the + button under the graph
		Then I should see the graph zoom in
	
	Scenario: Zoom out using the - button
		Given I am signed in
		And I click the - button under the graph
		Then I should see the graph zoom out