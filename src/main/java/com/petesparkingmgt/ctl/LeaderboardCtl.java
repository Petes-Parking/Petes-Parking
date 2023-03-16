package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

@Controller
public class LeaderboardCtl {

    @Autowired
    public UserService service;

    @Autowired
    public UserDAO dao;


    @GetMapping("/leaderboard")
    public String leaderboard(@ModelAttribute("form") UserForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            return "error";
        }
        // Removing the below code breaks, not exactly sure why, very janky, I know. Need to really dig into to determine
        // why
        model.addAttribute("email", "t");
        model.addAttribute("gender", "gender");
        model.addAttribute("password", "password");
        model.addAttribute("password2", "password2");

        model.addAttribute("firstName", "firstName");
        model.addAttribute("lastName", "lastName");
        model.addAttribute("phoneNumber", "pnum");
        model.addAttribute("dob", "dobb");


        List<UserDTO> users = dao.findAll();

        users.sort((o1, o2) -> o2.getPoints() - o1.getPoints()); // Sorts list in decreasing order
        model.addAttribute("leaderboardList", users);


        return "leaderboard";
    }


}
