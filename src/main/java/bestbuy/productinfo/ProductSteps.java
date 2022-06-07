package bestbuy.productinfo;
import bestbuy.constants.EndPoints;
import bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class ProductSteps {

    @Step("Creating Product with name : {0}, type: {1}, price: {2}, shipping: {3}, upc: {4},description: {5}, manufacturer: {6},model: {7},url: {8},image: {9}")
    public ValidatableResponse createProduct(String name, String type, Double price, Integer shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo=new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice( price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post("/products")
                .then();
    }

    @Step("Getting single Product information with name: {0}")
    public ValidatableResponse getProductInfoById(int productId) {

        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path("id");

    }


    @Step("Updating Product partially with ProductID: {0}, name : {1}, type: {2}, price: {3}")
    public ValidatableResponse updateProduct(int productId, String name, String type, Double price) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID", productId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting Product information with ProductID: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest
                .given()
                .pathParam("productID", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Getting Product information with ProductID: {0}")
    public ValidatableResponse getProductById(int productId) {
        return SerenityRest
                .given()
                .pathParam("productID", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }
}
