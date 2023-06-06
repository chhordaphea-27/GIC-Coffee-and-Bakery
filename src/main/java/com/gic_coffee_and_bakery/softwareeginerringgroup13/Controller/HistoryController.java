package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.OrderManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Order;

@Controller
public class HistoryController {
    @GetMapping("/history")	
	public ModelAndView test(Model model) {

		model.addAttribute("allOrder", showOrderHistory());

		return new ModelAndView("history");
	}

	private List<Order> showOrderHistory() {
		OrderManagement orderManagement = new OrderManagement();
		return orderManagement.getAllOrders();
	}


}
