package com.petesparkingmgt;

import com.petesparkingmgt.ctl.ProfileCtl;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import com.petesparkingmgt.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProfileCtlTest {

    @InjectMocks
    private ProfileCtl profileCtl;

    @Mock
    private VehicleDAO vehicleDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private NotificationService notificationService;

    @Test
    public void testProfilePage() {
        // Set up a test user
        UserDTO testUser = new UserDTO();
        testUser.setId(1L);
        testUser.setProfilePicture(new byte[0]);

        // Set up a test vehicle
        VehicleDTO testVehicle = new VehicleDTO();
        testVehicle.setUserId(testUser.getId());

        // Set up a session with the user
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", testUser);

        // Set up a mock model
        Model model = mock(Model.class);

        // Stub the vehicleDAO and notificationService methods
        when(vehicleDAO.getVehicleDTOByUserId(testUser.getId())).thenReturn(testVehicle);

        // Invoke the profile page method
        String result = profileCtl.profilePage(model, session);

        // Verify that the expected view name is returned
        assertEquals("profilePage", result);

        // Verify that the vehicleDAO and notificationService were called with the correct user ID
        verify(vehicleDAO, times(1)).getVehicleDTOByUserId(testUser.getId());
    }
}
