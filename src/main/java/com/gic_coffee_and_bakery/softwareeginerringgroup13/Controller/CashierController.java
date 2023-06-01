package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.UserManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;


@RestController
public class CashierController {
    @GetMapping(path = "/cashier")	
	public ModelAndView test(Model model) {

		UserManagement userManagement = new UserManagement();
		List<User> users = userManagement.getAllUsers();

		model.addAttribute("alluser", users);
	
		return new ModelAndView("cashier");
	}


}
