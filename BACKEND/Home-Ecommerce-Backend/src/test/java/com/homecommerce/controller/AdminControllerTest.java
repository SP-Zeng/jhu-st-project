package com.homecommerce.controller;

import com.homecommerce.dtos.LoginDTO;
import com.homecommerce.models.Admin;
import com.homecommerce.services.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AdminControllerTest {

    private AdminService adminService;
    private AdminController adminController;

    private LoginDTO loginDTO;

    @BeforeEach
    void setUp() {
        adminService = mock(AdminService.class);
        adminController = new AdminController();
        adminController.adminService = adminService;
        loginDTO = new LoginDTO();
        loginDTO.setUserid("admin");
        loginDTO.setPwd("password");
    }

    @Test
    void testValidateUserReturnsSuccess() {
        Admin admin = new Admin();
        when(adminService.validate("admin", "password")).thenReturn(admin);

        ResponseEntity<?> response = adminController.validateUser(this.loginDTO);

        verify(adminService, times(1)).validate("admin", "password");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(response.getBody() instanceof Map, "Response body should be a Map");
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Assertions.assertEquals("success", responseBody.get("status"), "Response status should be success");
        Assertions.assertTrue(responseBody.containsKey("data"), "Response should contain data field");
        Assertions.assertTrue(responseBody.get("data") instanceof Admin, "Response data should be an Admin object");
        Admin actualAdmin = (Admin) responseBody.get("data");
        Assertions.assertEquals(admin, actualAdmin, "Response data should match expected Admin object");
    }

    @Test
    void testValidateUserReturnsNotFound() {
        when(adminService.validate("admin", "password")).thenReturn(null);

        ResponseEntity<?> response = adminController.validateUser(this.loginDTO);

        verify(adminService, times(1)).validate("admin", "password");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateProfile() {
        Admin admin = new Admin();

        ResponseEntity<?> response = adminController.updateProfile(admin);

        verify(adminService, times(1)).updateAdmin(admin);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
