package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;


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
	public ModelAndView test() {
		return new ModelAndView("Login/loginPage");
	}

	@PostMapping("/")
	public ModelAndView login(@RequestParam("username") String username, 
								@RequestParam("password") String password){

		UserManagement userManagement = new UserManagement();
		User user = userManagement.loginUser(username, password);

		if(user!=null){
			
			return new ModelAndView("logdghghginPage");
			
			// if(user.getRole().equals("Cashier")) {}
			// else if(user.getRole().equals("Admin"){ }
		

		
		} 
		else{
			return new ModelAndView("Login/loginPage");

		}

	}
}