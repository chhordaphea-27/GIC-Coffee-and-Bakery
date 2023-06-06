package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CategoriesController {
    @GetMapping("/categories")	
	public ModelAndView test(Model model) {

		// model.addAttribute("", );

		return new ModelAndView("categories");
	}

	// private List<Product> get() {
	// 	ProductManagement productManagement = new ProductManagement();
	// 	return productManagement.getProductsByCategory();
	// }

	



}