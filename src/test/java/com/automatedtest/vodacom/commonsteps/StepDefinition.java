package com.automatedtest.vodacom.commonsteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinition {


    private CommonSteps CommonSteps;

    public StepDefinition() {
        this.CommonSteps = new CommonSteps();
    }

    @Given("^A user navigates to HomePage$")
    public void user_navigates_To_HomePage() {
        this.CommonSteps.goToHomePage();
    }

    @And("user under the Online only Deals clicks on “Buy now”")
    public void the_Online_Only_Deals_clicks_OnBuyNow(final String alert) throws  Throwable {
        this.CommonSteps.acceptCookie(alert);
        this.CommonSteps.onlineOnlyDeals();
    }

    @And("deals details page is validated for for the specified product")
    public void dealsDetailsPageIsValidatedForForTheSpecifiedProduct() {
        this.CommonSteps.productValidation();

    }

    @And("At the bottom of the Deals details screen, add a validation point for the Deal price, Contract duration and Available online fields")
    public void deals_details_validations() {
        this.CommonSteps.dealDetailsValidations();

    }

    @And("click on the Get this deal button")
    public void clickOnTheGetThisDealButton() {
        this.CommonSteps.clickGetDeal();

    }

    @And("validate that the Order summary screen has loaded")
    public void validateThatTheOrderSummaryScreenHasLoaded() {
        this.CommonSteps.oderPageValidation();

    }

    @And("validate the device, plan, contract cover fields")
    public void validateTheDevicePlanContractCoverFields() {
        this.CommonSteps.oderSummaryValidation();

    }

    @And("At the bottom of the Deals details screen, add a validation point for the Deal price")
    public void assert_deal_price() {
        this.CommonSteps.dealDetailsAssertions();
    }
}
