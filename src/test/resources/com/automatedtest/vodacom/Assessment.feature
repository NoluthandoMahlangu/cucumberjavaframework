Feature: Home page

  @FunctionaTests
  Scenario Outline: Oder a product
    Given A user navigates to HomePage
    And user under the Online only Deals clicks on “Buy now”
    And deals details page is validated for for the specified product
    And At the bottom of the Deals details screen, add a validation point for the Deal price, Contract duration and Available online fields
    And click on the Get this deal button
    And validate that the Order summary screen has loaded
    And validate the device, plan, contract cover fields

    Examples:
      | product       | duration |
      | 2x Galaxy A32 | 36     |

    @NegativeTest
    Scenario: Fail this scenario on the “Deals details” screen and report an error on an incorrect “Deal price”
    Given A user navigates to HomePage
    And user under the Online only Deals clicks on “Buy now”
    And deals details page is validated for for the specified product
    And At the bottom of the Deals details screen, add a validation point for the Deal price
    And click on the Get this deal button
    And validate that the Order summary screen has loaded
    And validate the device, plan, contract cover fields

