package com.petesparkingmgt.ctl;


import com.petesparkingmgt.dao.ExpReportDAO;
import com.petesparkingmgt.dao.PoorParkReportDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.PoorParkReportDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.BookingService;
import com.petesparkingmgt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@Controller
public class AdminCtl {

    @Autowired
    public UserService service;
    
    @Autowired
    public BookingService service2;

    @Autowired
    public UserDAO dao;

    @Autowired
    public ExpReportDAO expDAO;

    @Autowired
    public PoorParkReportDAO poorParkDAO;


    @GetMapping("/adminview")
    public String adminPage(Model model) {


        List<UserDTO> users = dao.getAllByUserRole("Student");
        users.forEach(userDTO -> System.out.println(userDTO.getEmail() + " " + userDTO.getUserRole()));

        model.addAttribute("adminUserList", users);


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
    public ModelAndView updateUser(@ModelAttribute UserDTO user, RedirectAttributes attributes) {
        // Update the user information in the database using the userService
        service.update(user);

        // Redirect back to the admin view page with an updated users list
        ModelAndView modelAndView = new ModelAndView("redirect:/adminview");
        attributes.addFlashAttribute("message", "User updated successfully!");

        return modelAndView;
    }

    @PostMapping("/admin/deleteUser")
    public ModelAndView deleteUser(@RequestParam("selectedUserId") Long userId, RedirectAttributes attributes) {
        service.deleteUser(userId);

        ModelAndView modelAndView = new ModelAndView("redirect:/adminview");
        attributes.addFlashAttribute("message", "User deleted successfully!");

        return modelAndView;
    }

    @PostMapping("/admin/givePermission")
    public ModelAndView givePermissions(@RequestParam("selectedUserId") Long userId, RedirectAttributes attributes) {
        service.giveAdminPermission(userId);

        ModelAndView modelAndView = new ModelAndView("redirect:/adminview");
        attributes.addFlashAttribute("message", "Successfully given admin permissions!");

        return modelAndView;
    }

    @GetMapping("/admin/review-exp")
    public String adminReviewExp(Model model) {
        List<ExpReportDTO> exps = expDAO.findAll();
        model.addAttribute("adminExpList", exps);

        return "adminExpReport";
    }

    @GetMapping("/admin/review-poorpark")
    public String adminReviewPoorPark(Model model) {
        List<PoorParkReportDTO> poorParks = poorParkDAO.findAll();
        model.addAttribute("adminPoorParkList", poorParks);

        return "adminPoorParkReport";
    }

    @GetMapping("/admin/review-exp/{expReportID}")
    public String adminReviewExpDetailed(@PathVariable("expReportID") Long expReportID, Model model) {

        ExpReportDTO expReport = expDAO.getById(expReportID);

        model.addAttribute("report", expReport);
        System.out.println(expReport);
        return "adminReviewExpDetailed";
    }
    
	@GetMapping("/bookinglist")
	public String list(@ModelAttribute("form")BookingForm form, Model model, HttpSession session){
		List<BookingDTO> list = null;
		UserDTO user = (UserDTO) session.getAttribute("user");
		String email  = user.getEmail();
		System.out.println("Booking list email: "+email);
		if(user.getUserRole().equals("Admin")) {
			 list = service2.list();
		}else {
			list = service2.findBookingByEmail(email);
		}
	model.addAttribute("list", list);
	return "bookinglist";
		
	}

    @GetMapping("/admin/review-poorpark/{poorParkReportID}")
    public String adminReviewPoorParkDetailed(@PathVariable("poorParkReportID") Long poorParkReportID, Model model) {

        PoorParkReportDTO poorParkReport = poorParkDAO.getById(poorParkReportID);

        model.addAttribute("report", poorParkReport);
        System.out.println(poorParkReport);
        return "adminReviewExpDetailed";
    }
}
