package com.petesparkingmgt;

import com.petesparkingmgt.ctl.MainCtl;
import com.petesparkingmgt.dao.users.FavoriteDAO;
import com.petesparkingmgt.dto.user.FavoriteDTO;
import com.petesparkingmgt.dto.user.NotificationDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.FavoriteForm;
import com.petesparkingmgt.service.NotificationService;
import com.petesparkingmgt.service.ParkingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainCtlTest {

    @InjectMocks
    private MainCtl mainCtl;

    @Mock
    private FavoriteDAO favoriteDAO;

    @Mock
    private ParkingService parkingService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    private UserDTO user;

    @Before
    public void setUp() {
        user = new UserDTO();
        user.setId(1L);
        user.setProfilePicture(new byte[]{0, 1, 2, 3, 4}); // Add this line to set a dummy array of bytes

    }


    @Test
    public void testAdd() {
        // Arrange
        FavoriteForm form = new FavoriteForm();
        form.setArea("Test Area");
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        when(session.getAttribute("user")).thenReturn(user);
        List<FavoriteDTO> favoriteList = new ArrayList<>();
        when(favoriteDAO.getFavoriteDTOSByUserId(user.getId())).thenReturn(favoriteList);

        // Act
        String result = mainCtl.Add(form, bindingResult, model, session);

        // Assert
        assertEquals("redirect:/main", result);
        verify(favoriteDAO).save(any(FavoriteDTO.class));
    }


    @Test
    public void testNotifications() {
        // Set up a test user
        UserDTO testUser = new UserDTO();
        testUser.setId(1L);
        testUser.setProfilePicture(new byte[0]);

        // Set up a non-null list of notifications for the user
        List<NotificationDTO> notifications = new ArrayList<>();
        notifications.add(new NotificationDTO());

        // Set up a session with the user
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", testUser);

        // Set up a mock model
        Model model = mock(Model.class);

        // Invoke the main page method
        mainCtl.MainPage(model, session);



        // Verify that the notifications were added to the model
        verify(model, times(1)).addAttribute(eq("notifications"), anyList());
    }
}
