package com.homecommerce.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDetailsTest {

    private OrderDetails orderDetails;
    private Product product;
    private Order order;

    @BeforeEach
    public void setup() {
        orderDetails = new OrderDetails();
        product = new Product();
        order = new Order();
    }

    @Test
    public void testGettersAndSetters() {
        orderDetails.setId(1);
        assertEquals(1, orderDetails.getId());
        orderDetails.setProduct(product);
        assertEquals(product, orderDetails.getProduct());
        orderDetails.setQty(2);
        assertEquals(2, orderDetails.getQty());
        orderDetails.setOrder(order);
        assertEquals(order, orderDetails.getOrder());
    }

    @Test
    public void testToString() {
        orderDetails.setId(1);
        orderDetails.setProduct(product);
        orderDetails.setQty(2);
        orderDetails.setOrder(order);
        assertNotNull(orderDetails.toString());
    }
}
