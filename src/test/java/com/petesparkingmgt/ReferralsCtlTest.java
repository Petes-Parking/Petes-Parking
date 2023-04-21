package com.petesparkingmgt;

import com.petesparkingmgt.ctl.ReferralsCtl;
import com.petesparkingmgt.dao.referrals.ReferralDAO;
import com.petesparkingmgt.dao.referrals.ReferralUserDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.service.ReferralService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReferralsCtlTest {

    @InjectMocks
    private ReferralsCtl referralsCtl;

    @Mock
    private ReferralDAO referralDAO;

    @Mock
    private ReferralUserDAO referralUserDAO;

    @Mock
    private ReferralService referralService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private Model model;

    private MockHttpSession session;
    private UserDTO user;

    @BeforeEach
    public void setUp() {
        session = new MockHttpSession();
        user = new UserDTO();
        user.setId(1);
        session.setAttribute("user", user);
    }

    @Test
    public void testReferralPage() {
        // Setup
        when(referralService.getReferralThatUserUsedWhenRegistering(user.getId())).thenReturn(null);
        when(referralDAO.getReferralDTOByUserId(user.getId())).thenReturn(null);

        // Execute
        String result = referralsCtl.referralPage(model, session);

        // Verify
        verify(model).addAttribute("referralCodes", null);
        verify(model).addAttribute("id", user.getId());
        verify(referralService, never()).getNamesWhoUsedReferral(anyInt());
        assert "referrals".equals(result);
    }

    @Test
    public void testGenerateReferralCode() {
        // Setup
        when(referralDAO.findByUserId(user.getId())).thenReturn(null);
        when(referralDAO.save(any(ReferralDTO.class))).thenReturn(new ReferralDTO());

        // Execute
        ModelAndView result = referralsCtl.generateReferralCode(session, redirectAttributes);

        // Verify
        verify(referralDAO).findByUserId(user.getId());
        verify(referralDAO).save(any(ReferralDTO.class));
        verify(redirectAttributes).addFlashAttribute(anyString(), anyString());
        assert "redirect:/referrals".equals(result.getViewName());
    }
}
