package com.petesparkingmgt.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeCtl {
<<<<<<< HEAD
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}
	
=======
	@GetMapping(path = "/")
	public String homePage() {
		return "home";
	}
>>>>>>> 35f889f24c691ab6c861b2c55d8b130efadf24ba

}
