package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.*;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;

@Controller
public class DrinksInfoController {
    @GetMapping("/drinksinfo")	
	public ModelAndView test(Model model) {

		ProductSize emptyDrink = new ProductSize(0, new Size(), new Product());

		model.addAttribute("alldrink", getallDrinks());
	    model.addAttribute("detailDrink", emptyDrink);

		return new ModelAndView("drinksinfo");
	}

	@GetMapping("/drinksinfo/displayDrinksInfo")
	public ModelAndView handleDataFromView(@RequestParam("product_id") int productId, 
											Model model) {

		   
		model.addAttribute("alldrink", getallDrinks());
	    model.addAttribute("detailDrink", getProductById(productId));
	  return new ModelAndView("/drinksinfo");
	}

	private List<Product> getallDrinks() {
		ProductManagement productManagement = new ProductManagement();
		return productManagement.getAllProducts();
	}


	private Product getProductById(int product_id) {
		ProductManagement productManagement = new ProductManagement();
		return productManagement.getProductById(product_id);
	}



}
