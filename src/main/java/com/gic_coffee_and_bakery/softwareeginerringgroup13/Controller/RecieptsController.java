package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.OrderAndItemList;


@Controller
public class RecieptsController {

	private OrderAndItemList orderAndItemList = null;
	

    @GetMapping("/reciepts")	
	public ModelAndView test(Model model) {

		OrderAndItemList tmp = (OrderAndItemList) model.getAttribute("data-orderItemList");
		if (tmp != null) orderAndItemList = tmp;


		model.addAttribute("orderItemList", orderAndItemList);
		model.addAttribute("orderItemslist", orderAndItemList.getOrderItemList());


		return new ModelAndView("reciepts");
	}
}