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
    /**
     This class represents the unit tests for the CartController class, which is responsible for handling HTTP requests related to the Cart model.
     The tests include checking the functionality of methods that handle adding, updating, listing, and deleting items in the cart.
     The tests are performed using Mockito to mock the CartService dependency, which is injected into the CartController.
     The tests ensure that the controller interacts with the service layer correctly and that the expected HTTP response statuses and bodies are returned.
     */

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

        Mockito.when(cartService.checkexist(Mockito.any(), Mockito.any())).thenReturn(false);
        Mockito.doNothing().when(cartService).save(Mockito.any());


        ResponseEntity<?> response = cartController.saveItem(cart);


        Mockito.verify(cartService, Mockito.times(1)).checkexist(Mockito.any(), Mockito.any());
        Mockito.verify(cartService, Mockito.times(1)).save(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testSaveItemAlreadyExists() {

        Mockito.when(cartService.checkexist(Mockito.any(), Mockito.any())).thenReturn(true);


        ResponseEntity<?> response = cartController.saveItem(cart);


        Mockito.verify(cartService, Mockito.times(1)).checkexist(Mockito.any(), Mockito.any());
        Mockito.verify(cartService, Mockito.times(0)).save(Mockito.any());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Item already exists", response.getBody());
    }

    @Test
    void testUpdateQty() {

        Mockito.doNothing().when(cartService).updateQty(Mockito.anyInt(), Mockito.anyInt());


        ResponseEntity<?> response = cartController.updateQty(1, 2);


        Mockito.verify(cartService, Mockito.times(1)).updateQty(Mockito.anyInt(), Mockito.anyInt());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testListAll() {

        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        Mockito.when(cartService.findByuserid(Mockito.anyInt())).thenReturn(carts);


        List<Cart> response = cartController.listall(1);


        Mockito.verify(cartService, Mockito.times(1)).findByuserid(Mockito.anyInt());
        Assertions.assertEquals(carts, response);
    }

    @Test
    void testDeleteItem() {

        Mockito.doNothing().when(cartService).deleteItem(Mockito.anyInt());


        ResponseEntity<?> response = cartController.deleteItem(1);


        Mockito.verify(cartService, Mockito.times(1)).deleteItem(Mockito.anyInt());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
