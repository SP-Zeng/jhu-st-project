import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocalApiTest {

    @BeforeClass
    public static void setup() {
        // set the base URI for the API
        baseURI = "http://localhost:4200";
    }

    @Test
    public void testApi() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}