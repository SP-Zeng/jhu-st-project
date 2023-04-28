package com.homecommerce.services;

import com.homecommerce.dtos.WishlistDTO;
import com.homecommerce.models.Customer;
import com.homecommerce.models.Product;
import com.homecommerce.models.Wishlist;
import com.homecommerce.repos.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class WishlistServiceTest {
    /**
     This is a test class for the WishlistService. It tests the various methods of the WishlistService
     class including save(), findByuserid(), deleteItem(), and checkexist(). The class uses Mockito for
     mocking the WishlistRepository and CustomerService dependencies. The test methods create mock objects
     and test various scenarios including saving a new wishlist, finding a wishlist by user ID, deleting
     a wishlist item, and checking if a product exists in the wishlist for a given user.
     */

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private WishlistService wishlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        WishlistDTO dto = new WishlistDTO();
        dto.setCustid(1);
        Product product = new Product();
        product.setId(1);

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(new Customer(1));
        wishlist.setProduct(product);

        when(customerService.findById(dto.getCustid())).thenReturn(new Customer(1));
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        wishlistService.save(dto);

        verify(wishlistRepository, times(1)).save(any(Wishlist.class));
    }

    @Test
    void testFindByuserid() {
        Customer customer = new Customer(1);
        List<Wishlist> wishlistList = new ArrayList<>();

        when(customerService.findById(anyInt())).thenReturn(customer);
        when(wishlistRepository.findByCustomer(any(Customer.class))).thenReturn(wishlistList);

        List<Wishlist> result = wishlistService.findByuserid(1);

        assertEquals(wishlistList, result);
        verify(wishlistRepository, times(1)).findByCustomer(any(Customer.class));
    }

    @Test
    void testDeleteItem() {
        doNothing().when(wishlistRepository).deleteById(anyInt());

        wishlistService.deleteItem(1);

        verify(wishlistRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testCheckExistWithExistingItem() {
        Customer customer = new Customer(1);
        Product product = new Product(1);

        when(customerService.findById(1)).thenReturn(customer);
        when(wishlistRepository.findByCustomerAndProduct(customer, product)).thenReturn(new Wishlist());

        boolean result = wishlistService.checkexist(1, product);

        assertTrue(result, "checkexist should return true for existing item");
    }

    @Test
    public void testCheckExistWithNonExistingItem() {
        Customer customer = new Customer(1);
        Product product = new Product(1);

        when(customerService.findById(1)).thenReturn(customer);
        when(wishlistRepository.findByCustomerAndProduct(customer, product)).thenReturn(null);

        boolean result = wishlistService.checkexist(1, product);

        assertFalse(result, "checkexist should return false for non-existing item");
    }
}

