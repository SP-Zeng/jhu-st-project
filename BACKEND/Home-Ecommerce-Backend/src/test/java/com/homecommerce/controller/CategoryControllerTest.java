package com.homecommerce.controller;

import com.homecommerce.models.Category;
import com.homecommerce.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import static org.mockito.Mockito.*;

class CategoryControllerTest {
    /**
     This is a test class for the CategoryController class which handles HTTP requests and responses for the Category model.
     The tests in this class ensure that the CategoryController class is working as intended.
     It uses the Mockito framework to mock the CategoryService dependency, and test the behavior of the CategoryController methods.
     The first test method, testSaveCategory(), verifies that the saveCategory() method in the CategoryController class saves a category
     using the CategoryService, and returns an HTTP status of OK along with a success message.
     The second test method, testListAll(), verifies that the listAll() method in the CategoryController class retrieves all categories
     using the CategoryService, and returns a list of categories.
     The third test method, testDeleteCategoryReturnsSuccess(), verifies that the deleteCategory() method in the CategoryController class
     deletes a category using the CategoryService, and returns an HTTP status of OK along with a success message.
     */

    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController();
        categoryController.cservice = categoryService;
    }

    @Test
    void testSaveCategory() {
        Category category = new Category();
        category.setCatname("TestCategory");

        ResponseEntity<?> response = categoryController.saveCategory(category);

        verify(categoryService, times(1)).save(category);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("{data=Category saved, status=success}", response.getBody().toString());
    }


    @Test
    void testListAll() {
        Category category1 = new Category();
        Category category2 = new Category();
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        when(categoryService.listAll()).thenReturn(categories);

        List<Category> response = categoryController.listall();

        verify(categoryService, times(1)).listAll();
        Assertions.assertEquals(categories.size(), response.size());
        Assertions.assertEquals(categories.get(0), response.get(0));
        Assertions.assertEquals(categories.get(1), response.get(1));
    }

    @Test
    void testDeleteCategoryReturnsSuccess() {
        Category cat = new Category();
        cat.setCatname("Test");
        categoryService.save(cat);
        int catId = cat.getId();

        ResponseEntity<?> response = categoryController.deleteCategory(catId);

        verify(categoryService, times(1)).deleteCategory(catId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("status", "success");
        expected.put("data", "Category deleted successfully");
        Assertions.assertEquals(expected, response.getBody());
    }

}
