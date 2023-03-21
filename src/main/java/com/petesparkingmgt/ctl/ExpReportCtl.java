package com.petesparkingmgt.ctl;

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
            dao.save(dto);
        }

        return "exp-report";
    }
}
