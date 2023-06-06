package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.ProductManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.ProductSizeManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Product;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.ProductSize;

@Controller
public class FoodsInfoController {
    @GetMapping("/foodsinfo")	
	public ModelAndView test(Model model) {
		Product emptyFood = new Product(0, null, null, null, null, null);

		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", emptyFood);

		return new ModelAndView("foodsinfo");
	}

	@GetMapping("/foodsinfo/displayFoodInfo")
	public ModelAndView handleDataFromView(@RequestParam("product_id") int productId, 
											Model model) {

		
		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", getProductById(productId));
	  return new ModelAndView("/foodsinfo");
	}

	private List<ProductSize> getAllProduct() {
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		return productSizeManagement.getAllProductSizes();
	}


	private ProductSize getProductById(int product_id) {
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		return productSizeManagement.getProductSizeById(product_id);
	}

	private ArrayList<ProductSize> getFoodTypeFromProduct() {
		ProductManagement productManagement = new ProductManagement();
		List<Product> foods = productManagement.getAllFood();

		ArrayList<ProductSize> allProductSizes = new ArrayList<>();
		
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		for (Product food : foods) {
			List<ProductSize> ps = productSizeManagement.getProductSizesByProductId(food.getId());
			for (ProductSize productSize : ps) {
				allProductSizes.add(productSize);
			}
		}


		return allProductSizes;
	} 

}
