import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class StFinalAPIWishlistTest {

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:8080/";
    }


    /*
    There is a bug for wishlist api. It should return status code <500> when we post.
     */
    @Test
    public void testWishlistAPIItem(){
        given()
                .baseUri(url)
                .when()
                .get("/api/wishlist")
                .then()
                .statusCode(500);
    }



}
