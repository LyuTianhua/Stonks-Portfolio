Feature: Bulk upload stock with CSV file

  Scenario: Add CSV file
    Given I am signed in
    And I click on upload csv modal
    And I choose 'csv.csv'
    And I click upload csv
    Then I should 'ASA' on the profile