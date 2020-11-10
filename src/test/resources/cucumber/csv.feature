Feature: Bulk upload stock with CSV file

  Scenario: Add CSV file
    Given I am signed in
    And I click on upload csv modal
    And I choose 'csv.csv'
    And I click upload csv
    Then I should 'PLAY' on the profile

  Scenario: I import a malformed CSV file with wrong ticker
    Given I am signed in
    And I click on upload csv modal
    And I choose 'malformedCsvTicker.csv'
    And I click upload csv
    Then I should see an upload error 'Malformed csv: Invalid ticker name LOLK'

  Scenario: I import a malformed CSV file with a sold date before the buy date
    Given I am signed in
    And I click on upload csv modal
    And I choose 'malformedCsvDate.csv'
    And I click upload csv
    Then I should see an upload error 'Malformed csv: Sold date before buy date for PLAY'

  Scenario: I import a malformed CSV file with a negative quantity
    Given I am signed in
    And I click on upload csv modal
    And I choose 'malformedCsvNegativeQuantity.csv'
    And I click upload csv
    Then I should see an upload error 'Malformed csv: Negative quantity for PLAY'
