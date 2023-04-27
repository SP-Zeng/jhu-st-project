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
        // Given
        Object data = new Object();

        // When
        ResponseEntity<?> response = Response.success(data);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("success", responseBody.get("status"));
        assertEquals(data, responseBody.get("data"));
    }

    @Test
    public void testSuccessWithNullData() {
        // Given
        Object data = null;

        // When
        ResponseEntity<?> response = Response.success(data);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("success", responseBody.get("status"));
        assertEquals(null, responseBody.get("data"));
    }

    @Test
    public void testErrorWithNonNullError() {
        // Given
        Object error = new Object();

        // When
        ResponseEntity<?> response = Response.error(error);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("error", responseBody.get("status"));
        assertEquals(error, responseBody.get("error"));
    }

    @Test
    public void testErrorWithNullError() {
        // Given
        Object error = null;

        // When
        ResponseEntity<?> response = Response.error(error);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap<String, Object> responseBody = (HashMap<String, Object>) response.getBody();
        assertNotNull(responseBody);
        assertEquals("error", responseBody.get("status"));
        assertEquals(null, responseBody.get("error"));
    }

    @Test
    public void testStatus() {
        // Given
        HttpStatus status = HttpStatus.NOT_FOUND;

        // When
        ResponseEntity<?> response = Response.status(status);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testConstructor() {
        // Given
        String status = "success";
        Object data = new Object();
        Object error = new Object();

        // When
        Response response = new Response();

        // Then
        assertNotNull(response);
    }
}
