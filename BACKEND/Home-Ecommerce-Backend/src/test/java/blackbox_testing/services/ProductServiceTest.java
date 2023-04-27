package blackbox_testing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.CategoryService;
import com.homecommerce.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.homecommerce.models.Category;
import com.homecommerce.models.Product;
import com.homecommerce.repos.ProductRepository;
import com.homecommerce.utils.StorageService;

public class ProductServiceTest {

  @Mock
  ProductRepository dao;

  @Mock
  CategoryService cdao;

  @Mock
  StorageService storageService;

  @InjectMocks
  ProductService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test addProduct method with a valid Product object and non-null MultipartFile
  @Test
  public void testAddProduct_ValidProduct_NonNullPic() {
    Product product = new Product();
    MultipartFile pic = mock(MultipartFile.class);
    String photo = "photo";
    when(storageService.store(pic)).thenReturn(photo);

    service.addProduct(product, pic);

    assertEquals(photo, product.getPhoto());
    verify(dao, times(1)).save(product);
  }

  // Test addProduct method with a valid Product object and null MultipartFile
  @Test
  public void testAddProduct_ValidProduct_NullPic() {
    Product product = new Product();

    service.addProduct(product, null);

    assertNull(product.getPhoto());
    verify(dao, times(1)).save(product);
  }

  // Test updateProduct method with a valid Product object
  @Test
  public void testUpdateProduct_ValidProduct() {
    Product product = new Product();

    service.updateProduct(product);

    verify(dao, times(1)).save(product);
  }

  // Test deleteProduct method with a valid product id
  @Test
  public void testDeleteProduct_ValidId() {
    int id = 1;

    service.deleteProduct(id);

    verify(dao, times(1)).deleteById(id);
  }

  // Test allProducts method
  @Test
  public void testAllProducts() {
    List<Product> products = Arrays.asList(new Product());
    when(dao.findAll()).thenReturn(products);

    List<Product> result = service.allProducts();

    assertEquals(products, result);
  }

  // Test searchProducts method with a valid search string
  @Test
  public void testSearchProducts_ValidSearch() {
    String search = "search";
    List<Product> products = Arrays.asList(new Product());
    when(dao.findByPnameOrDescrContaining(search, search)).thenReturn(products);

    List<Product> result = service.searchProducts(search);

    assertEquals(products, result);
  }

  // Test findProductById method with a valid product id
  @Test
  public void testFindProductById_ValidId() {
    int id = 1;
    Product product = new Product();
    when(dao.findById(id)).thenReturn(Optional.of(product));

    Product result = service.findProductById(id);

    assertEquals(product, result);
  }

  // Test findProductById method with an invalid product id
  @Test
  public void testFindProductById_InvalidId() {
    int id = 1;
    when(dao.findById(id)).thenReturn(Optional.empty());

    Product result = service.findProductById(id);

    assertNull(result);
  }

  // Test categoryProducts method with a valid category id
  @Test
  public void testCategoryProducts_ValidCategoryId() {
    int catid = 1;
    Category category = new Category();
    List<Product> products = Arrays.asList(new Product());

    when(cdao.findById(catid)).thenReturn(category);
    when(dao.findByCategory(category)).thenReturn(products);

    List<Product> result = service.categoryProducts(catid);

    assertEquals(products, result);
  }

  // Test categoryProducts method with an invalid category id
  @Test
  public void testCategoryProducts_InvalidCategoryId() {
    int catid = 1;

    when(cdao.findById(catid)).thenReturn(null);

    List<Product> result = service.categoryProducts(catid);

    assertNull(result);
  }
}



