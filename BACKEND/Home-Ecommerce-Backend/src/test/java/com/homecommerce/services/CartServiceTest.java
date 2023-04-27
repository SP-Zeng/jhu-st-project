package com.homecommerce.services;

import com.homecommerce.models.Cart;
import com.homecommerce.models.Customer;
import com.homecommerce.models.Product;
import com.homecommerce.repos.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        Cart cart = new Cart();
        cart.setId(1);
        cart.setQty(1);
        cart.setCustomer(new Customer());
        cart.setProduct(new Product());

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.save(cart);

        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testFindByuserid() {
        Customer customer = new Customer();
        customer.setId(1);

        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());

        when(customerService.findById(1)).thenReturn(customer);
        when(cartRepository.findByCustomer(customer)).thenReturn(carts);

        List<Cart> result = cartService.findByuserid(1);

        assertEquals(carts, result);

        verify(customerService, times(1)).findById(anyInt());
        verify(cartRepository, times(1)).findByCustomer(any(Customer.class));
    }

    @Test
    void testUpdateQty() {
        Cart cart = new Cart();
        cart.setId(1);
        cart.setQty(1);

        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.updateQty(1, 2);

        assertEquals(2, cart.getQty());

        verify(cartRepository, times(1)).findById(anyInt());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testDeleteItem() {
        doNothing().when(cartRepository).deleteById(anyInt());

        cartService.deleteItem(1);

        verify(cartRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void testCheckexist() {
        Customer customer = new Customer();
        customer.setId(1);
        Product product = new Product();
        product.setId(1);

        when(cartRepository.findByCustomerAndProduct(any(Customer.class), any(Product.class))).thenReturn(new Cart());

        boolean result = cartService.checkexist(customer, product);

        assertEquals(true, result);

        verify(cartRepository, times(1)).findByCustomerAndProduct(any(Customer.class), any(Product.class));
    }

    @Test
    void testClearCart() {
        Customer customer = new Customer();
        customer.setId(1);

        doNothing().when(cartRepository).deleteAll(anyList());

        cartService.clearCart(customer);

        verify(cartRepository, times(1)).deleteAll(anyList());
        verify(cartRepository, times(1)).findByCustomer(any(Customer.class));
    }
}
