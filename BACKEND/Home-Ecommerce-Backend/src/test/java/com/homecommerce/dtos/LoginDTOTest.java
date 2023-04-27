package com.homecommerce.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.homecommerce.dtos.LoginDTO;
import org.junit.jupiter.api.Test;

public class LoginDTOTest {

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
