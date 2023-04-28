package com.homecommerce.services;

import com.homecommerce.models.Category;
import com.homecommerce.repos.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    /**
     This class contains test cases for the CategoryService class.
     It uses Mockito to mock the CategoryRepository and test the CategoryService methods.
     The CategoryServiceTest class has four test cases:
     testSave() - Tests the save() method of CategoryService by mocking the CategoryRepository and verifying that the save() method is called once.
     testListAll() - Tests the listAll() method of CategoryService by mocking the CategoryRepository and verifying that it returns a list with the expected number of categories and that the categories in the list match the expected categories.
     testFindById() - Tests the findById() method of CategoryService by mocking the CategoryRepository and verifying that it returns the expected category for a given ID.
     testDeleteCategory() - Tests the deleteCategory() method of CategoryService by mocking the CategoryRepository and verifying that it calls the deleteById() method once.
     */

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setCatname("Category 1");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        categoryService.save(category);

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testListAll() {
        Category category1 = new Category();
        category1.setId(1);
        category1.setCatname("Category 1");

        Category category2 = new Category();
        category2.setId(2);
        category2.setCatname("Category 2");

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.listAll();

        assertEquals(2, result.size());
        assertEquals(category1, result.get(0));
        assertEquals(category2, result.get(1));

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Category category = new Category();
        category.setId(1);
        category.setCatname("Category 1");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category result = categoryService.findById(1);

        assertEquals(category, result);

        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    void testDeleteCategory() {
        categoryService.deleteCategory(1);

        verify(categoryRepository, times(1)).deleteById(anyInt());
    }
}
