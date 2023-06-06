package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.UserManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;


@RestController
public class CashierController {
    @GetMapping(path = "/cashier")	
	public ModelAndView test(Model model) {

		User emptyUser = new User(0, null, null, null, null, null, 0, null, null, null);

		model.addAttribute("alluser", getAllUser());
	    model.addAttribute("detailUser", emptyUser);
	
		return new ModelAndView("cashier");
	}

	@GetMapping("/cashier/dislaycashier")
	public ModelAndView handleDataFromView(@RequestParam("userId") int userId, 
											Model model) {

		System.out.println("User ID: " + getUserByID(userId).getId());

		model.addAttribute("alluser", getAllUser());
	    model.addAttribute("detailUser", getUserByID(userId));
	  return new ModelAndView("/cashier");
	}



	private List<User> getAllUser() {
		UserManagement userManagement = new UserManagement();
		return userManagement.getAllUsers();
	}

	private User getUserByID(int userId) {
		UserManagement userManagement = new UserManagement();
		return userManagement.getUserById(userId);
	}

}
