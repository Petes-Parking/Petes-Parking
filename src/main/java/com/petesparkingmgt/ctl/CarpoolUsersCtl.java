package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.CarpoolUsersDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.form.CarpoolAddMemberForm;
import com.petesparkingmgt.service.CarpoolUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CarpoolUsersCtl {

    @Autowired
    public CarpoolUsersService service;

    @Autowired
    public CarpoolUsersDAO dao;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public CarpoolDAO carpoolDAO;


    @PostMapping("/carpoolInvite")
    public String createCarpool(@ModelAttribute("carInviteForm") CarpoolAddMemberForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        CarpoolDTO carpool = carpoolDAO.getCarpoolDTOByLeaderId(user.getId());
        UserDTO toInvite = userDAO.findByEmail(form.getEmail());
        model.addAttribute("hasCarpool", true);
        model.addAttribute("carpool", carpool);
        model.addAttribute("carPoolName", carpool.getCarPoolName());
        model.addAttribute("isLeader", true);


        if (toInvite == null) {
            // not an existing user, tell user this
            model.addAttribute("errors", form.getEmail() + " is not a registered email!");
            model.addAttribute("carPoolName", carpool.getCarPoolName());

        } else {
            model.addAttribute("messages", "You have invited " + toInvite.getFirstName() + " " + toInvite.getLastName() + "!");
            CarpoolUserDTO dto = new CarpoolUserDTO();
            dto.setCarpoolId(carpool.getId());
            dto.setUserId(toInvite.getId());
            dao.save(dto);
        }
        return "carpool";

    }




    }
