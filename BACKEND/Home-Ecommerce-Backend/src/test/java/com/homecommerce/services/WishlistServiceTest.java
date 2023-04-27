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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class WishlistServiceTest {

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
    void testCheckexist() {
        Customer customer = new Customer(1);
        Product product = new Product();
        product.setId(1);
        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer);
        wishlist.setProduct(product);

        when(customerService.findById(anyInt())).thenReturn(customer);
        when(wishlistRepository.findByCustomerAndProduct(any(Customer.class), any(Product.class))).thenReturn(wishlist);

        boolean result = wishlistService.checkexist(1, product);

        assertEquals(true, result);
        verify(wishlistRepository, times(1)).findByCustomerAndProduct(any(Customer.class), any(Product.class));
    }
}

