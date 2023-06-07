package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.*;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;


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

		var t = getProductSizeListByID(productId);
		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", getProductSizeListByID(productId));
	  return new ModelAndView("/foodsinfo");
	}


	private List<ProductSizeList> getFoodTypeFromProduct() {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByType("Food")	;		
	}

	private ProductSizeList getProductSizeListByID(int product_id) {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByProductID(product_id);
	}
}
