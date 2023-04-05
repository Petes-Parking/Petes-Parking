package com.petesparkingmgt;

import com.petesparkingmgt.ctl.ParkingPalsCtl;
import com.petesparkingmgt.dao.users.FriendDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.FriendDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.AddFriendForm;
import com.petesparkingmgt.form.FriendResponseForm;
import com.petesparkingmgt.service.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingPalsCtlTest {

    @InjectMocks
    private ParkingPalsCtl parkingPalsCtl;

    @Mock
    private FriendService service;

    @Mock
    private FriendDAO dao;

    @Mock
    private UserDAO userDAO;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    private UserDTO user;

    @BeforeEach
    void setUp() {
        user = new UserDTO();
        user.setEmail("test@example.com");
        user.setFirstName("Test");
        user.setLastName("User");
    }

    @Test
    void parkingPals() {
        when(session.getAttribute("user")).thenReturn(user);
        when(userDAO.findAll()).thenReturn(Arrays.asList(user));

        parkingPalsCtl.parkingPals(model, session);

        verify(model, times(1)).addAttribute("users", Arrays.asList(user));
        verify(service, times(1)).getInvitesForUser(user.getEmail());
        verify(service, times(1)).getConfirmedUsersFor(user.getEmail());
    }

    @Test
    void invite() throws NoSuchAlgorithmException, KeyManagementException {
        AddFriendForm form = new AddFriendForm("invited@example.com");

        when(session.getAttribute("user")).thenReturn(user);
        when(userDAO.findAll()).thenReturn(Arrays.asList(user));
        when(userDAO.findByEmail("invited@example.com")).thenReturn(user);

        parkingPalsCtl.invite(form, model, session);

        verify(model, times(1)).addAttribute("users", Arrays.asList(user));
        verify(service, times(1)).getInvitesForUser(user.getEmail());
        verify(service, times(1)).getConfirmedUsersFor(user.getEmail());
        verify(dao, times(1)).save(any(FriendDTO.class));
    }

    @Test
    void inviteResponse_accept() {
        FriendResponseForm form = new FriendResponseForm();
        form.setAction("accept");
        form.setEmail("invited@example.com");

        when(session.getAttribute("user")).thenReturn(user);
        when(userDAO.findAll()).thenReturn(Arrays.asList(user));

        parkingPalsCtl.inviteResponse(form, model, session);

        verify(model, times(1)).addAttribute("users", Arrays.asList(user));
        verify(service, times(1)).getInvitesForUser(user.getEmail());
        verify(service, times(1)).getConfirmedUsersFor(user.getEmail());
        verify(service, times(1)).acceptInvite(user.getEmail(), form.getEmail());
    }

    @Test
    void inviteResponse_reject() {
        FriendResponseForm form = new FriendResponseForm();
        form.setAction("reject");
        form.setEmail("invited@example.com");

        when(session.getAttribute("user")).thenReturn(user);
        when(userDAO.findAll()).thenReturn(Arrays.asList(user));

        parkingPalsCtl.inviteResponse(form, model, session);

        verify(model, times(1)).addAttribute("users", Arrays.asList(user));
        verify(service, times(1)).getInvitesForUser(user.getEmail());
        verify(service, times(1)).getConfirmedUsersFor(user.getEmail());
        verify(service, times(1)).rejectInvite(user.getEmail(), form.getEmail());
    }

    @Test
    void inviteResponse_invalidAction() {
        FriendResponseForm form = new FriendResponseForm();
        form.setAction("invalid");
        form.setEmail("invited@example.com");

        when(session.getAttribute("user")).thenReturn(user);
        when(userDAO.findAll()).thenReturn(Arrays.asList(user));

        String result = parkingPalsCtl.inviteResponse(form, model, session);

        verify(model, times(1)).addAttribute("users", Arrays.asList(user));
        verify(service, times(1)).getInvitesForUser(user.getEmail());
        verify(service, never()).rejectInvite(anyString(), anyString());
        assert (result.equals("error"));
    }
}
