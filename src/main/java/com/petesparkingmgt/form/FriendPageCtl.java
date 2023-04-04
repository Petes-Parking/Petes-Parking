package com.petesparkingmgt.form;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.UserDTO;

import javax.servlet.http.HttpSession;
import java.lang.*;

@Controller
public class FriendPageCtl {

    @Autowired
    public UserDAO userDAO;

    @PostMapping("/friendpage")
    public String friendPage(@ModelAttribute("viewFriendForm") ViewFriendForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) return "error";

        UserDTO myFriend = userDAO.findByEmail(form.getEmail());
        model.addAttribute("myFriend", myFriend);

        return "friendpage";
    }
}
