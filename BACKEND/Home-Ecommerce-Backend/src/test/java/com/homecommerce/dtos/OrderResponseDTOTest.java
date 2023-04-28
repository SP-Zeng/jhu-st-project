package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.homecommerce.models.Order;

public class OrderResponseDTOTest {
    /**
     This is a test class for the OrderResponseDTO class which is a DTO class used to wrap an Order and its OrderDetailsDTOs
     to send as a response. The tests in this class ensure that the getters and setters for the Order and OrderDetailsDTOs are
     working as intended, and that the constructor is initializing the instance variables correctly.
     The first test method, testGetSetOrder(), verifies that the setOrder() and getOrder() methods in the OrderResponseDTO class
     set and return the expected Order object.
     The second test method, testGetSetDetails(), verifies that the setDetails() and getDetails() methods in the OrderResponseDTO
     class set and return the expected List of OrderDetailsDTO objects.
     The third test method, testConstructor(), verifies that the constructor initializes an instance of the OrderResponseDTO class
     correctly.
     */

    private OrderResponseDTO orderResponseDTO;
    private Order order;
    private List<OrderDetailsDTO> details;

    @BeforeEach
    void setUp() {
        orderResponseDTO = new OrderResponseDTO();
        order = new Order();
        details = new ArrayList<>();
    }

    @Test
    void testGetSetOrder() {
        orderResponseDTO.setOrder(order);
        assertEquals(order, orderResponseDTO.getOrder());
    }

    @Test
    void testGetSetDetails() {
        orderResponseDTO.setDetails(details);
        assertEquals(details, orderResponseDTO.getDetails());
    }

    @Test
    void testConstructor() {
        orderResponseDTO = new OrderResponseDTO();
        assertNotNull(orderResponseDTO);
    }
}
