package com.petesparkingmgt.ctl;


import com.petesparkingmgt.dao.parking.ParkingDAO;
import com.petesparkingmgt.dao.reports.ExpReportDAO;
import com.petesparkingmgt.dao.reports.PoorParkReportDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.parking.ParkingDTO;
import com.petesparkingmgt.dto.reports.ExpReportDTO;
import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import com.petesparkingmgt.dto.user.PendingUserDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.form.ManagePointForm;
import com.petesparkingmgt.form.UserForm;
import com.petesparkingmgt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class AdminCtl {

    @Autowired
    public UserService service;

    @Autowired
    public PendingUserService pendingUserService;

    @Autowired
    public PermissionService permissionService;
    
    @Autowired
    public BookingService service2;

    @Autowired
    public UserDAO dao;

    @Autowired
    public ExpReportDAO expDAO;

    @Autowired
    public PoorParkReportDAO poorParkDAO;

    @Autowired
    public ParkingDAO parkingDAO;
    @Autowired
    public NotificationService notificationService;


    @GetMapping("/adminview")
    public String adminPage(Model model) {


        List<UserDTO> users = dao.findAll();
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

    @GetMapping("/admin/removeReportPriv/{email}")
    public ModelAndView adminRemoveReportPriv(@PathVariable("email") String email, RedirectAttributes attributes) {
        // Retrieve the user by the userId from the database
        UserDTO user = service.getByEmail(email);
        System.out.println(email);
        permissionService.changeReportPermission(user.getId(), false);

        System.out.println("Removed " + user.getEmail() + " privS!" );
        // Add the user object to the model
        ModelAndView modelAndView = new ModelAndView("redirect:/adminview");
        attributes.addFlashAttribute("message", "Removed privileges successfully!");

        // Return the view name for the edit user page
        return modelAndView;
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
        if (expReport.getLicensePlate() == null || expReport.getLicensePlate().equals("")) {
            expReport.setLicensePlate("[License plate not provided]");
        }
        if (expReport.getParkingLot() == null || expReport.getParkingLot().equals("")) {
            expReport.setParkingLot("[Parking lot not provided]");
        }

        model.addAttribute("report", expReport);

        byte[] imageData = expReport.getImageData();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("reportImage", base64Image);


        return "adminReviewExpDetailed";
    }

    @GetMapping("/admin/parkingArea={area}")
    public String editSpecificArea(@PathVariable("area") String area, Model model) {
        ParkingDTO DTO = parkingDAO.findByParkingName(area);
        model.addAttribute("parkingArea", DTO);
        return "specificArea";
    }

    @GetMapping("/admin/review-poorpark/{poorParkReportID}")
    public String adminReviewPoorParkDetailed(@PathVariable("poorParkReportID") Long poorParkReportID, Model model) {

        PoorParkReportDTO poorParkReport = poorParkDAO.getById(poorParkReportID);
        if (poorParkReport.getDescription() == null || poorParkReport.getDescription().equals("")) {
            poorParkReport.setDescription("[Description not provided]");
        }
        if (poorParkReport.getDescription() == null || poorParkReport.getLicensePlate().equals("")) {
            poorParkReport.setLicensePlate("[License plate not provided]");
        }
        if (poorParkReport.getParkingLot() == null || poorParkReport.getParkingLot().equals("")) {
            poorParkReport.setParkingLot("[Parking lot not provided]");
        }

        model.addAttribute("report", poorParkReport);

        byte[] imageData = poorParkReport.getImageData();
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("reportImage", base64Image);

        return "adminReviewPoorParkDetailed";
    }

    @PostMapping("/admin/deleteExpReport/{expReportID}")
    public ModelAndView deleteExpReport(@PathVariable("expReportID") Long expReportID, RedirectAttributes attributes) {
        long reporterID = dao.findByEmail(expDAO.getById(expReportID).getReporterEmail()).getId();
        expDAO.deleteById(expReportID);

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/review-exp");
        attributes.addFlashAttribute("message", "Report deleted successfully!");
        if (reporterID > 0) {
            notificationService.addNotificationFor(reporterID, "Your report with ID: " + expReportID + " has been dismissed!", "main");
        }

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
        List<UserDTO> users = dao.findAll();
        model.addAttribute("adminUserList", users);
        return "managePoints";
    }


    @Autowired
    public ParkingDAO editParkingInfoDAO;
    @GetMapping("/admin/editparkinginfo")
    public String EditParkingInfoPage(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }
        List<ParkingDTO> parkingareaList = editParkingInfoDAO.findAll();
        List<String> formattedAreaList = new ArrayList<>();
        for (ParkingDTO editParkingInfoDTO : parkingareaList) {
            String s1 = editParkingInfoDTO.getParkingName();
            formattedAreaList.add(s1);
        }
        formattedAreaList.forEach(System.out::println);
        model.addAttribute("parkingAreas", formattedAreaList);
        return "editparkinginfo";
    }




    @PostMapping("/admin/updatePoint")
    public String updateByOne(@ModelAttribute("managePointForm") ManagePointForm form, Model model) {
        // Retrieve the user from the database using the UserDAO
        UserDTO user = dao.getById(form.getUserId());
        System.out.println(form.toString() +"--");
        if (form.getAmount() < 0) {
            List<UserDTO> adminUserList = dao.findAll();
            model.addAttribute("adminUserList", adminUserList);
            model.addAttribute("errors", "Do not use negative values!");
            return "managePoints";
        }

        if (form.getType().equalsIgnoreCase("add")) {
            user.setPoints(user.getPoints() + form.getAmount());
        } else {
            if (user.getPoints() - form.getAmount() < 0) {
                List<UserDTO> adminUserList = dao.findAll();
                model.addAttribute("adminUserList", adminUserList);
                model.addAttribute("errors", "User's points cannot be negative!");
                return "managePoints";
            }
            user.setPoints(user.getPoints() - form.getAmount());

        }
        System.out.println("--" + user.getPoints());

        // Save the changes to the database using the UserDAO
        dao.save(user);

        // Retrieve the updated list of users and add it to the model
        List<UserDTO> adminUserList = dao.findAll();
        model.addAttribute("adminUserList", adminUserList);

        // Redirect back to the same page with the updated list of users
        return "managePoints";
    }
}
