package bestbuy.cucumber.StepDefinationFile;

import bestbuy.serviceinfo.ServiceSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class ServiceStepdefs {
    @Steps
    ServiceSteps serviceSteps;
    static String name = "sweety";
    static int serviceId;
    ValidatableResponse response;

    @When("^User send a post request for creating Serivice$")
    public void userSendAPostRequestForCreatingSerivice() {
    }

    @And("^User  add in name field$")
    public void userAddInNameField() {
        response= serviceSteps.creatService(name);
        serviceId=response.extract().path("id");

    }

    @Then("^User should be able to creat new searvice$")
    public void userShouldBeAbleToCreatNewSearvice() {
        response.statusCode(201);
    }

    @When("^User send a get request by created new  searvice Id$")
    public void userSendAGetRequestByCreatedNewSearviceId() {

        response= serviceSteps.getSingleService(serviceId);
    }

    @Then("^user should be able to get response of new created searvice$")
    public void userShouldBeAbleToGetResponseOfNewCreatedSearvice() {
        response.statusCode(200);
    }

    @And("^verify name$")
    public void verifyName() {
        response.body("name",equalTo(name));
    }

    @When("^User Update created searvice$")
    public void userUpdateCreatedSearvice() {


    }

    @And("^user update name$")
    public void userUpdateName() {
        name= name+"updated001";
        response=serviceSteps.updateService(serviceId,name);
    }

    @Then("^User  should get success response$")
    public void userShouldGetSuccessResponse() {
        response.statusCode(200);
    }

    @When("^User send delet request for deleting searvice$")
    public void userSendDeletRequestForDeletingSearvice() {
        response=serviceSteps.deleteServiceByid(serviceId);
    }

    @And("^User should get success deleted response$")
    public void userShouldGetSuccessDeletedResponse() {
        response.statusCode(200);
    }

    @And("^Verify searvice is deleted$")
    public void verifySearviceIsDeleted() {
        response=serviceSteps.getSingleService(serviceId);
        response.statusCode(404);
    }
}
