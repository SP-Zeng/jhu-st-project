package com.homecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.homecommerce.models.Category;
import com.homecommerce.models.Product;
import com.homecommerce.repos.ProductRepository;
import com.homecommerce.utils.StorageService;

class ProductServiceTest {
    /**
     This is a test class for the ProductService class which handles the business logic for the Product model.
     The tests in this class ensure that the ProductService class is working as intended.
     It uses the Mockito framework to mock the dependencies, and test the behavior of the ProductService methods.
     The testAddProduct() method tests that the addProduct() method in the ProductService class saves a product
     using the ProductRepository and StorageService, and verifies that the correct methods in those classes are called.
     The testUpdateProduct() method tests that the updateProduct() method in the ProductService class updates a product
     using the ProductRepository, and verifies that the correct method in that class is called.
     The testDeleteProduct() method tests that the deleteProduct() method in the ProductService class deletes a product
     using the ProductRepository, and verifies that the correct method in that class is called.
     The testAllProducts() method tests that the allProducts() method in the ProductService class retrieves all products
     using the ProductRepository, and verifies that the correct method in that class is called.
     The testSearchProducts() method tests that the searchProducts() method in the ProductService class retrieves products
     containing a search term in their pname or descr using the ProductRepository, and verifies that the correct method
     in that class is called.
     The testFindProductById() method tests that the findProductById() method in the ProductService class retrieves a
     product by its id using the ProductRepository, and verifies that the correct method in that class is called.
     The testCategoryProducts() method tests that the categoryProducts() method in the ProductService class retrieves
     products for a category using the ProductRepository and CategoryService, and verifies that the correct methods in
     those classes are called.
     */

    @Mock
    private StorageService storageService;
    @Mock
    private ProductRepository dao;
    @Mock
    private CategoryService cdao;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setId(1);
        product.setPname("Product 1");
        product.setDescr("Product 1 Description");
        product.setPrice(10);
        Category category = new Category();
        category.setId(1);
        category.setCatname("Category 1");
        product.setCategory(category);

        MultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[0]);

        when(storageService.store(any(MultipartFile.class))).thenReturn("image.jpg");
        when(dao.save(any(Product.class))).thenReturn(product);

        productService.addProduct(product, file);

        verify(storageService, times(1)).store(any(MultipartFile.class));
        verify(dao, times(1)).save(any(Product.class));
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setId(1);
        product.setPname("Product 1");
        product.setDescr("Product 1 Description");
        product.setPrice(10);
        Category category = new Category();
        category.setId(1);
        category.setCatname("Category 1");
        product.setCategory(category);

        when(dao.save(any(Product.class))).thenReturn(product);

        productService.updateProduct(product);

        verify(dao, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        int id = 1;

        productService.deleteProduct(id);

        verify(dao, times(1)).deleteById(anyInt());
    }

    @Test
    void testAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setPname("Product 1");
        product1.setDescr("Product 1 Description");
        product1.setPrice(10);
        Category category1 = new Category();
        category1.setId(1);
        category1.setCatname("Category 1");
        product1.setCategory(category1);
        productList.add(product1);

        when(dao.findAll()).thenReturn(productList);

        List<Product> result = productService.allProducts();

        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getPname(), result.get(0).getPname());

        verify(dao, times(1)).findAll();
    }

    @Test
    void testSearchProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);

        when(dao.findByPnameOrDescrContaining(anyString(), anyString())).thenReturn(productList);

        List<Product> result = productService.searchProducts("search");

        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getPname(), result.get(0).getPname());

        verify(dao, times(1)).findByPnameOrDescrContaining(anyString(), anyString());
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setId(1);
        product.setPname("Product 1");
        product.setDescr("Product 1 Description");
        product.setPrice(10);
        Category category = new Category();
        category.setId(1);
        category.setCatname("Category 1");
        product.setCategory(category);

        when(dao.findById(1)).thenReturn(Optional.of(product));

        Product result = productService.findProductById(1);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getPname(), result.getPname());

        verify(dao, times(1)).findById(anyInt());
    }

    @Test
    void testCategoryProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setPname("Product 1");
        product1.setDescr("Product 1 Description");
        product1.setPrice(10);
        Category category1 = new Category();
        category1.setId(1);
        category1.setCatname("Category 1");
        product1.setCategory(category1);
        productList.add(product1);

        when(dao.findByCategory(any(Category.class))).thenReturn(productList);
        when(cdao.findById(anyInt())).thenReturn(category1);

        List<Product> result = productService.categoryProducts(1);

        assertEquals(productList.size(), result.size());
        assertEquals(productList.get(0).getPname(), result.get(0).getPname());

        verify(dao, times(1)).findByCategory(any(Category.class));
        verify(cdao, times(1)).findById(anyInt());
    }



}
