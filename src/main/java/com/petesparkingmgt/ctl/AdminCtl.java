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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminCtl {

    @Autowired
    public UserService service;

    @Autowired
    public UserDAO dao;


    @GetMapping("/adminview")
    public String adminPage(@ModelAttribute("form") UserForm form, Model model) {


        List<UserDTO> users = dao.findAll();
        model.addAttribute("email", "email");

        model.addAttribute("adminUserList", users);


        users.stream().map(UserDTO::getFirstName).collect(Collectors.toList());
        return "adminview";
    }

    @GetMapping("/admin/edit/{userId}")
    public String adminEdit(@PathVariable("userId") Long userId, Model model) {
        // Retrieve the user by the userId from the database
        UserDTO user = service.findUserById(userId);

        // Add the user object to the model
        model.addAttribute("user", user);

        // Return the view name for the edit user page
        return "adminEditUser";
    }

    @PostMapping("/admin/update")
    public ModelAndView updateUser(@ModelAttribute UserDTO user) {
        // Update the user information in the database using the userService
        service.update(user);

        // Redirect back to the admin view page with an updated users list
        ModelAndView modelAndView = new ModelAndView("redirect:/adminview");
        return modelAndView;
    }

}
