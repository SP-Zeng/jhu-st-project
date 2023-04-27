import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class StFinalAPIOrdersTest {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }

    /*
    There is old order by Ken when we initialize the testing. Therefore, the size should larger than 0
     */
    @Test
    public void testOrdersAPIItem(){
        given()
                .baseUri(url)
                .when()
                .get("/api/orders")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    /*
    check if the Ken's order is existed. The get function works fine.
     */
    @Test
    public void testOrdersAPIItemInfo(){
        String response = given().baseUri(url).get("/api/orders").asString();

    // Extract the customer name using JsonPath:
            JsonPath jsonPath = new JsonPath(response);
            String customerName = jsonPath.getString("customer.name");

    // Assert that the customer name is "Ken":
            assertTrue(customerName.contains("Ken"));
    }

    /*
    Test the post function for the Orders api. It is working.
     */
    @Test
    public void testOrdersAPIPostCat(){
        String jsonString = "{\"orderid\": 2, \"orderDate\": \"2023-04-17\", \"customer\": {\"id\": 2, \"name\": \"Ben\", \"city\": \"Baltimore\", \"userid\": \"easyjiajob@gmail.com\", \"pwd\": \"Ecommerce\", \"phone\": \"2127477701\", \"gender\": \"Male\"}, \"address\": null, \"payment\": null, \"paymethod\": \"By Card\", \"status\": \"Pending\", \"orderdetails\": []}";


        given()
                .contentType(ContentType.JSON)
                .body(jsonString)
                .baseUri(url)
                .when()
                .post("/api/orders")
                .then()
                .assertThat()
                .statusCode(200);
    }


    /*
    Test the put function for the Orders api. It is not working due to the authorization issue
     */
    @Test
    public void testOrdersAPIPutCat(){
        String jsonString = "{\"orderid\": 2, \"orderDate\": \"2023-04-17\", \"customer\": {\"id\": 2, \"name\": \"Johnny\", \"city\": \"Baltimore\", \"userid\": \"easyjiajob@gmail.com\", \"pwd\": \"Ecommerce\", \"phone\": \"2127477701\", \"gender\": \"Male\"}, \"address\": null, \"payment\": null, \"paymethod\": \"By Card\", \"status\": \"Pending\", \"orderdetails\": []}";

        given()
                .contentType(ContentType.JSON)
                .body(jsonString)
                .baseUri(url)
                .when()
                .put("/api/orders")
                .then()
                .assertThat()
                .statusCode(405);
    }

    /*
    Test the delete function for the Orders api. It is not working due to the authorization issue
     */
    @Test
    public void testOrdersAPIDeleteCat(){
        given()
                .contentType(ContentType.JSON)
                .baseUri(url)
                .when()
                .delete("/api/orders")
                .then()
                .statusCode(405);
    }

}
