package com.homecommerce.services;

import com.homecommerce.models.Customer;
import com.homecommerce.models.Order;
import com.homecommerce.repos.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    /**
     This is a test class for the OrderService class which is responsible for handling business logic for the Order model.
     The tests in this class ensure that the OrderService class is working as intended.
     The first test method, testSaveOrder(), verifies that the saveOrder() method in the OrderService class saves an order
     using the OrderRepository, and returns the saved order.
     The second test method, testConfirm(), verifies that the confirm() method in the OrderService class changes the status
     of an order from "Created" to "Confirmed" and saves the updated order using the OrderRepository.
     The third test method, testGetAllOrders(), verifies that the getAllOrders() method in the OrderService class retrieves
     all orders using the OrderRepository and returns a list of orders.
     The fourth test method, testGetCustomerOrders(), verifies that the getCustomerOrders() method in the OrderService class
     retrieves all orders associated with a given customer using the OrderRepository and returns a list of orders.
     The fifth test method, testFindById(), verifies that the findById() method in the OrderService class retrieves an order
     by its id using the OrderRepository and returns the order.
     */

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveOrder() {
        Order order = new Order();
        order.setOrderid(1);
        order.setStatus("Created");

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        assertEquals(order, savedOrder);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testConfirm() {
        Order order = new Order();
        order.setOrderid(1);
        order.setStatus("Created");

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.confirm(1);

        assertEquals("Confirmed", order.getStatus());
        verify(orderRepository, times(1)).findById(anyInt());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderid(1);
        order1.setStatus("Created");
        orders.add(order1);

        Order order2 = new Order();
        order2.setOrderid(2);
        order2.setStatus("Confirmed");
        orders.add(order2);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> retrievedOrders = orderService.getAllOrders();

        assertEquals(orders, retrievedOrders);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerOrders() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Customer 1");

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderid(1);
        order1.setStatus("Created");
        order1.setCustomer(customer);
        orders.add(order1);

        Order order2 = new Order();
        order2.setOrderid(2);
        order2.setStatus("Confirmed");
        order2.setCustomer(customer);
        orders.add(order2);

        when(orderRepository.findByCustomer(any(Customer.class))).thenReturn(orders);

        List<Order> retrievedOrders = orderService.getCustomerOrders(customer);

        assertEquals(orders, retrievedOrders);
        verify(orderRepository, times(1)).findByCustomer(any(Customer.class));
    }

    @Test
    void testFindById() {
        Order order = new Order();
        order.setOrderid(1);
        order.setStatus("Created");

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        Order retrievedOrder = orderService.findById(1);

        assertEquals(order, retrievedOrder);
        verify(orderRepository, times(1)).findById(anyInt());
    }
}
