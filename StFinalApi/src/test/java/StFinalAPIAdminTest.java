import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;


public class StFinalAPIAdminTest {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }


    /*
    Due to the authorization issue, it should return status code <405> because it is not allow to use get
     */
    @Test
    public void testProductAPIItem(){
        given()
                .baseUri(url)
                .when()
                .get("/api/admin")
                .then()
                .statusCode(405);
    }



}
