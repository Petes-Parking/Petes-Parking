package com.petesparkingmgt.ctl;

import com.fasterxml.jackson.core.JsonToken;
import com.petesparkingmgt.dao.ExpReportDAO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.ExpReportForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class ExpReportCtl {

    @Autowired
    public ExpReportDAO dao;

    @GetMapping("/exp-report")
    public String MainPage() {
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

    @PostMapping("/submitExpReport")
    public String saveReport(@RequestParam("licensePlate") String licensePlate,
                                             @RequestParam("description") String description,
                                             @RequestParam("imageData") MultipartFile imageData,
                                             @RequestParam("parkingLot") String parkingLot,
                                             HttpSession session) throws IOException {
        UserDTO user = (UserDTO) session.getAttribute("user");
        ExpReportDTO expReport = new ExpReportDTO();
        expReport.setLicensePlate(licensePlate);
        expReport.setDescription(description);
        expReport.setImageData(imageData.getBytes());
        expReport.setParkingLot(parkingLot);
        expReport.setReporterEmail(user.getEmail());
        Date date = new Date();
        expReport.setReportDate(date);
        dao.save(expReport);

        return "redirect:/main";
    }
}
