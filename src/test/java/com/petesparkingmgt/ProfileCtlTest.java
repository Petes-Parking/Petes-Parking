package com.petesparkingmgt;

import com.petesparkingmgt.ctl.ProfileCtl;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProfileCtl.class)
public class ProfileCtlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDAO userDAO;

    @MockBean
    private VehicleDAO vehicleDAO;

    @Test
    public void testProfilePage() throws Exception {
        // Mock session user
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        byte[] dummyImageData = new byte[]{1, 2, 3, 4, 5};
        user.setProfilePicture(dummyImageData);



        // Mock the userDAO response
        when(userDAO.findByEmail(any())).thenReturn(user);

        // Mock the vehicleDAO response
        when(vehicleDAO.getVehicleDTOByUserId(anyLong())).thenReturn(new VehicleDTO());

        mockMvc.perform(get("/profile").sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "vehicle", "profilePic", "username", "nextLevelPoints"))
                .andExpect(view().name("profilePage"));
    }

    // Add more test methods for addVehicle and addProfilePic endpoints
}
