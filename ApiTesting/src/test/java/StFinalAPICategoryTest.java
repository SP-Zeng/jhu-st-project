import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class StFinalAPICategoryTest {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }

    /*
    There is old categories named "vehicle" when we initialize the testing. Therefore, the size should larger than 0
     */
    @Test
    public void testCategoriesAPIItem(){
        given()
                .baseUri(url)
                .when()
                .get("/api/categories")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }



    /*
    Test the post function for the categories api. It is working.
     */
    @Test
    public void testCategoriesAPIPostCat(){
        String json = "{\"id\":3,\"catname\":\"vehicle\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .baseUri(url)
                .when()
                .post("/api/categories")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testCategoriesAPIItemInfo(){
        given()
                .baseUri(url)
                .when()
                .get("/api/categories")
                .then()
                .statusCode(200)
                .body("catname", hasItem("vehicle"));
    }

    /*
    Test the put function for the categories api. It is not working due to the authorization issue
     */
    @Test
    public void testCategoriesAPIPutCat(){
        String json = "{\"id\":3,\"catname\":\"food\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .baseUri(url)
                .when()
                .put("/api/categories")
                .then()
                .assertThat()
                .statusCode(405);
    }

    /*
    Test the delete function for the categories api. It is not working due to the authorization issue
     */
    @Test
    public void testCategoriesAPIDeleteCat(){
        given()
                .contentType(ContentType.JSON)
                .baseUri(url)
                .when()
                .delete("/api/categories")
                .then()
                .statusCode(405);
    }

}
