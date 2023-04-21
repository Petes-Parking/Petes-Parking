package com.petesparkingmgt;

import com.petesparkingmgt.ctl.SettingsCtl;
import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dto.user.EmailPreferencesDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.UserForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SettingsCtlTest {

    @InjectMocks
    private SettingsCtl settingsCtl;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private EmailPreferencesDAO emailPreferencesDAO;

    private UserDTO user;

    @BeforeEach
    public void setUp() {
        user = new UserDTO();
        user.setId(1L);
        when(session.getAttribute("user")).thenReturn(user);
    }

    @Test
    public void testSettingsPage() {
        // Setup
        EmailPreferencesDTO emailPreferencesDTO = new EmailPreferencesDTO();
        when(emailPreferencesDAO.getByUserID(user.getId())).thenReturn(emailPreferencesDTO);

        // Execute
        String result = settingsCtl.settingsPage(new UserForm(), model, session);

        // Verify
        verify(emailPreferencesDAO, times(2)).getByUserID(user.getId()); // Change this line
        verify(model, atLeastOnce()).addAttribute(anyString(), any());
        assert "settings".equals(result);
    }

    @Test
    public void testSaveEmailSettings() {
        // Setup
        boolean palPref = true;
        boolean repPref = true;
        boolean expPref = false;
        Integer timerInput = 30;

        // Execute
        String result = settingsCtl.saveEmailSettings(palPref, repPref, expPref, timerInput, session, model);

        // Verify
        verify(session).getAttribute("user");
        verify(emailPreferencesDAO).getByUserID(user.getId());
        verify(emailPreferencesDAO).save(any(EmailPreferencesDTO.class));
        verify(model, atLeastOnce()).addAttribute(anyString(), any());
        assert "redirect:/settings".equals(result);
    }
}
