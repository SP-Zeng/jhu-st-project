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
        // Given
        Category category = new Category();
        category.setCatname("TestCategory");

        // When
        ResponseEntity<?> response = categoryController.saveCategory(category);

        // Then
        verify(categoryService, times(1)).save(category);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("{data=Category saved, status=success}", response.getBody().toString());
    }


    @Test
    void testListAll() {
        // Given
        Category category1 = new Category();
        Category category2 = new Category();
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        when(categoryService.listAll()).thenReturn(categories);

        // When
        List<Category> response = categoryController.listall();

        // Then
        verify(categoryService, times(1)).listAll();
        Assertions.assertEquals(categories.size(), response.size());
        Assertions.assertEquals(categories.get(0), response.get(0));
        Assertions.assertEquals(categories.get(1), response.get(1));
    }

    @Test
    void testDeleteCategoryReturnsSuccess() {
        // Given
        Category cat = new Category();
        cat.setCatname("Test");
        categoryService.save(cat);
        int catId = cat.getId();

        // When
        ResponseEntity<?> response = categoryController.deleteCategory(catId);

        // Then
        verify(categoryService, times(1)).deleteCategory(catId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("status", "success");
        expected.put("data", "Category deleted successfully");
        Assertions.assertEquals(expected, response.getBody());
    }

}
