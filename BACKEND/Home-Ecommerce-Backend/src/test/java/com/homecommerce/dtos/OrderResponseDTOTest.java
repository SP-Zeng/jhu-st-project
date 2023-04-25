package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.homecommerce.models.Order;

public class OrderResponseDTOTest {

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
