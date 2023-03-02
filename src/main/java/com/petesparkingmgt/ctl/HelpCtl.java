package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpCtl {

    @GetMapping("/help")
    public String HelpPage() {
        return "helpPage";
    }


}
