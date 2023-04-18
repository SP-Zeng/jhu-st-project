import io.restassured.http.ContentType;
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



}
