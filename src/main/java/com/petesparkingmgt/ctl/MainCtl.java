package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCtl {

    @GetMapping("/main")
    public String MainPage() {
        return "mainPage";
    }


}
