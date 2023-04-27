package blackbox_testing.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.homecommerce.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Payment;
import com.homecommerce.repos.PaymentRepository;

public class PaymentServiceTest {

  @Mock
  PaymentRepository dao;

  @InjectMocks
  PaymentService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test savePayment method with a valid Payment object
  @Test
  public void testSavePayment_ValidPayment() {
    Payment payment = new Payment();
    when(dao.save(payment)).thenReturn(payment);

    Payment result = service.savePayment(payment);

    assertEquals(payment, result);
  }

  // Test savePayment method with a null Payment object
  @Test
  public void testSavePayment_NullPayment() {
    Payment result = service.savePayment(null);

    assertNull(result);
  }

  // Test findPaymentById method with a valid payment id
  @Test
  public void testFindPaymentById_ValidId() {
    int id = 1;
    Payment payment = new Payment();
    when(dao.findById(id)).thenReturn(Optional.of(payment));

    Payment result = service.findPaymentById(id);

    assertEquals(payment, result);
  }

  // Test findPaymentById method with an invalid payment id
  @Test
  public void testFindPaymentById_InvalidId() {
    int id = 1;
    when(dao.findById(id)).thenReturn(Optional.empty());

    Payment result = service.findPaymentById(id);

    assertNull(result);
  }
}
