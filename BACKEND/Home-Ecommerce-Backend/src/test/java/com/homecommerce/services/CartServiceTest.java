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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CartServiceTest {
    /**
     The CartServiceTest class contains JUnit tests for the CartService class.
     The tests cover the following methods of the CartService class:
     save(): tests saving a cart object to the repository.
     findByuserid(): tests finding carts by user id.
     updateQty(): tests updating the quantity of a cart item.
     deleteItem(): tests deleting a cart item.
     checkexist(): tests checking if a cart item exists.
     clearCart(): tests clearing a customer's cart.
     The tests use Mockito to mock the CartRepository and CustomerService dependencies of the CartService class.
     The testCheckExistWithExistingItem() and testCheckExistWithNonExistingItem() tests are added to test the checkexist() method with
     an existing and non-existing item respectively.
     */

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
    @Test
    public void testCheckExistWithExistingItem() {
        int customerId = 1;
        int productId = 1;

        Customer customer = new Customer();
        customer.setId(customerId);

        Product product = new Product();
        product.setId(productId);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setProduct(product);

        when(cartRepository.findByCustomerAndProduct(customer, product)).thenReturn(cart);

        boolean result = cartService.checkexist(customer, product);

        assertTrue(result, "checkexist should return true for existing cart item");
    }

    @Test
    public void testCheckExistWithNonExistingItem() {
        int customerId = 1;
        int productId = 1;

        Customer customer = new Customer();
        customer.setId(customerId);

        Product product = new Product();
        product.setId(productId);

        when(cartRepository.findByCustomerAndProduct(customer, product)).thenReturn(null);

        boolean result = cartService.checkexist(customer, product);

        assertFalse(result, "checkexist should return false for non-existing cart item");
    }

}
