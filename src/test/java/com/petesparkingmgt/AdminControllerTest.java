package com.petesparkingmgt;

import com.petesparkingmgt.ctl.AdminCtl;
import com.petesparkingmgt.dao.parking.ParkingDAO;
import com.petesparkingmgt.dao.reports.ExpReportDAO;
import com.petesparkingmgt.dao.reports.PoorParkReportDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.parking.ParkingDTO;
import com.petesparkingmgt.dto.reports.ExpReportDTO;
import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import com.petesparkingmgt.dto.user.PendingUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminCtl adminCtl;

    @Mock
    private UserService userService;

    @Mock
    private PendingUserService pendingUserService;

    @Mock
    private PermissionService permissionService;

    @Mock
    private BookingService bookingService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ExpReportDAO expReportDAO;

    @Mock
    private PoorParkReportDAO poorParkReportDAO;

    @Mock
    private ParkingDAO parkingDAO;

    @Mock
    private NotificationService notificationService;

    @Mock
    private Model model;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminCtl).build();
    }

    @Test
    public void testAdminPage() throws Exception {
        List<UserDTO> userList = Arrays.asList(new UserDTO(), new UserDTO());

        when(userDAO.findAll()).thenReturn(userList);

        mockMvc.perform(get("/adminview"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminview"));

        verify(userDAO, times(1)).findAll();
    }

    @Test
    public void testAdminReportsPage() throws Exception {
        List<ExpReportDTO> expReportList = Arrays.asList(new ExpReportDTO(), new ExpReportDTO());
        List<PoorParkReportDTO> poorParkReportList = Arrays.asList(new PoorParkReportDTO(), new PoorParkReportDTO());

        when(expReportDAO.findAll()).thenReturn(expReportList);
        when(poorParkReportDAO.findAll()).thenReturn(poorParkReportList);

        mockMvc.perform(get("/adminreports"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminreports"));

        verify(expReportDAO, times(1)).findAll();
        verify(poorParkReportDAO, times(1)).findAll();
    }

    @Test
    public void testAdminPendingUsersPage() throws Exception {
        List<PendingUserDTO> pendingUserList = Arrays.asList(new PendingUserDTO(), new PendingUserDTO());

        when(pendingUserService.list()).thenReturn(pendingUserList);

        mockMvc.perform(get("/adminpendingusers"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminpendingusers"));

        verify(pendingUserService, times(1)).list();
    }

    @Test
    public void testAdminBookingPage() throws Exception {
        List<BookingDTO> bookingList = Arrays.asList(new BookingDTO(), new BookingDTO());

        when(bookingService.list()).thenReturn(bookingList);

        mockMvc.perform(get("/adminbookings"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminbookings"));

        verify(bookingService, times(1)).list();
    }

    @Test
    public void testAdminParkingPage() throws Exception {
        List<ParkingDTO> parkingList = Arrays.asList(new ParkingDTO(), new ParkingDTO());

        when(parkingDAO.findAll()).thenReturn(parkingList);

        mockMvc.perform(get("/adminparking"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminparking"));

        verify(parkingDAO, times(1)).findAll();
    }



}
