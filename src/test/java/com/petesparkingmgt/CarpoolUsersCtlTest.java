package com.petesparkingmgt;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.petesparkingmgt.ctl.CarpoolUsersCtl;
import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.form.CarpoolAddMemberForm;
import com.petesparkingmgt.form.CarpoolResponseForm;
import com.petesparkingmgt.service.CarpoolService;
import com.petesparkingmgt.service.CarpoolUsersService;
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
public class CarpoolUsersCtlTest {

    @InjectMocks
    private CarpoolUsersCtl carpoolUsersCtl;

    @Mock
    private CarpoolUsersService service;

    @Mock
    private CarpoolService carpoolService;

    @Mock
    private CarpoolUsersDAO dao;

    @Mock
    private UserDAO userDAO;

    @Mock
    private CarpoolDAO carpoolDAO;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    private UserDTO user;
    private CarpoolDTO carpool;

    @BeforeEach
    public void setUp() {
        user = new UserDTO();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        carpool = new CarpoolDTO();
        carpool.setId(1L);
        carpool.setLeaderId(user.getId());
        carpool.setCarPoolName("Test Carpool");
    }

    @Test
    public void testCreateCarpool() {
        when(session.getAttribute("user")).thenReturn(user);
        when(carpoolDAO.getCarpoolDTOByLeaderId(user.getId())).thenReturn(carpool);

        CarpoolAddMemberForm form = new CarpoolAddMemberForm("jane.doe@example.com");

        UserDTO toInvite = new UserDTO();
        toInvite.setId(2L);
        toInvite.setFirstName("Jane");
        toInvite.setLastName("Doe");
        toInvite.setEmail("jane.doe@example.com");

        when(userDAO.findByEmail(form.getEmail())).thenReturn(toInvite);

        String result = carpoolUsersCtl.createCarpool(form, model, session);

        assertEquals("carpool", result);
        verify(dao).save(any(CarpoolUserDTO.class));
    }

    @Test
    public void testInviteResponse() {
        when(session.getAttribute("user")).thenReturn(user);
        when(carpoolDAO.getCarpoolDTOByCarPoolName(anyString())).thenReturn(carpool);

        CarpoolResponseForm form = new CarpoolResponseForm();
        form.setCarPoolName(carpool.getCarPoolName());
        form.setAction("accept");

        when(service.getCarpoolFor(user.getId())).thenReturn(null);

        String result = carpoolUsersCtl.inviteResponse(form, model, session);

        assertEquals("carpool", result);
        verify(service).acceptInvite(carpool, user.getId());
    }
}
