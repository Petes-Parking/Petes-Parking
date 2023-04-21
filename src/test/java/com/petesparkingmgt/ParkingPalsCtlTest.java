package com.petesparkingmgt;

import com.petesparkingmgt.ctl.ParkingPalsCtl;
import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.users.FriendDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.FriendDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.service.FriendService;
import com.petesparkingmgt.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingPalsCtlTest {

    @InjectMocks
    private ParkingPalsCtl controller;

    @Mock
    private FriendService friendService;

    @Mock
    private FriendDAO friendDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private EmailPreferencesDAO emailPreferencesDAO;

    @Mock
    private NotificationService notificationService;

    private MockHttpSession session;
    private Model model;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        model = new ExtendedModelMap();
    }

    @Test
    void parkingPals_userNotAuthenticated_shouldReturnError() {
        String result = controller.parkingPals(model, session);
        assertEquals("error", result);
    }

//    @Test
//    void parkingPals_userAuthenticated_shouldReturnParkingPals() {
//        UserDTO user = new UserDTO();
//        user.setEmail("user@example.com");
//        user.setId(1L);
//        session.setAttribute("user", user);
//
//        List<UserDTO> users = Arrays.asList(mock(UserDTO.class), mock(UserDTO.class));
//        List<FriendDTO> requests = Arrays.asList(mock(FriendDTO.class), mock(FriendDTO.class));
//        List<FriendDTO> friends = Arrays.asList(mock(FriendDTO.class), mock(FriendDTO.class));
//        List<FriendDTO> outgoingRequests = Arrays.asList(mock(FriendDTO.class), mock(FriendDTO.class));
//
//        when(userDAO.findAll()).thenReturn(users);
//        when(friendService.getInvitesForUser(user.getEmail())).thenReturn(requests);
//        when(friendService.getConfirmedUsersFor(user.getEmail())).thenReturn(friends);
//        when(friendService.getOutgoingRequestsFor(user.getEmail())).thenReturn(outgoingRequests);
//
//        String result = controller.parkingPals(model, session);
//
//        assertEquals("parkingpals", result);
//        verify(userDAO).findAll();
//        verify(friendService).getInvitesForUser(user.getEmail());
//        verify(friendService).getConfirmedUsersFor(user.getEmail());
//        verify(friendService).getOutgoingRequestsFor(user.getEmail());
//        verify(notificationService).addNotifications(eq(model), eq(notificationService), eq(user.getId()));
//    }
}
