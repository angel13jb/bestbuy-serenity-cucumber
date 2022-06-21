package bestbuy.productinfo;

import bestbuy.constants.product.Endpoints1;
import bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    @Step("creating product by name: {0}, type : {1}, price : {2} , shipping : {3} , upc :{4} ,discription : {5},manufacturer :{6} ,model : {7}, url : {8} , image : {9}")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping,
                                             String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setUpc(upc);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(Endpoints1.CREAT_PRODUCT)
                .then();


    }

    @Step("getting product by id : {0}")
    public ValidatableResponse getProductListById(int productId) {
        return SerenityRest.given().log().ifValidationFails()
                .pathParam("productID", productId)
                .when()
                .get(Endpoints1.GET_SINGLE_PRODUCT_BY_ID)
                .then();

    }

    @Step("updating  by  product id : {0}, name: {1}, type : {2}, price : {3} , shipping : {4} , upc :{5} ,discription : {6},manufacturer :{7} ,model : {8}, url : {8} , image : {9}")
    public ValidatableResponse updateProduct(int productId, String name, String type, int price, int shipping,
                                             String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setUpc(upc);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("productID", productId)
                .body(productPojo)
                .when()
                .patch(Endpoints1.UPDATE_PRODUCT_BY_ID)
                .then();


    }

    @Step("deleting peoduct by id : {0}")
    public ValidatableResponse deleteProductById(int productId) {
        return SerenityRest.given().log().ifValidationFails()
                .pathParam("productID", productId)
                .when()
                .delete(Endpoints1.DELETE_PRODUCT_BY_ID)
                .then();

    }@Step
    public ValidatableResponse getProductById(int productId) {
        return SerenityRest.given().log().ifValidationFails()
                .pathParam("productID", productId)
                .when()
                .delete(Endpoints1.GET_SINGLE_PRODUCT_BY_ID)
                .then();

    }
}