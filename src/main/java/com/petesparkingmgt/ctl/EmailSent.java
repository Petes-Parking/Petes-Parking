package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailSent {

    @GetMapping("/emailsent")
    public String Emailsent1() {
        return "email-sent";
    }


}
