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
public class ProductCurdTest extends TestBase {

    static String name = "sweety" + TestUtils.getRandomValue();
    static String type = "string";
    static int price = 0;
    static int shipping = 0;
    static String upc = "String";
    static String description = "string";
    static String manufacturer = "string";
    static String model = "String ";
    static String url = "String";
    static String image = "string";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("this will creat new product ")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc,
                description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.extract().path("id");
        System.out.println(productId);
    }

    @Title("This will fatch the product by id ")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProductListById(productId);
        response.body("name", equalTo(name));
    }

    @Title("this will update  product ")
    @Test
    public void test003() {
        name = name + "updated_01";
        ValidatableResponse response = productSteps.updateProduct(productId, name, type, price, shipping, upc,
                description, manufacturer, model, url, image);
        response.log().all().statusCode(200);

        ValidatableResponse response1 = productSteps.getProductListById(productId);
        response1.body("name", equalTo(name));
    }

    @Title("This will delete product ")
    @Test
    public void test004() {
        ValidatableResponse response = productSteps.deleteProductById(productId);
        response.statusCode(200);

        ValidatableResponse response1 = productSteps.getProductById(productId);
        response1.statusCode(404);
    }

}