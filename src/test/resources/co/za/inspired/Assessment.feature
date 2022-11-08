Feature: Home page

  @FunctionalTests
  Scenario Outline: Sorting Shopping page
    Given A user navigates to HomePage
    And user log in<username> <password>
    Then user sort page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |


