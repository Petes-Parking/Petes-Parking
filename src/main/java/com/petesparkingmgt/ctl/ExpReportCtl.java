package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpReportCtl {

    @GetMapping("/exp-report")
    public String MainPage() {
        return "exp-report";
    }


}