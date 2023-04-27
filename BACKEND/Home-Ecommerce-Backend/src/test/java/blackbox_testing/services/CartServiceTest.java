package blackbox_testing.services;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.CartService;
import com.homecommerce.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Cart;
import com.homecommerce.models.Customer;
import com.homecommerce.models.Product;
import com.homecommerce.repos.CartRepository;

public class CartServiceTest {

  @Mock
  CartRepository repo;

  @Mock
  CustomerService cservice;

  @InjectMocks
  CartService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test save method with a valid Cart object
  @Test
  public void testSave_ValidCart() {
    Cart wl = new Cart();
    service.save(wl);

    verify(repo, times(1)).save(wl);
  }

  // Test save method with a null Cart object
  @Test
  public void testSave_NullCart() {
    service.save(null);

  }

  // Test findByuserid method with a valid user id that exists in the system
  @Test
  public void testFindByuserid_ValidUserId() {
    int id = 1;
    Customer customer = new Customer();
    when(cservice.findById(id)).thenReturn(customer);
    List<Cart> carts = Arrays.asList(new Cart());
    when(repo.findByCustomer(customer)).thenReturn(carts);

    List<Cart> result = service.findByuserid(id);
    assertEquals(carts, result);

    verify(repo, times(1)).findByCustomer(customer);
  }

  // Test updateQty method with a valid cart id that exists in the system and a valid quantity
  @Test
  public void testUpdateQty_ValidCartIdValidQty() {
    int cartid = 1;
    int qty = 2;
    Cart c = new Cart();
    when(repo.findById(cartid)).thenReturn(Optional.of(c));

    service.updateQty(cartid, qty);

    assertEquals(qty, c.getQty());
    verify(repo, times(1)).save(c);
  }

  // Test deleteItem method with a valid cart id that exists in the system
  @Test
  public void testDeleteItem_ValidCartId() {
    int id = 1;

    service.deleteItem(id);

    verify(repo, times(1)).deleteById(id);
  }

  // Test checkexist method with valid Customer and Product objects that exist in the system
  @Test
  public void testCheckexist_ValidCustomerProduct() {
    Customer customer = new Customer();
    Product product = new Product();
    when(repo.findByCustomerAndProduct(customer, product)).thenReturn(new Cart());

    boolean result = service.checkexist(customer, product);

    assertTrue(result);
    verify(repo, times(1)).findByCustomerAndProduct(customer, product);
  }

  // Test clearCart method with a valid Customer object that exists in the system
  @Test
  public void testClearCart_ValidCustomer() {
    Customer cust = new Customer();
    List<Cart> carts = Arrays.asList(new Cart());
    when(repo.findByCustomer(cust)).thenReturn(carts);

    service.clearCart(cust);

    verify(repo, times(1)).deleteAll(carts);
  }
}

