import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class StFinalAPICustomerTest {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }

    /*
    There is old customer named "Ken" when we initialize the testing. Therefore, the size should larger than 0
     */
    @Test
    public void testCustomerAPIItem(){
        given()
                .baseUri(url)
                .when()
                .get("/api/customers")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    /*
    Test the post function for the Customer api. It is working.
     */
    @Test
    public void testCustomerAPIPostCat(){
        String jsonBody = "{\"id\":2,\"name\":\"Ben\",\"city\":\"Baltimore\",\"userid\":\"easyjiajob@gmail.com\",\"pwd\":\"Ecommerce\",\"phone\":\"2127477701\",\"gender\":\"Male\"}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .baseUri(url)
                .when()
                .post("/api/customers")
                .then()
                .assertThat()
                .statusCode(200);
    }

    /*
    Testing if we have the "Ben" customer when we get
     */
    @Test
    public void testCustomerAPIItemInfo(){
        given()
                .baseUri(url)
                .when()
                .get("/api/customers")
                .then()
                .statusCode(200)
                .body("name", hasItem("Ben"));
    }



    /*
    Test the put function for the Customer api. It is not working due to the authorization issue
     */
    @Test
    public void testCustomerAPIPutCat(){
        String jsonBody = "{\"id\":2,\"name\":\"Ben\",\"city\":\"Baltimore\",\"userid\":\"easyjiajob@gmail.com\",\"pwd\":\"Ecommerce\",\"phone\":\"2127477701\",\"gender\":\"Male\"}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .baseUri(url)
                .when()
                .put("/api/customers")
                .then()
                .assertThat()
                .statusCode(405);
    }

    /*
    Test the delete function for the Customer api. It is not working due to the authorization issue
     */
    @Test
    public void testCustomerAPIDeleteCat(){
        given()
                .contentType(ContentType.JSON)
                .baseUri(url)
                .when()
                .delete("/api/customers")
                .then()
                .statusCode(405);
    }

}
