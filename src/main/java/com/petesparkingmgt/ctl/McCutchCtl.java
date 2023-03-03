package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class McCutchCtl {

    @GetMapping("/mccutcheon")
    public String McCutchPage() {
        return "mccutcheon-lot";
    }


}
