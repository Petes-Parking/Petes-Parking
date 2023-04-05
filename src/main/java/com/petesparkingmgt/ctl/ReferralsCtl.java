package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.referrals.ReferralDAO;
import com.petesparkingmgt.dao.referrals.ReferralUserDAO;
import com.petesparkingmgt.dto.referrals.ReferralDTO;
import com.petesparkingmgt.dto.referrals.ReferralUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReferralsCtl {


    @Autowired
    public ReferralDAO dao;

    @Autowired
    public ReferralUserDAO userDAO;

    @GetMapping("/referrals")
    public String referralPage(Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }

        List<ReferralDTO> userCodes = dao.getReferralDTOSByUserId(user.getId());
        model.addAttribute("referralCodes", userCodes);
        


        return "referrals";
    }
}
