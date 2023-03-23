package com.petesparkingmgt.ctl;

import com.fasterxml.jackson.core.JsonToken;
import com.petesparkingmgt.dao.ExpReportDAO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.ExpReportForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
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

    @PostMapping("/submitExpReport")
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
    }
}
