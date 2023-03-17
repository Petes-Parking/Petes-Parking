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

@Controller
public class CarpoolCtl {

    @Autowired
    public UserService service;

    @Autowired
    public UserDAO dao;

    @GetMapping("/carpool")
    public String carpool(@ModelAttribute("form") UserForm form, Model model, HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }


        return "carpool";

    }
}
