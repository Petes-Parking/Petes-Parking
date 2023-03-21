package com.petesparkingmgt.ctl;

import com.petesparkingmgt.dao.PoorParkReportDAO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.PoorParkReportDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.form.ExpReportForm;
import com.petesparkingmgt.form.PoorParkingReportForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PoorParkReportCtl {

    @Autowired
    public PoorParkReportDAO dao;

    @GetMapping("/poorpark-report")
    public String MainPage() {
        return "poorpark-report";
    }

    @PostMapping("/submitPoorParkReport")
    public String submitReport(@ModelAttribute("poorParkReportForm") PoorParkingReportForm form, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (form.getDTO() != null) {
            PoorParkReportDTO dto = form.getDTO();
            dao.save(dto);
        }

        return "mainPage";
    }
}
