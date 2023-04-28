package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.homecommerce.dtos.LoginDTO;
import org.junit.jupiter.api.Test;

public class LoginDTOTest {
    /**
     This is a test class for the LoginDTO class which represents a data transfer object for login credentials.
     The tests in this class ensure that the LoginDTO class is working as intended by testing the behavior of its getter and setter methods.
     The first test method, testGetSetUserid(), verifies that the setUserid() and getUserid() methods in the LoginDTO class set and get
     the userid field correctly.
     The second test method, testGetSetPwd(), verifies that the setPwd() and getPwd() methods in the LoginDTO class set and get
     the pwd field correctly.
     The third test method, testGetSetRole(), verifies that the setRole() and getRole() methods in the LoginDTO class set and get
     the role field correctly.
     The fourth test method, testToString(), verifies that the toString() method in the LoginDTO class returns the expected string representation
     of the object.
     */

    @Test
    public void testGetSetUserid() {
        String userid = "admin";
        LoginDTO dto = new LoginDTO();

        dto.setUserid(userid);

        assertEquals(userid, dto.getUserid(), "Userid should match the input value");
    }

    @Test
    public void testGetSetPwd() {
        String pwd = "password";
        LoginDTO dto = new LoginDTO();

        dto.setPwd(pwd);

        assertEquals(pwd, dto.getPwd(), "Password should match the input value");
    }

    @Test
    public void testGetSetRole() {
        String role = "admin";
        LoginDTO dto = new LoginDTO();

        dto.setRole(role);

        assertEquals(role, dto.getRole(), "Role should match the input value");
    }

    @Test
    public void testToString() {
        String userid = "admin";
        String pwd = "password";
        String role = "admin";
        LoginDTO dto = new LoginDTO();
        dto.setUserid(userid);
        dto.setPwd(pwd);
        dto.setRole(role);

        String expectedString = "LoginDTO [userid=" + userid + ", pwd=" + pwd + ", role=" + role + "]";

        assertEquals(expectedString, dto.toString(), "String representation should match the expected value");
    }
}
