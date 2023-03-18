package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.form.CarpoolForm;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.CarpoolService;
import com.petesparkingmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CarpoolCtl {

    @Autowired
    public CarpoolService service;

    @Autowired
    public CarpoolDAO dao;

    @GetMapping("/carpool")
    public String carpool(@ModelAttribute("form") CarpoolForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }

        System.out.println("User: " + user.toString() + " on /carpool");
        CarpoolDTO carpool = dao.getCarpoolDTOByLeaderId(user.getId());
        model.addAttribute("carPoolName", "");

        if (carpool != null) {
            model.addAttribute("carpool", carpool);
            model.addAttribute("hasCarpool", true);
            model.addAttribute("carPoolName", carpool.getCarPoolName());

        } else {
            model.addAttribute("hasCarpool", false);
        }
        return "carpool";
    }


    @PostMapping("/createCarpool")
    public String createCarpool(@ModelAttribute("carform") CarpoolForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");

        model.addAttribute("carPoolName", "");
        model.addAttribute("hasCarpool", true);
        model.addAttribute("isLeader", true);


        System.out.println(user.toString() + " on /createCarpool");

        if (form.getDTO() != null) {
            CarpoolDTO dto = form.getDTO();
            dto.setLeaderId(user.getId());
            service.add(dto);
            model.addAttribute("carpool", dto);
            model.addAttribute("carPoolName", dto.getCarPoolName());

            model.addAttribute("messages", "New carpool created!");


            System.out.println("Created carpool DTO with name " + form.getCarPoolName() + " and leaderid " + dto.getLeaderId());
        } else {
            System.out.println("CarpoolDTO was null!");

        }
        return "carpool";
    }


    /**
     * Mapping for when a user leaves a carpool. If leader leaves, it disbands the entire carpool.
     * @param model
     * @param session
     * @return jsp to follow
     */
    @GetMapping("/leaveCarpool")
    public String leaveCarpool(@ModelAttribute("form") CarpoolForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        System.out.println(user.toString() + " on /leaveCarpool");

       // model.addAttribute("carPoolName", "");
        model.addAttribute("hasCarpool", false);
        model.addAttribute("messages", "You have left your carpool!");
        service.removeCarpoolFor(user);


        return "carpool";

    }

}
