package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComingsoonController {
	
	@GetMapping(value = "comingsoon")
	public String comingsoon() {
		
		return "site/comingsoon";
	}

}
