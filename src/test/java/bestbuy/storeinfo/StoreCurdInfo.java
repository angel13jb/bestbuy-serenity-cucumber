package bestbuy.storeinfo;

import bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class StoreCurdInfo extends TestBase {
    static String name = "sweety Store";
    static String type = "string";
    static String address = "string";
    static String address2 = "string";
    static String city = "string";
    static String state = "String";
    static String zip = "string";
    static int lat = 0;
    static int lng = 0;
    static String hours = "string";
    static int storeId;

    @Steps
    StoreSteps storeSteps;

    @Title("creat a store detail")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.creatStore(name, type, address, address2, city, state,
                zip, lat, lng, hours);

        response.log().all().statusCode(201);
        storeId = response.extract().path("id");
        System.out.println(storeId);
    }

    @Title("Get single store detail")
    @Test
    public void test002() {
        ValidatableResponse response= storeSteps.getStoreById(storeId);
        response.body("name",equalTo(name));
    }
    @Title("this will update store detail")
    @Test
    public void test003(){
        ValidatableResponse response= storeSteps.updateStoreDetail(storeId,name, type, address, address2, city, state,
                zip, lat, lng, hours);
        response.log().all().statusCode(200);


    }
    @Title("this will delet storedetail")
    @Test
    public void test004(){
        ValidatableResponse response= storeSteps.deletStoreById(storeId);
        response.log().all().statusCode(200);

        ValidatableResponse response1= storeSteps.getStoreById(storeId);
        response1.log().all().statusCode(404);
    }
}
