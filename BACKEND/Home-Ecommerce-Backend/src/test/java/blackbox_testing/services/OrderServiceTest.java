package blackbox_testing.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Customer;
import com.homecommerce.models.Order;
import com.homecommerce.repos.OrderRepository;

public class OrderServiceTest {

  @Mock
  OrderRepository dao;

  @InjectMocks
  OrderService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test saveOrder method with a valid Order object
  @Test
  public void testSaveOrder_ValidOrder() {
    Order order = new Order();
    when(dao.save(order)).thenReturn(order);

    Order result = service.saveOrder(order);

    assertEquals(order, result);
  }

  // Test saveOrder method with a null Order object
  @Test
  public void testSaveOrder_NullOrder() {
    Order result = service.saveOrder(null);

    assertNull(result);
  }

  // Test confirm method with a valid order id
  @Test
  public void testConfirm_ValidId() {
    int id = 1;
    Order order = new Order();
    order.setStatus("Not confirmed");
    when(dao.findById(id)).thenReturn(Optional.of(order));

    service.confirm(id);

    assertEquals("Confirmed", order.getStatus());
  }

  // Test confirm method with an invalid order id
  @Test
  public void testConfirm_InvalidId() {
    int id = 1;
    when(dao.findById(id)).thenReturn(Optional.empty());

    service.confirm(id);

    verify(dao, never()).save(any());
  }

  // Test getAllOrders method
  @Test
  public void testGetAllOrders() {
    List<Order> orders = Arrays.asList(new Order());
    when(dao.findAll()).thenReturn(orders);

    List<Order> result = service.getAllOrders();

    assertEquals(orders, result);
  }

  // Test getCustomerOrders method with a valid Customer object
  @Test
  public void testGetCustomerOrders_ValidCustomer() {
    Customer customer = new Customer();
    List<Order> orders = Arrays.asList(new Order());
    when(dao.findByCustomer(customer)).thenReturn(orders);

    List<Order> result = service.getCustomerOrders(customer);

    assertEquals(orders, result);
  }

  // Test findById method with a valid order id
  @Test
  public void testFindById_ValidId() {
    int id = 1;
    Order order = new Order();
    when(dao.findById(id)).thenReturn(Optional.of(order));

    Order result = service.findById(id);

    assertEquals(order, result);
  }

  // Test findById method with an invalid order id
  @Test
  public void testFindById_InvalidId() {
    int id = 1;
    when(dao.findById(id)).thenReturn(Optional.empty());

    Order result = service.findById(id);

    assertNull(result);
  }
}

