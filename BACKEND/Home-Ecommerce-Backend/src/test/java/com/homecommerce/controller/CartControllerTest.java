package com.homecommerce.controller;

import com.homecommerce.models.Cart;
import com.homecommerce.services.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CartControllerTest {

    private CartService cartService;
    private CartController cartController;

    private Cart cart;

    @BeforeEach
    void setUp() {
        cartService = Mockito.mock(CartService.class);
        cartController = new CartController();
        cartController.service = cartService;

        cart = new Cart();
        cart.setId(1);
        cart.setQty(1);
    }

    @Test
    void testSaveItem() {
        // Given
        Mockito.when(cartService.checkexist(Mockito.any(), Mockito.any())).thenReturn(false);
        Mockito.doNothing().when(cartService).save(Mockito.any());

        // When
        ResponseEntity<?> response = cartController.saveItem(cart);

        // Then
        Mockito.verify(cartService, Mockito.times(1)).checkexist(Mockito.any(), Mockito.any());
        Mockito.verify(cartService, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testSaveItemAlreadyExists() {
        // Given
        Mockito.when(cartService.checkexist(Mockito.any(), Mockito.any())).thenReturn(true);

        // When
        ResponseEntity<?> response = cartController.saveItem(cart);

        // Then
        Mockito.verify(cartService, Mockito.times(1)).checkexist(Mockito.any(), Mockito.any());
        Mockito.verify(cartService, Mockito.times(0)).save(Mockito.any());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Item already exists", response.getBody());
    }

    @Test
    void testUpdateQty() {
        // Given
        Mockito.doNothing().when(cartService).updateQty(Mockito.anyInt(), Mockito.anyInt());

        // When
        ResponseEntity<?> response = cartController.updateQty(1, 2);

        // Then
        Mockito.verify(cartService, Mockito.times(1)).updateQty(Mockito.anyInt(), Mockito.anyInt());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testListAll() {
        // Given
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        Mockito.when(cartService.findByuserid(Mockito.anyInt())).thenReturn(carts);

        // When
        List<Cart> response = cartController.listall(1);

        // Then
        Mockito.verify(cartService, Mockito.times(1)).findByuserid(Mockito.anyInt());
        Assertions.assertEquals(carts, response);
    }

    @Test
    void testDeleteItem() {
        // Given
        Mockito.doNothing().when(cartService).deleteItem(Mockito.anyInt());

        // When
        ResponseEntity<?> response = cartController.deleteItem(1);

        // Then
        Mockito.verify(cartService, Mockito.times(1)).deleteItem(Mockito.anyInt());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
