package com.react.meteo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class weatherController {
	
	@RequestMapping(value="/weather")
	public String getByZone(@RequestParam (name = "ville", defaultValue = "") String ville) {
		
		return "redirect:/weather/"+ville;
	}
	
	@RequestMapping(value="/")
	public String initial() {
		
		return "test";
	}

}
