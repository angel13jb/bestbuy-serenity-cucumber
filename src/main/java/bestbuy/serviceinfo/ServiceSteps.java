package bestbuy.serviceinfo;

import bestbuy.constants.EndPoints;
import bestbuy.model.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServiceSteps {
    @Step("Creating Service with name : {0}")
    public ValidatableResponse createService(String name) {
        ServicePojo servicePojo = new ServicePojo();
        servicePojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicePojo)
                .when()
                .post("/services")
                .then();
    }

    @Step("Getting single Service information with name: {0}")
    public HashMap<String, Object> getServiceInfoByName(String name) {
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

    @Step("Updating Service information with name : {0}")
    public ValidatableResponse updateService(String name, int serviceId) {
        ServicePojo servicePojo = new ServicePojo();
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("serviceID", serviceId)
                .body(servicePojo)
                .when()
                .put(EndPoints.UPDATE_SERVICE_BY_ID)
                .then();
    }

    @Step("Deleting Service information with ServiceID: {0}")
    public ValidatableResponse deleteService(int serviceId) {
        return SerenityRest
                .given()
                .pathParam("serviceID", serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then();
    }

    @Step("Getting Service information with ServiceID: {0}")
    public ValidatableResponse getServiceById(int serviceId) {
        return SerenityRest
                .given()
                .pathParam("serviceID", serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then();
    }

}
