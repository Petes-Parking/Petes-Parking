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
    public String leaderboard(Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            return "error";
        }



        List<UserDTO> users = dao.getAllByUserRole("Student");

        users.sort((o1, o2) -> o2.getPoints() - o1.getPoints()); // Sorts list in decreasing order
        model.addAttribute("leaderboardList", users);


        return "leaderboard";
    }


}
