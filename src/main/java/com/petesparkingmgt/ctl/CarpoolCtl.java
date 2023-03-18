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
        CarpoolDTO carpool = dao.getCarpoolDTOById(user.getId());
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
    public String createCarpool(@ModelAttribute("form") CarpoolForm form, Model model) {
        model.addAttribute("carPoolName", "");

        System.out.println("Created carpool named: " + form.getCarpoolName());
        System.out.println("Created carpool named (model): " + model.getAttribute("carPoolName"));
        model.addAttribute("carPoolName", "");
        System.out.println("Created carpool named (model): " + model.getAttribute("carPoolName"));


        if (form.getDTO() != null) {
            service.add(form.getDTO());
            System.out.println("Created carpool DTO with name " + form.getCarpoolName());
        } else {
            System.out.println("CarpoolDTO was null!");

        }
        return "carpool";
    }

}
