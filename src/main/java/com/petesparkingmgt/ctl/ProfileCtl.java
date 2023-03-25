package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileCtl {

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);


        return "profilePage";
    }


}
