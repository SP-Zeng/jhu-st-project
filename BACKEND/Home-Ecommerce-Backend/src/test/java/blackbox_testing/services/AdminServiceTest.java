package blackbox_testing.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.homecommerce.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homecommerce.models.Admin;
import com.homecommerce.repos.AdminRepository;

public class AdminServiceTest {

  @Mock
  AdminRepository dao;

  @InjectMocks
  AdminService service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  // Test validate method with valid user id and password
  @Test
  public void testValidate_ValidUserIdAndPassword() {
    String userid = "user1";
    String pwd = "pwd1";
    Admin admin = new Admin(userid, pwd, "");
    when(dao.findById(userid)).thenReturn(Optional.of(admin));

    Admin result = service.validate(userid, pwd);
    assertEquals(admin, result);

    verify(dao, times(1)).findById(userid);
  }

  // Test validate method with valid user id and invalid password
  @Test
  public void testValidate_ValidUserIdInvalidPassword() {
    String userid = "user1";
    String pwd = "pwd1";
    Admin admin = new Admin(userid, pwd, "");
    when(dao.findById(userid)).thenReturn(Optional.of(admin));

    Admin result = service.validate(userid, "invalidPwd");
    assertNull(result);

    verify(dao, times(1)).findById(userid);
  }

  // Test validate method with invalid user id
  @Test
  public void testValidate_InvalidUserId() {
    String userid = "invalidUser";
    when(dao.findById(userid)).thenReturn(Optional.empty());

    Admin result = service.validate(userid, "pwd");
    assertNull(result);

    verify(dao, times(1)).findById(userid);
  }

  // Test saveAdmin method with a valid Admin object
  @Test
  public void testSaveAdmin_ValidAdmin() {
    Admin admin = new Admin("user", "pwd", "");
    service.saveAdmin(admin);

    verify(dao, times(1)).save(admin);
  }

  // Test saveAdmin method with a null Admin object
  @Test
  public void testSaveAdmin_NullAdmin() {
    service.saveAdmin(null);

  }

  // Test updateAdmin method with an Admin object with a non-empty password
  @Test
  public void testUpdateAdmin_NonEmptyPassword() {
    Admin admin = new Admin("user", "pwd", "");
    when(dao.findById(admin.getUserid())).thenReturn(Optional.of(admin));
    service.updateAdmin(admin);

    verify(dao, times(1)).save(admin);
  }

  // Test updateAdmin method with an Admin object with an empty password
  @Test
  public void testUpdateAdmin_EmptyPassword() {
    Admin admin = new Admin("user", "", "");
    Admin existingAdmin = new Admin("user", "pwd", "");
    when(dao.findById(admin.getUserid())).thenReturn(Optional.of(existingAdmin));
    service.updateAdmin(admin);

    verify(dao, times(1)).save(admin);
  }
}
