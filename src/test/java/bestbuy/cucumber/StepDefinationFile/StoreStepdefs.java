package bestbuy.cucumber.StepDefinationFile;

import bestbuy.storeinfo.StoreSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class StoreStepdefs {
    static String name = "sweety Store";
    static String type = "string";
    static String address = "string";
    static String address2 = "string";
    static String city = "string";
    static String state = "String";
    static String zip = "string";
    static int lat = 0;
    static int lng = 0;
    static String hours = "string";
    static int storeId;
    ValidatableResponse response;
    @Steps
    StoreSteps storeSteps;

    @When("^user send a post request for creating store data$")
    public void userSendAPostRequestForCreatingStoreData() {
    }

    @And("^User insert name, type, address, address2, city, state,zip, lat, lng, hours$")
    public void userInsertNameTypeAddressAddressCityStateZipLatLngHours() {
        response = storeSteps.creatStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @Then("^User should be able to creat store successfully$")
    public void userShouldBeAbleToCreatStoreSuccessfully() {
        response.statusCode(201);
    }

    @And("^User should get the store Id$")
    public void userShouldGetTheStoreId() {
        storeId = response.extract().path("id");
        System.out.println(storeId);
    }

    @When("^user Send a request for single created store$")
    public void userSendARequestForSingleCreatedStore() {
        response = storeSteps.getStoreById(storeId);
    }

    @Then("^user should be able to get Store detail succesfully$")
    public void userShouldBeAbleToGetStoreDetailSuccesfully() {
        response.statusCode(200);
    }

    @And("^verify name to check store has been created$")
    public void verifyNameToCheckStoreHasBeenCreated() {
        response.body("name", equalTo(name));
    }

    @When("^user send a patch request for update store$")
    public void userSendAPatchRequestForUpdateStore() {
    }

    @And("^User update name$")
    public void userUpdateName() {
        name = name + "updated001";
        response = storeSteps.updateStoreDetail(storeId, name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @Then("^user should be able to add successfully$")
    public void userShouldBeAbleToAddSuccessfully() {
        response.statusCode(200);
    }

    @When("^User send delete request for deleting store$")
    public void userSendDeleteRequestForDeletingStore() {
        response = storeSteps.deletStoreById(storeId);
    }

    @And("^User should be able to delete store succefully$")
    public void userShouldBeAbleToDeleteStoreSuccefully() {
        response.statusCode(200);
    }

    @And("^verify store has been deleted$")
    public void verifyStoreHasBeenDeleted() {
        response = storeSteps.getStoreById(storeId);
        response.statusCode(404);
    }


}
