package com.petesparkingmgt.ctl;


import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminCtl {

    @Autowired
    public UserService service;

    @Autowired
    public UserDAO dao;


    @GetMapping("/adminview")
    public String adminPage(@ModelAttribute("form") UserForm form, Model model) {


        model.addAttribute("email", "email");
        return "adminview";
    }

    @GetMapping("/viewuser")
    public String adminViewPage(@ModelAttribute("form") UserForm form, Model model){
        return "";
    }


}
