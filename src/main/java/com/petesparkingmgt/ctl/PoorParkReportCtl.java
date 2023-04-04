package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.reports.PoorParkReportDAO;
import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class PoorParkReportCtl {

    @Autowired
    public PoorParkReportDAO dao;

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
                             HttpSession session) throws IOException {
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

        return "redirect:/main";
    }
}
