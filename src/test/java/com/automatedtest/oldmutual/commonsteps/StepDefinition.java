package com.automatedtest.oldmutual.commonsteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinition {


    private CommonSteps CommonSteps;

    public StepDefinition() {
        this.CommonSteps = new CommonSteps();
    }

    @Given("^A user navigates to HomePage$")
    public void aUserNavigatesToHomePage() {
        this.CommonSteps.goToHomePage();
    }

    @And("^user verifies title is displayed$")
    public void googleLogoIsDisplayed(final String alert) throws Throwable {
        this.CommonSteps.acceptCookie(alert);
        this.CommonSteps.checkTitleDisplayed();
    }

    @And("user navigates PERSONAL LOANS page")
    public void userNavigatesPERSONALLOANS() throws Throwable{
        this.CommonSteps.goToPersonalLoans();
    }

    @And("user clicks calculate button")
    public void userClicksCalculateButton() throws Throwable{
        this.CommonSteps.calculateLinkonPersonalLoan();
    }

    @And("^user select amount(.*)$")
    public void userSelectAmountAmount(final String amount) throws Throwable {
        this.CommonSteps.selectAmount(amount);
    }

    @And("^user select duration(.*)$")
    public void userSelectMonthsMonths(final String months) throws Throwable{
        this.CommonSteps.selectDuration(months);
    }

    @Then("user calculates and verifies amounts")
    public void userCalculatesAndVerifiesAmounts() throws Throwable{
        this.CommonSteps.calculateAndValidate();
    }

}
