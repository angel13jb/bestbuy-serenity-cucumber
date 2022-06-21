package bestbuy.cucumber.StepDefinationFile;

import bestbuy.categoriesinfo.CategoriesSteps;
import bestbuy.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;

public class CategoryStepdefs {
    static String name = "sweety" + TestUtils.getRandomValue();
    static String id = "001" + TestUtils.getRandomValue();
    static int categoriesId;
    ValidatableResponse response;
    @Steps
    CategoriesSteps categoriesSteps;
    @When("^User insert  name and id$")
    public void userInsertNameAndId() {

    }

    @And("^user send a Post request for creating category$")
    public void userSendAPostRequestForCreatingCategory() {
        response=categoriesSteps.creatCategory(name,id);
    }

    @Then("^user should be able to creat category successfully$")
    public void userShouldBeAbleToCreatCategorySuccessfully() {
        response.statusCode(201);
    }

    @When("^User send a get request by given id$")
    public void userSendAGetRequestByGivenId() {
        response = categoriesSteps.getSingleCategory(id);
        response.log().all().statusCode(200);
    }

    @Then("^User should be able to get category$")
    public void userShouldBeAbleToGetCategory() {
        response.log().all().statusCode(200);
    }

    @And("^verify name of category$")
    public void verifyNameOfCategory() {
        response.body("name",equalTo(name));
    }

    @When("^User update category by give id with patch request$")
    public void userUpdateCategoryByGiveIdWithPatchRequest() {
        name = name + "updated";
        response = categoriesSteps.updateCategory(name, id);

    }

    @Then("^User should be able to update succefully$")
    public void userShouldBeAbleToUpdateSuccefully() {
        response.log().all().statusCode(200);

    }

    @When("^user send delet request for deleting category$")
    public void userSendDeletRequestForDeletingCategory() {
        response=categoriesSteps.deletSingleCategory(id);
        response.log().all().statusCode(200);

    }

    @Then("^user should be able to delete category successfully$")
    public void userShouldBeAbleToDeleteCategorySuccessfully() {
        response.statusCode(200);
    }

    @And("^verify category is deleted$")
    public void verifyCategoryIsDeleted() {
        response=categoriesSteps.getSingleCategory(id);
        response.statusCode(404);
    }
}
