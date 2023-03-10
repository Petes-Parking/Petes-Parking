package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class poorparkReportCtl {

    @GetMapping("/poorpark-report")
    public String MainPage() {
        return "poorpark-report";
    }


}
