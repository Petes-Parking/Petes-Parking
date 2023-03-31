package com.petesparkingmgt.ctl;



import com.petesparkingmgt.dto.PendingUserDTO;
import com.petesparkingmgt.service.PendingUserService;
import com.petesparkingmgt.utility.DataUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.UserService;

import java.time.LocalDate;
import java.util.List;



@Controller
@RequestMapping("/user")
public class UserCtl {

	@Autowired
	public UserService service;

	@Autowired
	public PendingUserService pendingUserService;

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
			model.addAttribute("password2", form.getPassword2());

			model.addAttribute("firstName", form.getFirstName());
			model.addAttribute("lastName", form.getLastName());
			model.addAttribute("phoneNumber", form.getPhoneNumber());
			model.addAttribute("dob", form.getDob());
		} else {

			model.addAttribute("email", "email");
			model.addAttribute("gender", "gender");
			model.addAttribute("password", "password");
			model.addAttribute("password2", "password2");

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

	@GetMapping("/resetpassword")
	public String resetPasswordPage(@ModelAttribute("form") UserForm form) { return "resetpassword"; }

	@PostMapping("/addUser")
	public String signup(@ModelAttribute("form") UserForm form, Model model) {

		UserDTO user =	dao.findByEmail(form.getEmail());
		PendingUserDTO pendingUserDTO =	pendingUserService.getPendingUser(form.getEmail());

		if (form.getEmail() != null) {

			System.out.println(form.toString() + "-bang");
			model.addAttribute("email", form.getEmail());
			model.addAttribute("gender", form.getGender());
			model.addAttribute("password", form.getPassword());
			model.addAttribute("password2", form.getPassword2());

			model.addAttribute("firstName", form.getFirstName());
			model.addAttribute("lastName", form.getLastName());
			model.addAttribute("phoneNumber", form.getPhoneNumber());
			model.addAttribute("dob", form.getDob());

			if(form.getPassword().length() < 8) {
				model.addAttribute("error", "Password needs at least 8 characters!");
				return "register";
			}

			if (!form.getPassword().equals(form.getPassword2())) {
				System.out.println("Passwords not equal!");
				model.addAttribute("error", "Passwords do not match!");
				return "register";
			}
			if (form.getDob().equals("") || form.getFirstName().equals("") || form.getLastName().equals("") ||
					form.getPhoneNumber().equals("") ){
				model.addAttribute("error", "Please enter all fields!");
				return "register";
			}

			if (!DataUtility.containsUpperCaseLetter(form.getPassword())) {
				model.addAttribute("error", "Password must have at least one uppercase character");
				return "register";
			}
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
			model.addAttribute("password2", "password2");

			model.addAttribute("firstName", "firstName");
			model.addAttribute("lastName", "lastName");
			model.addAttribute("phoneNumber", "pnum");
			model.addAttribute("dob", "dobb");
		}

		if(user == null && pendingUserDTO == null) {

			PendingUserDTO dto = new PendingUserDTO();
			dto.setDob(form.getDob());
			dto.setGender(form.getGender());
			dto.setEmail(form.getEmail());
			dto.setPassword(form.getPassword());
			dto.setPassword2(form.getPassword2());
			dto.setPhoneNumber(form.getPhoneNumber());
			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setUserRole(form.getUserRole());

			//dto.setStatus("InActive");
			pendingUserService.add(dto);

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
	
	@GetMapping("/userApprove")	
	public String userApprove(Model model, @RequestParam("id") long id ) throws Exception{
		pendingUserService.acceptedUser(id);
		
		List<PendingUserDTO> list = pendingUserService.list();
		model.addAttribute("list", list);	
		model.addAttribute("success", "User Approved Successfully!");
		return "userListView";
	}
	
	@GetMapping("/userReject")	
	public String userReject(Model model, @RequestParam("id") long id ) throws Exception{
		pendingUserService.rejectUser(id);
		
		List<PendingUserDTO> list =	pendingUserService.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "User Rejected successfully");
		return "userListView";
	}

}