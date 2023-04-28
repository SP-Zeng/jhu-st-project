package com.homecommerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.homecommerce.models.Admin;
import com.homecommerce.repos.AdminRepository;

public class AdminServiceTest {

    private AdminRepository adminRepository;
    private AdminService adminService;

    @BeforeEach
    public void setUp() {
        adminRepository = mock(AdminRepository.class);
        adminService = new AdminService(adminRepository);
    }

    @Test
    @DisplayName("Test validate method with valid credentials")
    public void testValidateWithValidCredentials() {
        String userid = "admin";
        String pwd = "password";
        Admin admin = new Admin(userid, pwd, "John Doe");

        when(adminRepository.findById(userid)).thenReturn(Optional.of(admin));

        Admin validatedAdmin = adminService.validate(userid, pwd);

        assertEquals(admin, validatedAdmin, "Validated admin should match the input admin");
    }

    @Test
    @DisplayName("Test validate method with invalid credentials")
    public void testValidateWithInvalidCredentials() {
        String userid = "admin";
        String pwd = "password";
        Admin admin = new Admin(userid, "wrong_password", "John Doe");

        when(adminRepository.findById(userid)).thenReturn(Optional.of(admin));

        Admin validatedAdmin = adminService.validate(userid, pwd);

        assertNull(validatedAdmin, "Validated admin should be null for invalid credentials");
    }

    @Test
    @DisplayName("Test validate method with non-existent user")
    public void testValidateWithNonExistentUser() {
        String userid = "non_existent";
        String pwd = "password";

        when(adminRepository.findById(userid)).thenReturn(Optional.empty());

        Admin validatedAdmin = adminService.validate(userid, pwd);

        assertNull(validatedAdmin, "Validated admin should be null for non-existent user");
    }

    @Test
    public void testValidateBeforeDaoInitialized() {
        // BUG: This test will fail with a NullPointerException because the dao field is null and has not been initialized.
        AdminService adminService = new AdminService(null);

        // Call the validate method with valid credentials
        String userid = "admin";
        String pwd = "password";
        Admin admin = new Admin(userid, pwd, "John Doe");
        when(adminRepository.findById(userid)).thenReturn(Optional.of(admin));
        Admin validatedAdmin = adminService.validate(userid, pwd);

        assertEquals(admin, validatedAdmin, "Validated admin should match the input admin");
    }


    @Test
    @DisplayName("Test saveAdmin method")
    public void testSaveAdmin() {
        Admin admin = new Admin("admin", "password", "John Doe");

        adminService.saveAdmin(admin);

        verify(adminRepository).save(admin);
    }

    @Test
    @DisplayName("Test updateAdmin method with a new password")
    public void testUpdateAdminWithNewPassword() {
        Admin admin = new Admin("admin", "password", "John Doe");
        Admin updatedAdmin = new Admin("admin", "new_password", "John Doe");

        when(adminRepository.findById(admin.getUserid())).thenReturn(Optional.of(admin));

        adminService.updateAdmin(updatedAdmin);

        verify(adminRepository).save(updatedAdmin);
    }

    @Test
    public void testUpdateAdminForNullParameter() {
        // BUG: because updateAdmin fails on Null input
        Admin updatedAdmin = null;

        adminService.updateAdmin(updatedAdmin);

    }
    @Test
    @DisplayName("Test updateAdmin method with null password")
    public void testUpdateAdminWithNullPassword() {
        Admin admin = new Admin("admin", "password", "John Doe");
        Admin updatedAdmin = new Admin("admin", null, "John Doe");

        when(adminRepository.findById(admin.getUserid())).thenReturn(Optional.of(admin));

        adminService.updateAdmin(updatedAdmin);

        verify(adminRepository).save(updatedAdmin);
        assertEquals("password", updatedAdmin.getPwd(), "Admin password should not be updated if new password is null");
    }

    @Test
    @DisplayName("Test updateAdmin method with empty password")
    public void testUpdateAdminWithEmptyPassword() {
        Admin admin = new Admin("admin", "password", "John Doe");
        Admin updatedAdmin = new Admin("admin", "", "John Doe");

        when(adminRepository.findById(admin.getUserid())).thenReturn(Optional.of(admin));

        adminService.updateAdmin(updatedAdmin);

        verify(adminRepository).save(updatedAdmin);
        assertEquals("password", updatedAdmin.getPwd(), "Admin password should not be updated if new password is empty");
    }

    @Test
    @DisplayName("Test updateAdmin method with non-existent user")
    public void testUpdateAdminWithNonExistentUser() {
        Admin admin = new Admin("non_existent", "password", "John Doe");

        when(adminRepository.findById(admin.getUserid())).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> {
            adminService.updateAdmin(admin);
        }, "updateAdmin should throw NullPointerException if user does not exist");
    }

    @Test
    @DisplayName("Test count method")
    public void testCount() {
        long expectedCount = 10L;

        when(adminRepository.count()).thenReturn(expectedCount);

        long actualCount = adminService.count();

        assertEquals(expectedCount, actualCount, "Count should match the expected value");
    }
}