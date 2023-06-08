package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.CategoryManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.ProductSizeListManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Category;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.ProductSizeList;


@Controller
public class ProductSelectionController {
    @GetMapping("/product_selection")	
	public ModelAndView test(Model model) {

		model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
		model.addAttribute("foodCategories", getAllCategoriesOfFood());
		model.addAttribute("allProduct", getAllProduct());

		return new ModelAndView("product_selection");
	}

	@GetMapping("/product_selection/displayProductFromSelectedCategory")	
	public ModelAndView Drink(@RequestParam("category_id") int category_id,Model model) {

		model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
		model.addAttribute("foodCategories", getAllCategoriesOfFood());
		model.addAttribute("selectedCate", getProductByCategory(category_id));


		return new ModelAndView("product_selection");
	}


	private List<Category> getAllCategoriesOfDrink() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllDrinkCategories();
	}
	private List<Category> getAllCategoriesOfFood() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllFoodCategories();
	}

	private List<ProductSizeList> getProductByCategory(int category_id) {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByCategoryID(category_id);
	}
	private  List<ProductSizeList> getAllProduct() {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getAllProductSizeList();
	}

}
