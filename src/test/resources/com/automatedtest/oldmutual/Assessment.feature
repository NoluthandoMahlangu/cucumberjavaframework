Feature: Home page

  @PersonalLoanTests @FunctionaTests
  Scenario Outline: Check page display
    Given A user navigates to HomePage
    And user verifies title is displayed
    And user navigates PERSONAL LOANS page
    And user clicks calculate button
    And user select amount<amount>
    And user select duration<months>
    Then user calculates and verifies amounts

    Examples:
      | amount |months|
      | 50000 |60     |