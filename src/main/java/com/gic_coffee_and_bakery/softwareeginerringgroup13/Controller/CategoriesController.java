package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.CategoryManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.ProductSizeManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Category;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.ProductSize;
@Controller
public class CategoriesController {
    @GetMapping("/categories")	
	public ModelAndView test(Model model) {

		model.addAttribute("allCategoryName", showCategoryNames());

		return new ModelAndView("categories");

	}

	@GetMapping("/categories/displayDrinks") 
	public ModelAndView handleDataFromView(@RequestParam("category_id") int category_id, Model model) {
		// System.out.println("Category ID: " + getCategoryById(category_id).getId());

		model.addAttribute("allCategoryName", showCategoryNames());
	    model.addAttribute("allDrinks", showDrinksProduct(category_id));
	  return new ModelAndView("/cashier");
	}
	

	private	List<Category> showCategoryNames() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllFoodCategories();
	}

	private List<ProductSize> showDrinksProduct(int category_id) {
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		return productSizeManagement.getProductSizesByCategory(category_id);
	}


	



}