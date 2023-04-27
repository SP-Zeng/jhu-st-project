package blackbox_testing.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.homecommerce.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Category;
import com.homecommerce.repos.CategoryRepository;

public class CategoryServiceTest {

  @Mock
  CategoryRepository repo;

  @InjectMocks
  CategoryService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test save method with a valid Category object
  @Test
  public void testSave_ValidCategory() {
    Category cat = new Category();
    service.save(cat);

    verify(repo, times(1)).save(cat);
  }

  // Test save method with a null Category object
  @Test
  public void testSave_NullCategory() {
    service.save(null);
  }

  // Test findById method with a valid category id that exists in the system
  @Test
  public void testFindById_ValidId() {
    int id = 1;
    Category cat = new Category();
    when(repo.findById(id)).thenReturn(Optional.of(cat));

    Category result = service.findById(id);

    assertEquals(cat, result);
    verify(repo, times(1)).findById(id);
  }

  // Test findById method with a valid category id that does not exist in the system
  @Test
  public void testFindById_InvalidId() {
    int id = 1;
    when(repo.findById(id)).thenReturn(Optional.empty());

    Category result = service.findById(id);

    assertNull(result);
    verify(repo, times(1)).findById(id);
  }

  // Test deleteCategory method with a valid category id
  @Test
  public void testDeleteCategory_ValidId() {
    int id = 1;

    service.deleteCategory(id);

    verify(repo, times(1)).deleteById(id);
  }

  // Test listAll method
  @Test
  public void testListAll() {
    List<Category> categories = Arrays.asList(new Category());
    when(repo.findAll()).thenReturn(categories);

    List<Category> result = service.listAll();

    assertEquals(categories, result);
    verify(repo, times(1)).findAll();
  }
}

