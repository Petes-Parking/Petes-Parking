package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LotCtl {

    @GetMapping("/corec")
    public String LotPage() {
        return "parking-lot";
    }


}
