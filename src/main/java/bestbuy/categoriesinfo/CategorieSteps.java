package bestbuy.categoriesinfo;

import bestbuy.constants.EndPoints;
import bestbuy.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategorieSteps {
    @Step("Creating Category with name : {0}, id: {1}")
    public ValidatableResponse createCategory(String name, String id) {
        CategoriesPojo categoriesPojo =new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoriesPojo)
                .when()
                .post("/categories")
                .then();
    }

    @Step("Getting single Category information with name: {0}")
    public ValidatableResponse getCategoryInfoByName(String name) {

        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path("name");
    }

    @Step("Updating Category information with name : {0}, CategoryID: {1}")
    public ValidatableResponse updateCategory(String name, String categoryId) {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(categoryId);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("categoryID", categoryId)
                .body(categoriesPojo)
                .when()
                .put(EndPoints.UPDATE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Deleting Category information with CategoryID: {0}")
    public ValidatableResponse deleteCategory(String categoryId) {
        return SerenityRest
                .given()
                .pathParam("categoryID", categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then();
    }

    @Step("Getting Category information with CategoryID: {0}")
    public ValidatableResponse getCategoryById(String categoryId) {
        return SerenityRest
                .given()
                .pathParam("categoryID", categoryId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then();
    }
}
