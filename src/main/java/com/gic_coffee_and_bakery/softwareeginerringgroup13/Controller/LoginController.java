package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.UserManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;


@RestController
public class LoginController {

	@GetMapping(path = "/")	
	public ModelAndView test(Model model) {


		return new ModelAndView("login");
	}

	@PostMapping("/")
	public ModelAndView login(@RequestParam("username") String username, 
								@RequestParam("password") String password){

		UserManagement userManagement = new UserManagement();
		User user = userManagement.login(username, password);

		if(user!=null){
			if(user.getRole().equals("Cashier")) {
				return new ModelAndView("login");
			}
			else if(user.getRole().equals("Admin")){ 
				return new ModelAndView("redirect:/cashier");
				
			}
	

			return new ModelAndView("login");
		
		} 
		else{
			return new ModelAndView("login");

		}

	}
}