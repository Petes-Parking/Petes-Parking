package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.referrals.ReferralDAO;
import com.petesparkingmgt.dao.referrals.ReferralUserDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.objects.users.ReferralWrapper;
import com.petesparkingmgt.service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
public class ReferralsCtl {


    @Autowired
    public ReferralDAO dao;

    @Autowired
    public ReferralUserDAO userDAO;

    @Autowired
    public ReferralService referralService;

    @Autowired
    public UserDAO users;

    @GetMapping("/referrals")
    public String referralPage(Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }

        ReferralWrapper referralWrapper = referralService.getReferralThatUserUsedWhenRegistering(user.getId());
        ReferralDTO userCodes = dao.getReferralDTOByUserId(user.getId());
        model.addAttribute("referralCodes", userCodes);
        model.addAttribute("id", user.getId());
        if (userCodes != null) {
            model.addAttribute("studentsUsed", referralService.getNamesWhoUsedReferral(userCodes.getId()));
        }

        if (referralWrapper != null) {
            UserDTO creator = users.findById(referralWrapper.getReferral().getUserId());
            String creatorName = creator.getFirstName() + " " + creator.getLastName();
            model.addAttribute("creator", creatorName);
            model.addAttribute("usedCode", referralWrapper.getReferral().getCode());
        }




        return "referrals";
    }

    @GetMapping("/generateReferralCode")
    public ModelAndView generateReferralCode(HttpSession session, RedirectAttributes attributes) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/error");
        }
        ReferralDTO existingReferral = dao.findByUserId(user.getId());

        ModelAndView modelAndView = new ModelAndView("redirect:/referrals");

        if (existingReferral != null) {
            attributes.addFlashAttribute("error", "You already have an active referral!");
            return modelAndView;
        }

        String referralCode = generateRandomCode(8);
        ReferralDTO newReferral = new ReferralDTO();
        newReferral.setCode(referralCode);
        newReferral.setUserId(user.getId());
        newReferral.setUses(0);
        dao.save(newReferral);

        attributes.addFlashAttribute("message", "New referral code generated: " + referralCode);


        return modelAndView;
    }

    private String generateRandomCode(int length) {
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}