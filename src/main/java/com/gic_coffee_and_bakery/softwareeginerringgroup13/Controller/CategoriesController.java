package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.CategoryManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.ProductSizeListManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.ProductSizeManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Category;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.ProductSize;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.ProductSizeList;

@Controller
public class CategoriesController {

    @GetMapping("/categories")	
	public ModelAndView test(Model model) {


		model.addAttribute("allCategoryDrink", showCategoryDrink());
		model.addAttribute("allCategoryFood", showCategoryFood());
		model.addAttribute("selectedCate", getAllProduct());

		return new ModelAndView("categories");

	}

	@GetMapping("/categories/displayDrinks") 
	public ModelAndView handleDataFromView(@RequestParam("category_id") int category_id, Model model) {
		// System.out.println("Category ID: " + getCategoryById(category_id).getId());

		model.addAttribute("allCategoryDrink", showCategoryDrink());
	    model.addAttribute("allCategoryFood", showCategoryFood());
		model.addAttribute("selectedCate", getProductByCategory(category_id));

	  	return new ModelAndView("categories");
	}

	@GetMapping("/categories/displayFoods") 
	public ModelAndView handleDataFromFoodView(@RequestParam("category_id") int category_id, Model model) {
		// System.out.println("Category ID: " + getCategoryById(category_id).getId());

		model.addAttribute("allCategoryDrink", showCategoryDrink());
	    model.addAttribute("allCategoryFood", showCategoryFood());
		model.addAttribute("selectedCate", getProductByCategory(category_id));

	 	 return new ModelAndView("categories");
	}
	

	
	private	List<Category> showCategoryDrink() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllDrinkCategories();
	}
	
	private List<ProductSize> showDrinksProduct(int category_id) {
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		return productSizeManagement.getProductSizesByCategory(category_id);
	}
	
	
	private	List<Category> showCategoryFood() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllFoodCategories();
	}
	private List<ProductSize> showFoodsProduct(int category_id) {
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		return productSizeManagement.getProductSizesByCategory(category_id);
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