package com.petesparkingmgt.ctl;

import javax.servlet.http.HttpSession;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.service.PendingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.service.UserService;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.UserForm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class LoginCtl {

	@Autowired
	public UserService service;

	@Autowired
	public PendingUserService pendingUserService;

	@Autowired
	public UserDAO dao;

	@GetMapping("/login")
	public String loginPage() {
		return "home";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user != null) {
			session.invalidate();
			model.addAttribute("success", "Logout Sucessfully");
		}
		return "home";
	}

	@PostMapping("/auth")
	public String Login(@ModelAttribute("form") UserForm form, Model model, HttpSession session) {
		UserDTO user = service.login(form.getEmail(), form.getPassword());

		if (user.getProfilePicture() == null || user.getProfilePicture().length == 0) {
			user.setProfilePicture(getDefaultProfilePicture());
			service.update(user);
		}


		if (user == null) {
			if (pendingUserService.getPendingUser(form.getEmail()) != null) {
				model.addAttribute("error", "Admin must approve your account!");

			} else {
				model.addAttribute("error", "Invalid username/password or register an account.");
			}
		} else {
			if (user.getUserRole().equals("Admin")) {
				session.setAttribute("user", user);
				List<UserDTO> users = dao.findAll();
				model.addAttribute("email", "email");

				model.addAttribute("adminUserList", users);

				return "adminview";

			} else  {
				session.setAttribute("user", user);
				return "redirect:/main";
			}
		}
		return "home";
	}


	public byte[] getDefaultProfilePicture() {
		String defaultProfilePicturePath = "src/main/webapp/resources/image/profile-pic.png";
		Path path = Paths.get(defaultProfilePicturePath);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}