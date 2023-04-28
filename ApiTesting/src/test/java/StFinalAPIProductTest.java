import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;



public class StFinalAPIProductTest {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }

    /*
    Before creating any product, the product API list should be more than 0 because I pre add some item.
     */
    @Test
    public void testProductAPIItem(){
        given()
                .baseUri(url)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    /*
    Create a new post by sending a http POST request to /posts endpoint with this info
    Then Check if the post is successful.
     */
    @Test
    public void testProductAPIPost() throws IOException {
        given()
                .contentType("multipart/form-data")
                .multiPart("id", 2)
                .multiPart("pname", "Benz C400")
                .multiPart("descr", "Car")
                .multiPart("category", "1")
                .multiPart("price", "30000")
                .multiPart("pic", new File("src/test/server/server/uploads/7e466f8a692241309173c6e8d973c054.jpg"))
                .baseUri(url)
                .when()
                .post("/api/products")
                .then()
                .statusCode(200);
    }

    /*
    I add this item info from frontend:
    [{"id":1,
    "pname":"Benz C400",
    "descr":"Car",
    "category":{"12"},
    "price":30000,
    "photo":"7e466f8a692241309173c6e8d973c054.png"}]
    check if we can get the products correctly
     */
    @Test
    public void testProductAPIItemInfo(){
        given()
                .baseUri(url)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("id", hasItem(1))
                .body("pname", hasItem("Benz C400"))
                .body("descr", hasItem("Car"))
                .body("price", hasItem(30000));
    }





    /*
    Test the put function for the categories api. It is not working due to the authorization issue
     */
    @Test
    public void testCategoriesAPIPutCat(){
        given()
                .contentType("multipart/form-data")
                .multiPart("id", 2)
                .multiPart("pname", "Benz C400")
                .multiPart("descr", "Car")
                .multiPart("category", "1")
                .multiPart("price", "30000")
                .multiPart("pic", new File("src/test/server/server/uploads/7e466f8a692241309173c6e8d973c054.jpg"))
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
    public void testProductsAPIDeleteCat(){
        given()
                .contentType(ContentType.JSON)
                .baseUri(url)
                .when()
                .delete("/api/products")
                .then()
                .statusCode(405);
    }
}
