package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class DrinksInfoController {
    @GetMapping(path = "/drinksinfo")	
	public ModelAndView test(Model model) {


		return new ModelAndView("drinksinfo");
	}
}
