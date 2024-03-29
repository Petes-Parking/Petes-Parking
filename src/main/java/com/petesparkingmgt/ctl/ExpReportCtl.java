package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.reports.ExpReportDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.reports.ExpReportDTO;
import com.petesparkingmgt.dto.user.EmailPreferencesDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import com.petesparkingmgt.service.EmailService;
import com.petesparkingmgt.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Controller
public class ExpReportCtl {

    @Autowired
    public ExpReportDAO dao;

    @Autowired
    public EmailPreferencesDAO emailDAO;

    @Autowired
    public VehicleDAO vehicleDAO;

    @Autowired
    public PermissionService permissionService;


    @Autowired
    public UserDAO userDAO;

    @GetMapping("/exp-report")
    public String MainPage(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "error";
        }
        model.addAttribute("hasPermission", permissionService.hasPermissionToReport(user.getId()));
        return "exp-report";
    }

    /*@PostMapping("/submitExpReport")
    public String submitReport(@ModelAttribute("expReportForm") ExpReportForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (form.getDTO() != null) {
            ExpReportDTO dto = form.getDTO();
            dto.setReporterEmail(user.getEmail());
            Date date = new Date();
            dto.setReportDate(date);
            dao.save(dto);
        }

        return "mainPage";
    }*/

    @GetMapping("/noPermissionExpReport")
    public String noSubmissionExpReport(Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");

        System.out.println("Tried to submit exp report park but no permission!");
        model.addAttribute("error", "Your permission to report has been revoked for inaccurate reports!");

        return "exp-report";
    }
    @PostMapping("/submitExpReport")
    public String saveReport(@RequestParam("licensePlate") String licensePlate,
                                             @RequestParam("description") String description,
                                             @RequestParam("imageData") MultipartFile imageData,
                                             @RequestParam("parkingLot") String parkingLot, Model model,
                                             HttpSession session) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        UserDTO user = (UserDTO) session.getAttribute("user");

        if (!permissionService.hasPermissionToReport(user.getId())) {
            System.out.println("Here!");
            model.addAttribute("error", "Your permission to report has been revoked for inaccurate reports!");
            return "exp-report";
        }

        ExpReportDTO expReport = new ExpReportDTO();
        expReport.setLicensePlate(licensePlate);
        expReport.setDescription(description);
        expReport.setImageData(imageData.getBytes());
        expReport.setParkingLot(parkingLot);
        expReport.setReporterEmail(user.getEmail());
        Date date = new Date();
        expReport.setReportDate(date);
        dao.save(expReport);

        VehicleDTO vehicle = vehicleDAO.getVehicleDTOByLicense1OrLicense2(licensePlate, licensePlate);
        if (vehicle != null) {
            UserDTO reported = userDAO.findById(vehicle.getUserId());
            if (reported != null) {
                EmailPreferencesDTO emailDTO =  emailDAO.getByUserID(reported.getId());
                if (emailDTO != null) {
                    int emailPref = emailDTO.getReportPref();
                    System.out.print("Email preference: ");
                    System.out.println(emailPref);
                    if (emailPref == 1) {
                        EmailService emailService = new EmailService();
                        emailService.createExpReportedEmail(expReport, reported);
                    } else {
                        System.out.println("No email sent since pref is off");
                    }
                } else {
                    //Shouldn't reach this since it null checks upon login
                    System.out.println("Email pref is null so it won't send");
                }
            } else {
                System.out.println("No user found");
            }
        } else {
            System.out.println("No vehicle found");
        }
        return "redirect:/main";
    }
}
