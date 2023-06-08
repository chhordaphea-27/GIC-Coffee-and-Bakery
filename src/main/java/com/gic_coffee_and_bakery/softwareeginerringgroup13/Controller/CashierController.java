package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.UserManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;


@RestController
public class CashierController {
    @GetMapping(path = "/cashier")	
	public ModelAndView test(Model model) {


		model.addAttribute("alluser", getAllUser());
	    model.addAttribute("detailUser", getEmptyUser());
	
		return new ModelAndView("cashier");
	}

	@GetMapping("/cashier/dislaycashier")
	public ModelAndView handleDataFromView(@RequestParam("userId") int userId, 
											Model model) {


		model.addAttribute("alluser", getAllUser());
	    model.addAttribute("detailUser", getUserByID(userId));
	  return new ModelAndView("/cashier");
	}

	@PostMapping("/cashier")
	public ModelAndView addUpdateCashireHandler(Model model,
		@RequestParam("userID") int userID,
		@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName,
		@RequestParam("sex") String sex,
		@RequestParam("dob") Date dob,
		@RequestParam("username") String username,
		@RequestParam("password") String password
		) {
		
		UserManagement userManagement = new UserManagement();
		
		if (userID == 0) { 
			LocalDate currentDate = LocalDate.now();
			Date hireDate = Date.valueOf(currentDate);

			User user = new User(userID, firstName, lastName, sex, "Cashier", dob, hireDate, getAge(dob), username, password, null);
			userID = userManagement.addUser(user);
			
		} else {
			User user = new User(userID, firstName, lastName, sex, "Cashier", dob, getAge(dob), username, password, null);
			userManagement.updateUser(user);
		}

		model.addAttribute("alluser", getAllUser());
	    model.addAttribute("detailUser", getUserByID(userID));
	  return new ModelAndView("/cashier");

	}

	@GetMapping("/cashier/deleteuser")
	private ModelAndView deleteUserhandler(Model model,
		@RequestParam("user_ID") int user_ID
		) {

			UserManagement userManagement = new UserManagement();
			userManagement.disableUser(user_ID);
			
		model.addAttribute("alluser", getAllUser());
		model.addAttribute("detailUser", getEmptyUser());
		return new ModelAndView("cashier");

	}


	private List<User> getAllUser() {
		UserManagement userManagement = new UserManagement();
		return userManagement.getAllUsers();
	}

	private User getUserByID(int userId) {
		UserManagement userManagement = new UserManagement();
		return userManagement.getUserById(userId);
	}


	private int getAge(Date inputDate) {
        LocalDate localDobDate = inputDate.toLocalDate();     
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(localDobDate, currentDate);
        return period.getYears();
    }

	private User getEmptyUser() {
		return new User(0, null, null, null, null, null, 0, null, null, null);

	}
}
