import io.restassured.http.ContentType;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

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
    I add this item info from frontend:
    [{"id":1,
    "pname":"Benz C300",
    "descr":"Car",
    "category":{"id":1,"catname":"vechicle"},
    "price":30000,
    "photo":"d7069b8d6b9142e0b0f28c70bc6a9163.png"}]
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
                .body("pname", hasItem("Benz C300"))
                .body("descr", hasItem("Car"))
                .body("price", hasItem(30000));
    }


    /*
    Create a new post by sending a http POST request to /posts endpoint with this info
    Then Check if the post is successful. However, if I send post request I am not able to do that using rest assured,
    which is a bug for the backend connection as well. The status return <500> instead of <201>
     */
    @Test
    public void testProductAPIPost() throws IOException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", 5);
        requestParams.put("pname", "Benz C400");
        requestParams.put("descr", "Car");

        JSONObject category = new JSONObject();
        category.put("id", 5);
        category.put("catname", "vechicle");
        requestParams.put("category", category);

        requestParams.put("price", 40000);
        byte[] imageBytes = Files.readAllBytes(Paths.get("src/test/server/server/uploads/7e466f8a692241309173c6e8d973c054.jpg"));
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        requestParams.put("photo", base64Image);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(requestParams);


         given()
                .contentType(ContentType.JSON)
                .body(jsonArray.toString())
                .baseUri(url)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201);
    }
}
