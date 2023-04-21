package com.petesparkingmgt;

import com.petesparkingmgt.ctl.BookingCtl;
import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.parking.BookingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dao.users.HistoryDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.parking.SlotDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.BookingPaymentForm;
import com.petesparkingmgt.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingCtl.class)
public class FutureBookingCtlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SlotDAO slotDAO;

    @MockBean
    private BookingDAO bookingDAO;

    @MockBean
    private BookingService service;

    @MockBean
    private VehicleDAO vehicleDAO;

    @MockBean
    private UserService userService;

    @MockBean
    private HistoryService historyService;

    @MockBean
    private ParkingService parkingService;

    @MockBean
    private CarpoolUsersService carpoolUsersService;

    @MockBean
    private CarpoolService carpoolService;

    @MockBean
    private HistoryDAO historyDAO;

    @MockBean
    private EmailPreferencesDAO emailDAO;

    @MockBean
    private PaymentRequestService paymentRequestService;

    @Test
    public void testConfirmPayment() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setPoints(100);

        BookingPaymentForm form = new BookingPaymentForm();
        form.setCost(10);

        BookingDTO booking = new BookingDTO();
        booking.setSlotId(1);
        form.setDTO(booking);

        SlotDTO slot = new SlotDTO();
        slot.setId(1);

        when(slotDAO.findById(1)).thenReturn(slot);
        when(userService.update(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/booking/confirmPayment")
                        .flashAttr("bookingForm", form)
                        .sessionAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bookinglist"));

        verify(slotDAO, times(1)).findById(1);
        verify(service, times(1)).Add(any(BookingDTO.class));
        verify(userService, times(1)).update(any(UserDTO.class));
    }
}
