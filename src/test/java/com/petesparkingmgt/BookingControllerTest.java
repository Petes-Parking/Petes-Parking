package com.petesparkingmgt;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.petesparkingmgt.ctl.BookingCtl;
import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dao.users.HistoryDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @InjectMocks
    private BookingCtl bookingController;

    @Mock
    private CarpoolUsersService carpoolUsersService;

    @Mock
    private CarpoolService carpoolService;

    @Mock
    private BookingService service;

    @Mock
    private VehicleDAO vehicleDAO;

    @Mock
    private ParkingService parkingService;

    @Mock
    private HistoryDAO historyDAO;

    @Mock
    private SlotDAO slotDAO;

    @Mock
    private EmailPreferencesDAO emailDAO;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

    private BookingForm form;
    private UserDTO user;

    @BeforeEach
    public void setUp() {
        form = new BookingForm();
        user = new UserDTO();
    }

    // Test cases go here
    @Test
    public void addBooking_withErrors_shouldReturnBookingView() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = bookingController.Add(form, bindingResult, model, session);

        assertEquals("booking", result);
    }

    @Test
    public void addBooking_userInCarpoolButNotLeader_shouldReturnBookingViewWithError() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(session.getAttribute("user")).thenReturn(user);
        when(carpoolUsersService.isInCarpoolButNotLeader(user.getId())).thenReturn(true);

        String result = bookingController.Add(form, bindingResult, model, session);

        assertEquals("booking", result);
        verify(model).addAttribute(eq("error"), anyString());
    }






}
