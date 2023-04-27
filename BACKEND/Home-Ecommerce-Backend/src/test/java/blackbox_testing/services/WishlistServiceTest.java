package blackbox_testing.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.CustomerService;
import com.homecommerce.services.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.dtos.WishlistDTO;
import com.homecommerce.models.Customer;
import com.homecommerce.models.Product;
import com.homecommerce.models.Wishlist;
import com.homecommerce.repos.WishlistRepository;

public class WishlistServiceTest {

  @Mock
  WishlistRepository repo;

  @Mock
  CustomerService cservice;

  @InjectMocks
  WishlistService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSave_ValidWishlistDTO() {
    WishlistDTO dto = new WishlistDTO();
    dto.setCustid(1);
    dto.setProduct(new Product());
    Customer customer = new Customer();
    when(cservice.findById(1)).thenReturn(customer);

    service.save(dto);

    verify(repo, times(1)).save(any(Wishlist.class));
  }

  @Test
  public void testFindByUserId_ValidId() {
    int id = 1;
    List<Wishlist> wishlists = Arrays.asList(new Wishlist());
    when(cservice.findById(id)).thenReturn(new Customer());
    when(repo.findByCustomer(any(Customer.class))).thenReturn(wishlists);

    List<Wishlist> result = service.findByuserid(id);

    assertEquals(wishlists, result);
  }

  @Test
  public void testDeleteItem_ValidId() {
    int id = 1;

    service.deleteItem(id);

    verify(repo, times(1)).deleteById(id);
  }

  @Test
  public void testCheckExist_ExistingCustomerAndProduct() {
    int custid = 1;
    Product product = new Product();
    when(cservice.findById(custid)).thenReturn(new Customer());
    when(repo.findByCustomerAndProduct(any(Customer.class), eq(product))).thenReturn(new Wishlist());

    boolean result = service.checkexist(custid, product);

    assertEquals(true, result);
  }

  @Test
  public void testCheckExist_NonExistingCustomerAndProduct() {
    int custid = 1;
    Product product = new Product();
    when(cservice.findById(custid)).thenReturn(new Customer());
    when(repo.findByCustomerAndProduct(any(Customer.class), eq(product))).thenReturn(null);

    boolean result = service.checkexist(custid, product);

    assertEquals(false, result);
  }
}
