package blackbox_testing.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.homecommerce.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Address;
import com.homecommerce.repos.AddressRepository;

public class AddressServiceTest {

  @Mock
  AddressRepository dao;

  @InjectMocks
  AddressService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test saveAddress method with valid input
  @Test
  public void testSaveAddress_ValidInput() {
    Address address = new Address();
    // Populate address object with valid data

    when(dao.save(address)).thenReturn(address);

    Address result = service.saveAddress(address);
    assertEquals(address, result);

    verify(dao, times(1)).save(address);
  }

  // Test saveAddress method with invalid input
  @Test
  public void testSaveAddress_InvalidInput() {
    Address address = new Address();
    // Populate address object with invalid data

    when(dao.save(address)).thenThrow(new IllegalArgumentException());

    assertThrows(IllegalArgumentException.class, () -> service.saveAddress(address));

    verify(dao, times(1)).save(address);
  }

  // Test saveAddress method with null input
  @Test
  public void testSaveAddress_NullInput() {
    assertThrows(IllegalArgumentException.class, () -> service.saveAddress(null));

    verify(dao, never()).save(any());
  }

  // Test findAddress method with valid existing id
  @Test
  public void testFindAddress_ExistingId() {
    int id = 1;
    Address address = new Address();
    // Populate address object with some data

    when(dao.findById(id)).thenReturn(Optional.of(address));

    Address result = service.findAddress(id);
    assertEquals(address, result);

    verify(dao, times(1)).findById(id);
  }

  // Test findAddress method with valid non-existing id
  @Test
  public void testFindAddress_NonExistingId() {
    int id = 1;

    when(dao.findById(id)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.findAddress(id));

    verify(dao, times(1)).findById(id);
  }

  // Test findAddress method with invalid id (e.g. negative id)
  @Test
  public void testFindAddress_InvalidId() {
    int id = -1;

    assertThrows(NoSuchElementException.class, () -> service.findAddress(id));

  }
}

