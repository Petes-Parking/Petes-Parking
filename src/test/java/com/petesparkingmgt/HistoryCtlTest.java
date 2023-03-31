package com.petesparkingmgt;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.petesparkingmgt.ctl.HistoryCtl;
import com.petesparkingmgt.dao.FavoriteDAO;
import com.petesparkingmgt.dao.HistoryDAO;
import com.petesparkingmgt.dto.FavoriteDTO;
import com.petesparkingmgt.dto.HistoryDTO;
import com.petesparkingmgt.dto.UserDTO;
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
public class HistoryCtlTest {

    @InjectMocks
    private HistoryCtl historyCtl;

    @Mock
    private HistoryDAO historyDAO;

    @Mock
    private FavoriteDAO favoriteDAO;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    private UserDTO user;

    @BeforeEach
    public void setUp() {
        user = new UserDTO();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setProfilePicture(new byte[0]);
    }

    @Test
    public void testHistoryPage() {
        when(session.getAttribute("user")).thenReturn(user);

        List<HistoryDTO> historyList = new ArrayList<>();
        when(historyDAO.getHistoryDTOSByUserId(user.getId())).thenReturn(historyList);

        List<FavoriteDTO> favoriteList = new ArrayList<>();
        when(favoriteDAO.getFavoriteDTOSByUserId(user.getId())).thenReturn(favoriteList);

        String result = historyCtl.HistoryPage(model, session);

        assertEquals("historyPage", result);
        verify(model).addAttribute("histories", new ArrayList<String>());
        verify(model).addAttribute("favorites", new ArrayList<String>());
        verify(model).addAttribute("username", user.getFirstName() + " " + user.getLastName());

        String base64Image = Base64.getEncoder().encodeToString(user.getProfilePicture());
        verify(model).addAttribute("profilePic", base64Image);
    }
}
