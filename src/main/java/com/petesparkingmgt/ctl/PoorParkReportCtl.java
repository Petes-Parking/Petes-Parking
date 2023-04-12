package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.reports.PoorParkReportDAO;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import com.petesparkingmgt.dto.user.EmailPreferencesDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import com.petesparkingmgt.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class PoorParkReportCtl {

    @Autowired
    public PoorParkReportDAO dao;

    @Autowired
    public VehicleDAO vehicleDAO;

    @Autowired
    public EmailPreferencesDAO emailDAO;

    @Autowired
    public UserDAO userDAO;

    @GetMapping("/poorpark-report")
    public String MainPage() {
        return "poorpark-report";
    }

    /*@PostMapping("/submitPoorParkReport")
    public String submitReport(@ModelAttribute("poorParkReportForm") PoorParkingReportForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (form.getDTO() != null) {
            PoorParkReportDTO dto = form.getDTO();
            dto.setReporterEmail(user.getEmail());
            Date date = new Date();
            dto.setReportDate(date);
            dao.save(dto);
        }

        return "mainPage";
    }*/

    @PostMapping("/submitPoorParkReport")
    public String saveReport(@RequestParam("licensePlate") String licensePlate,
                             @RequestParam("description") String description,
                             @RequestParam("imageData") MultipartFile imageData,
                             @RequestParam("parkingLot") String parkingLot,
                             HttpSession session) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        UserDTO user = (UserDTO) session.getAttribute("user");
        PoorParkReportDTO poorParkReport = new PoorParkReportDTO();
        poorParkReport.setLicensePlate(licensePlate);
        poorParkReport.setDescription(description);
        poorParkReport.setImageData(imageData.getBytes());
        poorParkReport.setParkingLot(parkingLot);
        poorParkReport.setReporterEmail(user.getEmail());
        Date date = new Date();
        poorParkReport.setReportDate(date);
        dao.save(poorParkReport);

        VehicleDTO vehicle = vehicleDAO.getVehicleDTOByLicense1OrLicense2(licensePlate, licensePlate);
        if (vehicle != null) {
            UserDTO reported = userDAO.findById(vehicle.getUserId());
            if (reported != null) {
                EmailPreferencesDTO emailDTO = emailDAO.getByUserID(reported.getId());
                if (emailDTO != null) {
                    int emailPref = emailDTO.getReportPref();
                    System.out.print("Email preference: ");
                    System.out.println(emailPref);
                    if (emailPref == 1) {
                        EmailService emailService = new EmailService();
                        emailService.createPoorParkReportedEmail(poorParkReport, reported);
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
