package bestbuy.productinfo;

import bestbuy.testbase.TestBase;
import bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.Matchers.equalTo;


@RunWith(SerenityRunner.class)
public class ProductCURDTestSteps extends TestBase {
    static String name = "Duracell - AA Batteries (2-Pack)" + TestUtils.getRandomValue();
    static String type = "Battery" + TestUtils.getRandomValue();
    static Double price = 12.99;
    static Integer shipping = 10;
    static String upc = "012333424000";
    static String description = "Long-lasting energy Battery";
    static String manufacturer = "Energizer";
    static String model = TestUtils.getRandomValue();
    static String url = "https://www.bestbuy.com/site/Energizer";
    static String image = "http://img.bystatic.com/BestBuy_US/images/products/4853/48000_sa.jpg";
    static int productId;

    @Steps
    ProductSteps productsSteps;

    @Title("This will create a new Product")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId=response.extract().path("id");
        System.out.println(productId);
    }

    @Title("Verify that Product was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response  = productsSteps.getProductInfoById(productId);
        response.body("name",equalTo(name));
    }



    @Title("Update the Product  and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
      ValidatableResponse response= productsSteps.updateProduct(productId, name, type, price);
        response.log().all().statusCode(200);

        ValidatableResponse response1  = productsSteps.getProductInfoById(productId);
        response1.body("name",equalTo(name));
    }

    @Title("Delete the Product and verify if the Product is deleted!")
    @Test
    public void test004() {
        productsSteps.deleteProduct(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);
    }

}
