Feature: Bulk upload stock with CSV file

  Scenario: Add CSV file
    Given I am signed in
    And I click on upload csv modal
    And I choose 'csv.csv'
    And I click upload csv
    Then I should 'asa' on the profile

  Scenario: I import a malformed CSV file
    Given I am signed in
    And I click on the upload csv modal
    And I choose 'malformedcsv.csv'
    And I click upload csv
    Then I should see an upload error 'Invalid ticker in csv file'