package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagePointsCtl {

    @GetMapping("/managepoints")
    public String ProfilePage() {
        return "managePoints";
    }


}
