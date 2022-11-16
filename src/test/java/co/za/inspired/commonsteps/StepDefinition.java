package co.za.inspired.commonsteps;

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

    @And("^user log in(.*) (.*)$")
    public void user_log_inn(final String name, final String password){

        this.CommonSteps.login(name,password);
    }

    @Then("^user sort page$")
    public void userPopulateTheFormOnceDisplayed() throws Throwable  {
        this.CommonSteps.sortAscedingOrder();
    }
}
