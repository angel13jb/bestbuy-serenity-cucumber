package bestbuy.storeinfo;

import bestbuy.constants.EndPoints;
import bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating store with name: {0}, type: {1}, address: {2}, city: {3}, state: {4}, zip: {5}, lat: {6}, lng: {7}, hours: {8}")
    public ValidatableResponse createStore (String name, String type, String address,  String city,
                                            String state, String zip, double lat, double lng, String hours){
        StorePojo storePojo =new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        return SerenityRest.given().log().all()
                .contentType((ContentType.JSON))
                .body(storePojo)
                .when()
                .post("/stores")
                .then();
    }

    @Step ("Fetching the store information with name: {0}")
    public HashMap<String, Object> getStoreInfoByname(String name){
        String p1 = "data.findAll{it.name='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step ("Updating store with name: {0}, type: {1}, address: {2}, city: {3}")

    public ValidatableResponse updateStore(int storeID, String name, String type, String address, String city){
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setCity(city);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeID)
                .body(storePojo)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }

    @Step("Deleting store information with storeID: {0}")
    public ValidatableResponse deleteStore(int storeID){
        return SerenityRest
                .given()
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Fetching store information with storeID: {0}")
    public ValidatableResponse getStoreById(int storeID){
        return SerenityRest
                .given()
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }

}
