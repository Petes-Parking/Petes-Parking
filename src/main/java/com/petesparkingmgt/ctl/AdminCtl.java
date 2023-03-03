package com.petesparkingmgt.ctl;


import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/viewuser")
    public String adminViewPage(@ModelAttribute("form") UserForm form, Model model){

        System.out.println("Dates--" + form.getDTO().getBdate1());
        if (form.getDTO().getBdate1() != null) {
            model.addAttribute("dateList", form.getDTO().getBdate1());
            model.addAttribute("datesList", form.getDTO().getBdate2());
            model.addAttribute("datesList", form.getDTO().getBdate3());
        } else {
            model.addAttribute("dateList", "No history yet!");

        }

        return "adminviewhistory";
    }


}
