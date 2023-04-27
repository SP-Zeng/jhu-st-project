package blackbox_testing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.OrderDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Order;
import com.homecommerce.models.OrderDetails;
import com.homecommerce.repos.OrderDetailsRepository;

public class OrderDetailServiceTest {

  @Mock
  OrderDetailsRepository dao;

  @InjectMocks
  OrderDetailService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test saveOrderDetails method with a valid OrderDetails object
  @Test
  public void testSaveOrderDetails_ValidOrderDetails() {
    OrderDetails od = new OrderDetails();
    service.saveOrderDetails(od);

    verify(dao, times(1)).save(od);
  }

  // Test saveOrderDetails method with a null OrderDetails object
  @Test
  public void testSaveOrderDetails_NullOrderDetails() {
    service.saveOrderDetails(null);

  }

  // Test findById method with a valid order detail id that exists in the system
  @Test
  public void testFindById_ValidId() {
    int id = 1;
    OrderDetails od = new OrderDetails();
    when(dao.findById(id)).thenReturn(Optional.of(od));

    OrderDetails result = service.findById(id);

    assertEquals(od, result);
    verify(dao, times(1)).findById(id);
  }

  // Test findById method with a valid order detail id that does not exist in the system
  @Test
  public void testFindById_InvalidId() {
    int id = 1;
    when(dao.findById(id)).thenReturn(Optional.empty());

    OrderDetails result = service.findById(id);

    assertNull(result);
    verify(dao, times(1)).findById(id);
  }

  // Test findByOrder method with a valid Order object that has associated order details
  @Test
  public void testFindByOrder_ValidOrder() {
    Order order = new Order();
    List<OrderDetails> details = Arrays.asList(new OrderDetails());
    when(dao.findByOrder(order)).thenReturn(details);

    List<OrderDetails> result = service.findByOrder(order);

    assertEquals(details, result);
  }

  // Test findByOrder method with a valid Order object that does not have associated order details
  @Test
  public void testFindByOrder_OrderWithoutDetails() {
    Order order = new Order();
    when(dao.findByOrder(order)).thenReturn(Arrays.asList());

    List<OrderDetails> result = service.findByOrder(order);

    assertEquals(0, result.size());
  }
}


