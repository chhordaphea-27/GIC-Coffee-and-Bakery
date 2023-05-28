package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@GetMapping(path = "/")
	public ModelAndView test() {
		return new ModelAndView("Login/index");
	}

}