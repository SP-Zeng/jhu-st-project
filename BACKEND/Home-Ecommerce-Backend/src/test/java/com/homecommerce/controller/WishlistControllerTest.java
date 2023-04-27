package com.homecommerce.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.homecommerce.dtos.Response;
import com.homecommerce.dtos.WishlistDTO;
import com.homecommerce.models.Customer;
import com.homecommerce.models.Product;
import com.homecommerce.models.Wishlist;
import com.homecommerce.services.WishlistService;

public class WishlistControllerTest {

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveItem() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setCustid(1);
        Product product = new Product();
        wishlistDTO.setProduct(product);

        ResponseEntity<?> response = wishlistController.saveItem(wishlistDTO);

        verify(wishlistService).checkexist(1, product);
        verify(wishlistService).save(wishlistDTO);

        Response.success("Item saved into Wishlist").equals(response.getBody());
    }

    @Test
    void testSaveItemAlreadyExists() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setCustid(1);
        Product product = new Product();
        wishlistDTO.setProduct(product);

        when(wishlistService.checkexist(1, product)).thenReturn(true);

        ResponseEntity<?> response = wishlistController.saveItem(wishlistDTO);

        verify(wishlistService).checkexist(1, product);

        ResponseEntity.badRequest().body("Item already exists").equals(response);
    }

    @Test
    void testListall() {
        List<Wishlist> wishlist = new ArrayList<>();
        Customer customer = new Customer();
        wishlist.add(new Wishlist());
        // there isn't a way to attach a wishlist to a customer
        when(wishlistService.findByuserid(1)).thenReturn(wishlist);

        List<Wishlist> response = wishlistController.listall(1);

        verify(wishlistService).findByuserid(1);

        wishlist.equals(response);
    }

    @Test
    void testDeleteItem() {
        ResponseEntity<?> response = wishlistController.deleteItem(1);

        verify(wishlistService).deleteItem(1);

        Response.success("item deleted successfully").equals(response.getBody());
    }
}
