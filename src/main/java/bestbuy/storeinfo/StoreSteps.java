package bestbuy.storeinfo;

import bestbuy.constants.stores.EndPoints3;
import bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoreSteps {
    @Step
    public ValidatableResponse creatStore(String name, String type, String address, String address2, String city, String state, String zip,
                                          int lat, int lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(EndPoints3.CREAT_STORE)
                .then();

    }

    @Step
    public ValidatableResponse getStoreById(int storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storeID", storeId)
                .when()
                .get(EndPoints3.GET_SINGLE_STORE_BY_ID)
                .then();

    }

    @Step
    public ValidatableResponse updateStoreDetail(int storeId, String name, String type, String address, String address2, String city, String state, String zip,
                                                 int lat, int lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storeID", storeId)
                .body(storePojo)
                .when()
                .patch(EndPoints3.UPDATE_STORE_BY_ID)
                .then();

    }
    @Step
    public ValidatableResponse deletStoreById(int storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storeID", storeId)
                .when()
                .delete(EndPoints3.DELETE_STORE_BY_ID)
                .then();

    }

}