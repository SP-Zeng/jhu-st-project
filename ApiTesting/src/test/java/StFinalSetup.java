import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;


public class StFinalSetup {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }



    /*
    Create a new post by sending a http POST request to /posts endpoint with this info
    Then Check if the post is successful.
     */
    public static void testProductAPIPost() {
        given()
                .contentType("multipart/form-data")
                .multiPart("id", 2)
                .multiPart("pname", "Benz C400")
                .multiPart("descr", "Car")
                .multiPart("category", "1")
                .multiPart("price", "30000")
                .multiPart("pic", new File("src/test/server/server/uploads/7e466f8a692241309173c6e8d973c054.jpg"))
                .baseUri("http://localhost:8080/")
                .when()
                .post("/api/products")
                .then()
                .statusCode(200);
    }

    public static void testCategoriesAPIPostCat(){
        String json = "{\"id\":3,\"catname\":\"vehicle\"}";

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .baseUri("http://localhost:8080/")
                .when()
                .post("/api/categories")
                .then()
                .assertThat()
                .statusCode(200);
    }

    /*
    Test the post function for the Customer api. It is working.
     */
    public static void testCustomerAPIPostCat(){
        String jsonBody = "{\"id\":1,\"name\":\"1\",\"city\":\"1\",\"userid\":\"1\",\"pwd\":\"1\",\"phone\":\"1\",\"gender\":\"Male\"}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .baseUri("http://localhost:8080/")
                .when()
                .post("/api/customers")
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    public void setup(){
        testCustomerAPIPostCat();
        testCategoriesAPIPostCat();
        testProductAPIPost();
    }






}
