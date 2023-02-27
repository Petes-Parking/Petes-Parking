package com.petesparkingmgt.ctl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;

@Controller
public class UserCtl {
	
	@Autowired
	public UserService service;
	
	@Autowired
	public UserDAO dao;

	@GetMapping("/signup")
	public String signupPage(@ModelAttribute("form") UserForm form) {
		
		return "registration";
	}
	
	@PostMapping("/addUser")
	public String signup(@ModelAttribute("form") UserForm form, Model model) {
		
	UserDTO user =	dao.findByEmail(form.getEmail());
	
	if(user == null) {
		UserDTO dto = form.getDTO();
		service.add(dto);
		model.addAttribute("success", "User registration success");
	}else {
		model.addAttribute("error", "Duplicate emails are not allow!");
	}
		
		
		return "registration";
		
	}
	
	
}