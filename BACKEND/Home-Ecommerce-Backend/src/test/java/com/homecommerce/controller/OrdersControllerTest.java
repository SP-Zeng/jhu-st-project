package com.homecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.homecommerce.dtos.PlaceOrderDTO;
import com.homecommerce.dtos.Response;
import com.homecommerce.models.Cart;
import com.homecommerce.models.Customer;
import com.homecommerce.models.Order;
import com.homecommerce.models.OrderDetails;
import com.homecommerce.services.CartService;
import com.homecommerce.services.CustomerService;
import com.homecommerce.services.OrderDetailService;
import com.homecommerce.services.OrderService;

public class OrdersControllerTest {

    @Mock
    private OrderService orderService;
    @Mock
    private CustomerService customerService;
    @Mock
    private OrderDetailService orderDetailsService;
    @Mock
    private CartService cartService;

    private OrdersController ordersController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ordersController = new OrdersController();
        ordersController.orderService = orderService;
        ordersController.customerService = customerService;
        ordersController.orderDetailsService = orderDetailsService;
        ordersController.cartservice = cartService;
    }

    @Test
    public void testSave() {
        // Arrange
        PlaceOrderDTO dto = new PlaceOrderDTO();
        dto.setCustomerid(1);
        Customer customer = new Customer(1);
        when(customerService.findById(dto.getCustomerid())).thenReturn(customer);
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());
        when(cartService.findByuserid(dto.getCustomerid())).thenReturn(carts);
        Order savedOrder = new Order();
        savedOrder.setOrderid(1);
        when(orderService.saveOrder(savedOrder)).thenReturn(savedOrder);

        // Act
        ResponseEntity<?> result = ordersController.save(dto);

        // Assert
        assertEquals(Response.success("Order placed"), result);
    }

    @Test
    public void testConfirmOrder() {
        // Arrange
        int orderId = 1;

        // Act
        ResponseEntity<?> result = ordersController.confirmOrder(orderId);

        // Assert
        assertEquals(Response.success("Confirmed"), result);
    }

    @Test
    public void testFindAllOrders() {
        // Arrange
        Optional<Integer> customerId = Optional.empty();
        List<Order> orders = new ArrayList<>();
        when(orderService.getAllOrders()).thenReturn(orders);

        // Act
        List<Order> result = ordersController.findAllOrders(customerId);

        // Assert
        assertEquals(orders, result);
    }

    @Test
    public void testFindAllOrdersByCustomerId() {
        // Arrange
        int customerId = 1;
        Optional<Integer> optionalCustomerId = Optional.of(customerId);
        Customer customer = new Customer(customerId);
        List<Order> orders = new ArrayList<>();
        when(customerService.findById(customerId)).thenReturn(customer);
        when(orderService.getCustomerOrders(customer)).thenReturn(orders);

        // Act
        List<Order> result = ordersController.findAllOrders(optionalCustomerId);

        // Assert
        assertEquals(orders, result);
    }

    @Test
    public void testFindOrderById() {
        // Arrange
        int orderId = 1;
        Order order = new Order();
        order.setOrderid(1);
        List<OrderDetails> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetails());
        when(orderService.findById(orderId)).thenReturn(order);
        when(orderDetailsService.findByOrder(order)).thenReturn(orderDetails);

        // Act
        ResponseEntity<?> response = ordersController.findOrderById(orderId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
