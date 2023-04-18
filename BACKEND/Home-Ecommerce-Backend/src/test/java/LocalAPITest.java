import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LocalApiTest {

    @BeforeClass
    public static void setup() {
        // set the base URI for the API
        baseURI = "http://localhost:4200";
    }

    @Test
    public void testApi() {
        given()
                .param("name", "John")
                .header("Authorization", "Bearer token")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("data[0].name", equalTo("John"))
                .header("Content-Type", "application/json");
    }
}