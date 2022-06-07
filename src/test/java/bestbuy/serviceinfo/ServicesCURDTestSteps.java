package bestbuy.serviceinfo;

import bestbuy.testbase.TestBase;
import bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ServicesCURDTestSteps extends TestBase {
    static String name = "Nokia Store" + TestUtils.getRandomValue();
    static Integer serviceId;

    @Steps
    ServiceSteps servicesSteps;

    @Title("This will create new Service")
    @Test
    public void test001() {
        ValidatableResponse response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the Service was created in application")
    @Test
    public void test002() {
        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        serviceId = (int) value.get("id");
        System.out.println("Created Service Id: "+serviceId);
    }

    @Title("Update Service information and verify the updated information")
    @Test
    public void test003() {
        name = name + " _Updated";
        servicesSteps.updateService(name, serviceId).log().all().statusCode(200);
        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        Assert.assertThat(value, hasValue(name));
    }

    @Title("Delete Service and verify if the Service is deleted")
    @Test
    public void test004() {
        servicesSteps.deleteService(serviceId).statusCode(200);
        servicesSteps.getServiceById(serviceId).statusCode(404);
    }


}
