Feature:

  Scenario: Add CSV file
    Given I am signed in
    And I click on upload csv modal
    And I choose '/Users/richardhernandez/Desktop/fall2020/softwareEng/310/csv.csv'
    And I click upload csv
    Then I should 'asa' on the profile