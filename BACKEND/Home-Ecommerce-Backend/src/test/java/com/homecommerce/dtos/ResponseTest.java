package com.homecommerce.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResponseTest {
    /**
     This is a test class for the Response class which is used to return HTTP responses for different scenarios in the application.
     The tests in this class ensure that the Response class is working as intended.
     The first test method, testSuccessWithNonNullData(), verifies that the Response.success() method returns a ResponseEntity object
     with HTTP status of OK, and a success message along with the provided data object.
     The second test method, testSuccessWithNullData(), verifies that the Response.success() method returns a ResponseEntity object
     with HTTP status of OK, and a success message with null data when the provided data object is null.
     The third test method, testErrorWithNonNullError(), verifies that the Response.error() method returns a ResponseEntity object
     with HTTP status of OK, and an error message along with the provided error object.
     The fourth test method, testErrorWithNullError(), verifies that the Response.error() method returns a ResponseEntity object
     with HTTP status of OK, and an error message with null error object when the provided error object is null.
     The fifth test method, testStatus(), verifies that the Response.status() method returns a ResponseEntity object
     with the provided HTTP status.
     The sixth test method, testConstructor(), verifies that a new instance of the Response class can be created successfully.
     */

    @Test
    public void testSuccessWithNonNullData() {
        Object data = new Object();
        ResponseEntity<?> response = Response.success(data);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("success", responseBody.get("status"));
        assertEquals(data, responseBody.get("data"));
    }

    @Test
    public void testSuccessWithNullData() {
        Object data = null;
        ResponseEntity<?> response = Response.success(data);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("success", responseBody.get("status"));
        assertEquals(null, responseBody.get("data"));
    }

    @Test
    public void testErrorWithNonNullError() {
        Object error = new Object();
        ResponseEntity<?> response = Response.error(error);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("error", responseBody.get("status"));
        assertEquals(error, responseBody.get("error"));
    }

    @Test
    public void testErrorWithNullError() {
        Object error = null;
        ResponseEntity<?> response = Response.error(error);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("error", responseBody.get("status"));
        assertEquals(null, responseBody.get("error"));
    }

    @Test
    public void testStatus() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ResponseEntity<?> response = Response.status(status);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testConstructor() {
        String status = "success";
        Object data = new Object();
        Object error = new Object();
        Response response = new Response();
        assertNotNull(response);
    }
}
