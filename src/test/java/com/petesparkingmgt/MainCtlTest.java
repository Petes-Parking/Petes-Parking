package com.petesparkingmgt;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.petesparkingmgt.ctl.MainCtl;
import com.petesparkingmgt.dao.users.FavoriteDAO;
import com.petesparkingmgt.dto.user.FavoriteDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.FavoriteForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class MainCtlTest {

    @InjectMocks
    private MainCtl mainCtl;

    @Mock
    private FavoriteDAO favoriteDAO;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

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
    public void testMainPage() {
        when(session.getAttribute("user")).thenReturn(user);

        List<FavoriteDTO> favoriteList = new ArrayList<>();
        when(favoriteDAO.getFavoriteDTOSByUserId(user.getId())).thenReturn(favoriteList);

        String result = mainCtl.MainPage(model, session);

        assertEquals("mainPage", result);
        verify(model).addAttribute("favorites", new ArrayList<String>());

        String base64Image = Base64.getEncoder().encodeToString(user.getProfilePicture());
        verify(model).addAttribute("profilePic", base64Image);
    }

    @Test
    public void testAddFavorite() {
        when(session.getAttribute("user")).thenReturn(user);

        List<FavoriteDTO> favoriteList = new ArrayList<>();
        when(favoriteDAO.getFavoriteDTOSByUserId(user.getId())).thenReturn(favoriteList);

        FavoriteForm form = new FavoriteForm();
        form.setArea("Test Area");

        String result = mainCtl.Add(form, bindingResult, model, session);

        assertEquals("redirect:/main", result);
        verify(favoriteDAO).save(any(FavoriteDTO.class));
    }
}
