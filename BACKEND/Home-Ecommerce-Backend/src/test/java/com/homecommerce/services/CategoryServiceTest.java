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
