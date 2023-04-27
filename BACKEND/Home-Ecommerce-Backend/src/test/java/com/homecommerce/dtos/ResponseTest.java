package com.homecommerce.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResponseTest {

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
