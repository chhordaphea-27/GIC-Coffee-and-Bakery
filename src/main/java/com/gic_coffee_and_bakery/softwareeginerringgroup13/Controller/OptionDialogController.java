package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OptionDialogController {
    @GetMapping("/option_dialog")	
	public ModelAndView test(Model model) {


		return new ModelAndView("option_dialog");
	}
}