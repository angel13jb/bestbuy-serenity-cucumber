package bestbuy.categoriesinfo;

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
public class CategoriesCURDTestSteps extends TestBase {
    static String name = "special gift" + TestUtils.getRandomValue();
    static String id = "cat"+TestUtils.getRandomValue();
    static String categoryId;

    @Steps
    CategorieSteps categoriesSteps;

    @Title("This will create new Category")
    @Test
    public void test001() {
        ValidatableResponse response = categoriesSteps.createCategory(name, id);
        response.log().all().statusCode(201);
        categoryId=response.extract().path("id");
        System.out.println(categoryId);
    }

    @Title("Verify if the Category was created in application")
    @Test
    public void test002() {
       ValidatableResponse response = categoriesSteps.getCategoryInfoByName(name);
        response.body("name",equalTo(name));
    }

    @Title("Update Category information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_Updated";
        categoriesSteps.updateCategory(name, categoryId).log().all().statusCode(200);
        ValidatableResponse response = categoriesSteps.getCategoryInfoByName(categoryId);
        response.body("name",equalTo(name));
    }

    @Title("Delete Category and verify if the Category is deleted")
    @Test
    public void test004() {
        categoriesSteps.deleteCategory(categoryId).statusCode(200);
        categoriesSteps.getCategoryById(categoryId).statusCode(404);
    }
}
