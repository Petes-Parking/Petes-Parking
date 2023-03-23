package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileCtl {

    @GetMapping("/profile")
    public String profilePage() {
        return "profilePage";
    }


}
