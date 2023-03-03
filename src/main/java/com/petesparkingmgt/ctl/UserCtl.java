package com.petesparkingmgt.ctl;



import com.petesparkingmgt.utility.DataUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;

import java.time.LocalDate;
import java.util.List;



@Controller
public class UserCtl {
	
	@Autowired
	public UserService service;
	
	@Autowired
	public UserDAO dao;

	@GetMapping("/signup")
	public String signupPage(@ModelAttribute("form") UserForm form, Model model) {
		UserDTO user =	dao.findByEmail(form.getEmail());

		if (form.getEmail() != null) {
			System.out.println(form.toString() + " -------");
			model.addAttribute("email", form.getEmail());
			model.addAttribute("gender", form.getGender());
			model.addAttribute("password", form.getPassword());
			model.addAttribute("firstName", form.getFirstName());
			model.addAttribute("lastName", form.getLastName());
			model.addAttribute("phoneNumber", form.getPhoneNumber());
			model.addAttribute("dob", form.getDob());
		} else {

		model.addAttribute("email", "email");
		model.addAttribute("gender", "gender");
		model.addAttribute("password", "password");
		model.addAttribute("firstName", "firstName");
		model.addAttribute("lastName", "lastName");
		model.addAttribute("phoneNumber", "pnum");
		model.addAttribute("dob", "dobb");
		}





		return "register";
	}



	@GetMapping("/forgotpassword")
	public String forgotPasswordPage(@ModelAttribute("form") UserForm form) {
		return "forgotpassword";
	}
	
	@PostMapping("/addUser")
	public String signup(@ModelAttribute("form") UserForm form, Model model) {
		
	UserDTO user =	dao.findByEmail(form.getEmail());
		if (form.getEmail() != null) {

			System.out.println(form.toString() + "-bang");
			model.addAttribute("email", form.getEmail());
			model.addAttribute("gender", form.getGender());
			model.addAttribute("password", form.getPassword());
			model.addAttribute("firstName", form.getFirstName());
			model.addAttribute("lastName", form.getLastName());
			model.addAttribute("phoneNumber", form.getPhoneNumber());
			model.addAttribute("dob", form.getDob());

			if (!DataUtility.isAbove16(form.getDob())) {
				System.out.println("Minor detected!");
				model.addAttribute("error", "You must be at least 16 to use!");
				return "register";
			}


		} else {

			System.out.println("Boo");
			model.addAttribute("email", "email");
			model.addAttribute("gender", "gender");
			model.addAttribute("password", "password");
			model.addAttribute("firstName", "firstName");
			model.addAttribute("lastName", "lastName");
			model.addAttribute("phoneNumber", "pnum");
			model.addAttribute("dob", "dobb");
		}

		if(user == null) {

		UserDTO dto = form.getDTO();
		service.add(dto);

		model.addAttribute("success", "User registration success");
	}else {
		model.addAttribute("error", "Duplicate emails are not allowed!");
	}
		
	
		
		return "register";
		
	}

	@PostMapping("/map")
	public String mapPage(@ModelAttribute("form") UserForm form, Model model) {

		return "welcome";
	}
	

}
