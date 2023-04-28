package com.homecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Customer;
import com.homecommerce.models.Order;
import com.homecommerce.models.OrderDetails;
import com.homecommerce.repos.OrderDetailsRepository;

class OrderDetailServiceTest {
    /**

     This is a test class for the OrderDetailService class which handles CRUD operations on the OrderDetails model.
     The tests in this class ensure that the OrderDetailService class is working as intended.
     It uses the Mockito framework to mock the OrderDetailsRepository dependency, and test the behavior of the OrderDetailService methods.
     The first test method, testSaveOrderDetails(), verifies that the saveOrderDetails() method in the OrderDetailService class saves an order details
     using the OrderDetailsRepository, and returns nothing.
     The second test method, testFindById(), verifies that the findById() method in the OrderDetailService class retrieves an order details by id
     using the OrderDetailsRepository, and returns the corresponding OrderDetails object.
     The third test method, testFindByOrder(), verifies that the findByOrder() method in the OrderDetailService class retrieves all order details
     related to an order using the OrderDetailsRepository, and returns a list of OrderDetails.
     */

    @Mock
    private OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    private OrderDetailService orderDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveOrderDetails() {
        OrderDetails orderDetails = new OrderDetails();
        //orderDetails.setProduct("Product 1");
        orderDetails.setQty(1);
        Order order = new Order();
        order.setOrderid(1);
        order.setOrderDate(LocalDate.now());
        order.setCustomer(new Customer());
        order.setPaymethod("By Card");
        order.setStatus("Pending");
        orderDetails.setOrder(order);

        when(orderDetailsRepository.save(any(OrderDetails.class))).thenReturn(orderDetails);

        orderDetailService.saveOrderDetails(orderDetails);

        verify(orderDetailsRepository, times(1)).save(any(OrderDetails.class));
    }

    @Test
    void testFindById() {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(1);
        //orderDetails.setProduct("Product 1");
        orderDetails.setQty(1);
        Order order = new Order();
        order.setOrderid(1);
        order.setOrderDate(LocalDate.now());
        order.setCustomer(new Customer());
        order.setPaymethod("By Card");
        order.setStatus("Pending");
        orderDetails.setOrder(order);

        when(orderDetailsRepository.findById(anyInt())).thenReturn(Optional.of(orderDetails));

        OrderDetails actualOrderDetails = orderDetailService.findById(1);

        assertEquals(orderDetails, actualOrderDetails);

        verify(orderDetailsRepository, times(1)).findById(anyInt());
    }

    @Test
    void testFindByOrder() {
        OrderDetails orderDetails1 = new OrderDetails();
        orderDetails1.setId(1);
        //orderDetails1.setProduct("Product 1");
        orderDetails1.setQty(1);
        Order order = new Order();
        order.setOrderid(1);
        order.setOrderDate(LocalDate.now());
        order.setCustomer(new Customer());
        order.setPaymethod("By Card");
        order.setStatus("Pending");
        orderDetails1.setOrder(order);

        OrderDetails orderDetails2 = new OrderDetails();
        orderDetails2.setId(2);
        //orderDetails2.setProduct("Product 2");
        orderDetails2.setQty(2);
        orderDetails2.setOrder(order);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(orderDetails1);
        orderDetailsList.add(orderDetails2);

        when(orderDetailsRepository.findByOrder(any(Order.class))).thenReturn(orderDetailsList);

        List<OrderDetails> actualOrderDetailsList = orderDetailService.findByOrder(order);

        assertEquals(orderDetailsList, actualOrderDetailsList);

        verify(orderDetailsRepository, times(1)).findByOrder(any(Order.class));
    }
}
