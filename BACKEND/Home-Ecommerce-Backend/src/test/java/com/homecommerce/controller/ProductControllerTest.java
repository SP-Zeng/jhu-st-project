package com.homecommerce.controller;

import com.homecommerce.dtos.ProductDTO;
import com.homecommerce.dtos.Response;
import com.homecommerce.models.Category;
import com.homecommerce.models.Product;
import com.homecommerce.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setPname("Product 1");
        dto.setDescr("Product 1 Description");
        dto.setPrice(10);
        dto.setPic(mock(MultipartFile.class));
        Category category = new Category();
        category.setId(1);
        category.setCatname("Category 1");
        dto.setCategory(category);

        Product product = new Product();
        product.setPname(dto.getPname());
        product.setDescr(dto.getDescr());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());


        ResponseEntity<?> responseEntity = productController.saveProduct(dto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Response.success(product).getBody(), responseEntity.getBody());

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


        ResponseEntity<?> responseEntity = productController.updateProduct(product, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Response.success(product).getBody(), responseEntity.getBody());

        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    void testFindProduct() {
        Product product = new Product();
        product.setId(1);
        product.setPname("Product 1");
        product.setDescr("Product 1 Description");
        product.setPrice(10);
        Category category = new Category();
        category.setId(1);
        category.setCatname("Category 1");
        product.setCategory(category);

        when(productService.findProductById(1)).thenReturn(product);

        ResponseEntity<?> responseEntity = productController.findProduct(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Response.success(product).getBody(), responseEntity.getBody());

        verify(productService, times(1)).findProductById(anyInt());
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setPname("Product 1");
        product1.setDescr("Product 1 Description");
        product1.setPrice(10);
        Category category1 = new Category();
        category1.setId(1);
        category1.setCatname("Category 1");
        Product product2 = new Product();
        product2.setId(2);
        product2.setPname("Product 2");
        product2.setDescr("Product 2 Description");
        product2.setPrice(20);
        Category category2 = new Category();
        category2.setId(2);
        category2.setCatname("Category 2");
        product2.setCategory(category2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productService.allProducts()).thenReturn(productList);

        // Test with search param
        Optional<String> search = Optional.of("Product");
        List<Product> resultList = productController.findAllProducts(search);
        assertEquals(2, resultList.size());
        assertEquals(productList, resultList);
        verify(productService, times(1)).searchProducts(anyString());

        // Test without search param
        search = Optional.empty();
        resultList = productController.findAllProducts(search);
        assertEquals(2, resultList.size());
        assertEquals(productList, resultList);
        verify(productService, times(1)).allProducts();
    }

    @Test
    void testFindByCategory() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setPname("Product 1");
        product1.setDescr("Product 1 Description");
        product1.setPrice(10);
        Category category1 = new Category();
        category1.setId(1);
        category1.setCatname("Category 1");
        product1.setCategory(category1);
        Product product2 = new Product();
        product2.setId(2);
        product2.setPname("Product 2");
        product2.setDescr("Product 2 Description");
        product2.setPrice(20);
        Category category2 = new Category();
        category2.setId(1);
        category2.setCatname("Category 1");
        product2.setCategory(category2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productService.categoryProducts(1)).thenReturn(productList);

        List<Product> resultList = productController.findByCategory(1);
        assertEquals(2, resultList.size());
        assertEquals(productList, resultList);

        verify(productService, times(1)).categoryProducts(anyInt());
    }

    @Test
    void testDeleteProduct() {
        ResponseEntity<?> responseEntity = productController.deleteProduct(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(productService, times(1)).deleteProduct(anyInt());
    }

}
