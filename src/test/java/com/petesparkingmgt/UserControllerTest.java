package com.petesparkingmgt;

import static org.mockito.Mockito.*;

import com.petesparkingmgt.ctl.LoginCtl;
import com.petesparkingmgt.ctl.UserCtl;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.PendingUserDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.PendingUserService;
import com.petesparkingmgt.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @InjectMocks
    private LoginCtl userController;

    @Mock
    private UserService service;

    @Mock
    private UserDAO dao;

    @Mock
    private PendingUserService pendingUserService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    private UserForm form;

    @BeforeEach
    public void setUp() {
        form = new UserForm();
        form.setEmail("test@example.com");
        form.setPassword("password");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        when(service.login(form.getEmail(), form.getPassword())).thenReturn(null);
        when(pendingUserService.getPendingUser(form.getEmail())).thenReturn(null);

        String result = userController.Login(form, model, session);

        assertEquals("home", result);
        verify(model).addAttribute("error", "Invalid username/password or register an account.");
    }

    @Test
    public void testLoginWithPendingUser() {
        when(service.login(form.getEmail(), form.getPassword())).thenReturn(null);
        when(pendingUserService.getPendingUser(form.getEmail())).thenReturn(new PendingUserDTO());

        String result = userController.Login(form, model, session);

        assertEquals("home", result);
        verify(model).addAttribute("error", "Admin must approve your account!");
    }

    @Test
    public void testLoginAsAdmin() {
        UserDTO adminUser = new UserDTO();
        adminUser.setUserRole("Admin");
        when(service.login(form.getEmail(), form.getPassword())).thenReturn(adminUser);

        String result = userController.Login(form, model, session);

        assertEquals("adminview", result);
        verify(session).setAttribute("user", adminUser);
    }

    @Test
    public void testLoginAsRegularUser() {
        UserDTO regularUser = new UserDTO();
        regularUser.setUserRole("User");
        when(service.login(form.getEmail(), form.getPassword())).thenReturn(regularUser);

        String result = userController.Login(form, model, session);

        assertEquals("redirect:/main", result);
        verify(session).setAttribute("user", regularUser);
    }
}
