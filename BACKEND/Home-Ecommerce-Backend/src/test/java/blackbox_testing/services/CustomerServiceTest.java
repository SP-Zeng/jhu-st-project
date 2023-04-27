package blackbox_testing.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Customer;
import com.homecommerce.repos.CustomerRepository;

public class CustomerServiceTest {

  @Mock
  CustomerRepository dao;

  @InjectMocks
  CustomerService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test registerCustomer method
  @Test
  public void testRegisterCustomer_ValidCustomer() {
    Customer cust = new Customer();
    service.registerCustomer(cust);

    verify(dao, times(1)).save(cust);
  }

  // Test allCustomers method
  @Test
  public void testAllCustomers() {
    List<Customer> customers = Arrays.asList(new Customer());
    when(dao.findAll()).thenReturn(customers);

    List<Customer> result = service.allCustomers();

    assertEquals(customers, result);
  }

  // Test findById method
  @Test
  public void testFindById_ValidId() {
    int id = 1;
    Customer cust = new Customer();
    when(dao.findById(id)).thenReturn(Optional.of(cust));

    Customer result = service.findById(id);

    assertEquals(cust, result);
  }

  // Test validate method with valid user id and password
  @Test
  public void testValidate_ValidUserIdPassword() {
    String userid = "test";
    String pwd = "test";
    Customer cc = new Customer();
    cc.setPwd(pwd);
    when(dao.findByUserid(userid)).thenReturn(cc);

    Customer result = service.validate(userid, pwd);

    assertEquals(cc, result);
  }

  // Test verifyUserId method with a user id that exists in the system
  @Test
  public void testVerifyUserId_UserIdExists() {
    String userid = "test";
    when(dao.findByUserid(userid)).thenReturn(new Customer());

    boolean result = service.verifyUserId(userid);

    assertTrue(result);
  }

  // Test updateProfile method with a valid Customer object
  @Test
  public void testUpdateProfile_ValidCustomer() {
    Customer cust = new Customer();
    cust.setId(1);
    cust.setPwd("test");
    when(dao.findById(cust.getId())).thenReturn(Optional.of(cust));

    service.updateProfile(cust);

    verify(dao, times(1)).save(cust);
  }
}
