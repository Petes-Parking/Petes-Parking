package com.petesparkingmgt.ctl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.service.UserService;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;


@Controller
public class LoginCtl {
	
	@Autowired
	public UserService service;
	
	@GetMapping("/login")
	public String loginPage() {
		return "home";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if(user != null) {
			session.invalidate();
			model.addAttribute("success", "Logout Sucessfully");
		}
		return "home";
	}
	
	

	@PostMapping("/auth")
	public String Login(@ModelAttribute("form") UserForm form, Model model, HttpSession session) {
       
	  UserDTO user = 	service.login(form.getEmail(), form.getPassword());
	  if(user == null) {
		  model.addAttribute("error", "Invalid username/password or register an account.");
	  }else {
		  session.setAttribute("user", user);
		  return "welcome";
	  }
		return "home";
	}
	
	

	

}
