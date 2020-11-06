Feature: Remove Historical Stock

  Scenario: Remove stock from historical table
    Given I am signed in
    And I have one historical stock in table
    When I click remove
    Then I shouldn't see any historical stock