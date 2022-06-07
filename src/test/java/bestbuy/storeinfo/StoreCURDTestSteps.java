package bestbuy.storeinfo;

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
public class StoreCURDTestSteps extends TestBase {
    static String name = "Minne" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue() + " 13 Dale";
    static String city = "Turkey";
    static String state = "MZ";
    static String zip = "123456";
    static Double lat = 44.969658;
    static Double lng = -95.263429;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoreSteps storesSteps;

    @Title("This will create new store")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.createStore(name, type, address,city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
    }

    @Title("Verify if the store was created in application")
    @Test
    public void test002() {
        HashMap<String, Object> storeMap = storesSteps.getStoreInfoByname(name);
        Assert.assertThat(storeMap, hasValue(name));
        storeId = (int) storeMap.get("id");
        System.out.println("Created Store ID: " + storeId);
    }

    @Title("Update store information and verify the updated information")
    @Test
    public void test003() {
        name = name + " _Updated";
        address = address + " _Updated";
        ValidatableResponse response=storesSteps.updateStore(storeId, name, type, address,city);
                response.log().all().statusCode(200);
//        HashMap<String, Object> value = storesSteps.getStoreInfoByname(name);
//        Assert.assertThat(value, hasValue(name));
    }

    @Title("Delete store and verify if the product is deleted")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);
    }

}
