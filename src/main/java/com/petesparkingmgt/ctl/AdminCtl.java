package com.petesparkingmgt.ctl;


import com.petesparkingmgt.dao.ExpReportDAO;
import com.petesparkingmgt.dao.PoorParkReportDAO;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.*;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.form.ManagePointForm;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.BookingService;
import com.petesparkingmgt.service.PendingUserService;
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
    public PendingUserService pendingUserService;
    
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

        for (int i = 0; i < exps.size(); i++) {
            ExpReportDTO current = exps.get(i);
            if (current.getDescription() == null || current.getDescription().equals("")) {
                current.setDescription("[Description not provided]");
            }
            if (current.getParkingLot() == null || current.getParkingLot().equals("")) {
                current.setParkingLot(("[Parking lot not provided]"));
            }
        }

        model.addAttribute("adminExpList", exps);

        return "adminExpReport";
    }

    @GetMapping("/admin/review-poorpark")
    public String adminReviewPoorPark(Model model) {
        List<PoorParkReportDTO> poorParks = poorParkDAO.findAll();

        for (int i = 0; i < poorParks.size(); i++) {
            PoorParkReportDTO current = poorParks.get(i);

            if (current.getDescription() == null || current.getDescription().equals("")) {
                current.setDescription("[Description not provided]");
            }
            if (current.getParkingLot() == null || current.getParkingLot().equals("")) {
                current.setParkingLot(("[Parking lot not provided]"));
            }
        }

        model.addAttribute("adminPoorParkList", poorParks);

        return "adminPoorParkReport";
    }

    @GetMapping("/admin/review-exp/{expReportID}")
    public String adminReviewExpDetailed(@PathVariable("expReportID") Long expReportID, Model model) {

        ExpReportDTO expReport = expDAO.getById(expReportID);
        if (expReport.getDescription() == null || expReport.getDescription().equals("")) {
            expReport.setDescription("[Description not provided]");
        }
        if (expReport.getImageURL() == null || expReport.getImageURL().equals("")) {
            expReport.setImageURL("[Image not provided]");
        }
        if (expReport.getLicensePlate() == null || expReport.getLicensePlate().equals("")) {
            expReport.setLicensePlate("[License plate not provided]");
        }
        if (expReport.getParkingLot() == null || expReport.getParkingLot().equals("")) {
            expReport.setParkingLot("[Parking lot not provided]");
        }

        model.addAttribute("report", expReport);
        return "adminReviewExpDetailed";
    }

    @GetMapping("/admin/review-poorpark/{poorParkReportID}")
    public String adminReviewPoorParkDetailed(@PathVariable("poorParkReportID") Long poorParkReportID, Model model) {

        PoorParkReportDTO poorParkReport = poorParkDAO.getById(poorParkReportID);
        if (poorParkReport.getDescription() == null || poorParkReport.getDescription().equals("")) {
            poorParkReport.setDescription("[Description not provided]");
        }
        if (poorParkReport.getDescription() == null || poorParkReport.getImageURL().equals("")) {
            poorParkReport.setImageURL("[Image not provided]");
        }
        if (poorParkReport.getDescription() == null || poorParkReport.getLicensePlate().equals("")) {
            poorParkReport.setLicensePlate("[License plate not provided]");
        }
        if (poorParkReport.getParkingLot() == null || poorParkReport.getParkingLot().equals("")) {
            poorParkReport.setParkingLot("[Parking lot not provided]");
        }

        model.addAttribute("report", poorParkReport);
        return "adminReviewPoorParkDetailed";
    }

    @PostMapping("/admin/deleteExpReport/{expReportID}")
    public ModelAndView deleteExpReport(@PathVariable("expReportID") Long expReportID, RedirectAttributes attributes) {
        expDAO.deleteById(expReportID);

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/review-exp");
        attributes.addFlashAttribute("message", "Report deleted successfully!");

        return modelAndView;
    }

    @PostMapping("/admin/deletePoorParkReport/{poorParkReportID}")
    public ModelAndView deletePoorParkReport(@PathVariable("poorParkReportID") Long poorParkReportID, RedirectAttributes attributes) {
        poorParkDAO.deleteById(poorParkReportID);

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/review-poorpark");
        attributes.addFlashAttribute("message", "Report deleted successfully!");

        return modelAndView;
    }

    @PostMapping("/admin/backToAdminView")
    public ModelAndView backToAdminView() {
        ModelAndView modelAndView = new ModelAndView("redirect:/adminview");
        return modelAndView;
    }

    @PostMapping("/admin/backToExpReport")
    public ModelAndView backToExpReport() {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/review-exp");
        return modelAndView;
    }

    @PostMapping("/admin/backToPoorParkReport")
    public ModelAndView backToPoorParkReport() {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/review-poorpark");
        return modelAndView;
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
	
	@GetMapping("/userList")
	public String list(@ModelAttribute("form") UserForm form, Model model) {
        List<PendingUserDTO> list = pendingUserService.list();
        model.addAttribute("pendingList", list);
        return "userListView";
    }

    @GetMapping("/admin/managepoints")
    public String ProfilePage(Model model) {

        List<UserDTO> users = dao.getAllByUserRole("Student");
        model.addAttribute("adminUserList", users);


        return "managePoints";
    }



    @PostMapping("/admin/updatePoint")
    public String updateByOne(@ModelAttribute("managePointForm") ManagePointForm form, Model model) {
        // Retrieve the user from the database using the UserDAO
        UserDTO user = dao.getById(form.getUserId());
        System.out.println(form.toString() +"--");



        if (form.getType().equals("ADD")) {
            user.setPoints(user.getPoints() + form.getAmount());
        } else {
            user.setPoints(user.getPoints() - form.getAmount());

        }
        System.out.println("--" + user.getPoints());

        // Save the changes to the database using the UserDAO
        dao.save(user);

        // Retrieve the updated list of users and add it to the model
        List<UserDTO> adminUserList = dao.findAll();
        model.addAttribute("adminUserList", adminUserList);

        // Redirect back to the same page with the updated list of users
        return "redirect:/admin/managepoints";
    }
}
