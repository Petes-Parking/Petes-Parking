package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCtl {
	
	@GetMapping("/homePage")
	public String homePage() {
		return "home";
	}
	


}
