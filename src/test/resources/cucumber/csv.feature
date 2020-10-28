Feature:

  Scenario: Add CSV file
    Given I am signed in
    And I click on upload csv modal
    And I choose 'csv.csv'
    And I click upload csv
    Then I should 'asa' on the profile