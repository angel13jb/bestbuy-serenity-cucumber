package bestbuy.cucumber.StepDefinationFile;


import bestbuy.productinfo.ProductSteps;
import bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class ProductStepdefs {
    @Steps
    ProductSteps productSteps;
    public ValidatableResponse response;
    static String name = "sweety" + TestUtils.getRandomValue();
    static String type = "string";
    static int price = 0;
    static int shipping = 0;
    static String upc = "String";
    static String description = "string";
    static String manufacturer = "string";
    static String model = "String ";
    static String url = "String";
    static String image = "string";
    static int productId = 0;

    @When("^User send a post request for creating data$")
    public void userSendAPostRequestForCreatingData() {
    }

    @And("^User add name ,type ,price ,shipping ,upc ,description ,manufacture ,model ,url ,image$")
    public void userAddNameTypePriceShippingUpcDescriptionManufactureModelUrlImage() {
        response = productSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
    }

    @Then("^User should get http responce code (\\d+)$")
    public void userShouldGetHttpResponceCode(int num) {
        response.statusCode(num);
    }

    @And("^User should get the id of the  created new product$")
    public void userShouldGetTheIdOfTheCreatedNewProduct() {
        productId = response.extract().path("id");
        System.out.println(productId);
    }

    @When("^User send a get request for the created id$")
    public void userSendAGetRequestForTheCreatedId() {
        response = productSteps.getProductById(productId);
    }

    @Then("^User should get response code (\\d+)$")
    public void userShouldGetResponseCode(int num) {
        response.statusCode(num);
    }

    @And("^Verify the name$")
    public void verifyTheName() {
        response.body("name", equalTo(name));
    }

    @When("^User send a put request for updating product detail$")
    public void userSendAPutRequestForUpdatingProductDetail() {
    }

    @And("^User update name  ,type ,price ,shipping ,upc ,description ,manufacture ,model ,url ,image$")
    public void userUpdateNameTypePriceShippingUpcDescriptionManufactureModelUrlImage() {
        name = name + "updated_01";
        response = productSteps.updateProduct(productId, name, type, price, shipping, upc,
                description, manufacturer, model, url, image);
    }

    @Then("^User should get http responce code (\\d+) for updating data$")
    public void userShouldGetHttpResponceCodeForUpdatingData(int num) {
        response.statusCode(num);
    }

    @When("^User send delet request for deleting id$")
    public void userSendDeletRequestForDeletingId() {
        response = productSteps.deleteProductById(productId);
    }

    @Then("^User should get response code (\\d+) for deleting data$")
    public void userShouldGetResponseCodeForDeletingData(int num) {
        response.statusCode(num);
    }
}
