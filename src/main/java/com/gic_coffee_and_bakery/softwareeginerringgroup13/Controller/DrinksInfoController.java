package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.*;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;

@Controller
public class DrinksInfoController {
    @GetMapping("/drinksinfo")	
	public ModelAndView test(Model model) {

		var t = getAllDrinkCategories();

		model.addAttribute("drinkCategories", getAllDrinkCategories());
		model.addAttribute("alldrink", getDrinkTypeFromProduct());
	    model.addAttribute("detailDrink", getEmptyProductSizeList());

		return new ModelAndView("drinksinfo");
	}

	@GetMapping("/drinksinfo/displayDrinksInfo")
	public ModelAndView handleDataFromView(@RequestParam("product_id") int productId, 
											Model model) {

		model.addAttribute("drinkCategories", getAllDrinkCategories());
		model.addAttribute("alldrink", getDrinkTypeFromProduct());
	    model.addAttribute("detailDrink", getProductSizeListByID(productId));
	  return new ModelAndView("drinksinfo");
	}


	@PostMapping("/drinksinfo")
	public ModelAndView addUpdateDrinkHadaler(Model model,
		@RequestParam("productID") int product_id,
		@RequestParam("name") String foodName,
		@RequestParam("cateogry") int categoryId,

		@RequestParam("smallPrice") Double smallPrice,
		@RequestParam("mediumPrice") Double mediumPrice,
		@RequestParam("largePrice") Double largePrice,

		@RequestParam("smallID") int smallID,
		@RequestParam("mediumID") int mediumID,
		@RequestParam("largeID") int largeID,

		@RequestParam("note") String note,
		@RequestParam("imageUrl") String imageUrl
	) {
		ProductManagement productManagement = new ProductManagement();
		SizeManagement sizeManagement = new SizeManagement();
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		
		Product product;
		Size smallSize;
		Size mediumSize;
		Size largeSize;
		
		if (product_id == 0) { 
			// Create Mode
			product = new Product(product_id, foodName, note, getCategoryByID(categoryId), imageUrl);
			product_id = productManagement.addProduct(product);
			product.setId(product_id);

		} else {
			// Update Mode
			product = new Product(product_id, foodName, note, getCategoryByID(categoryId), imageUrl);
			productManagement.updateProduct(product);
		}
		

		if(smallID > 0) {	// Update
			if (smallPrice > 0) {
				smallSize = new Size(smallID, "Small", smallPrice);
				sizeManagement.updateSize(smallSize);
			}
		} else { 	 // Create
			if (smallPrice > 0) {
				smallSize = new Size(smallID, "Small", smallPrice);
				smallID = sizeManagement.addSize(smallSize);

				smallSize.setId(smallID);
				productSizeManagement.addProductSize(new ProductSize(0, smallSize, product));
			}
		}

		if (mediumID > 0) {
			if (mediumPrice > 0) {
				mediumSize = new Size(mediumID, "Medium", mediumPrice);
				sizeManagement.updateSize(mediumSize);
			}
		} else {
			if (mediumPrice > 0) {
				mediumSize = new Size(mediumID, "Medium", mediumPrice);
				mediumID = sizeManagement.addSize(mediumSize);
				
				mediumSize.setId(mediumID);
				productSizeManagement.addProductSize(new ProductSize(0, mediumSize, product));
			}
		}

		if (largeID > 0){
			if (largePrice > 0) {
				largeSize = new Size(largeID, "Large", largePrice);
				sizeManagement.updateSize(largeSize);
			} 
		} else {
			if (largePrice > 0) {
				largeSize = new Size(largeID, "Large", largePrice);
				largeID = sizeManagement.addSize(largeSize);

				largeSize.setId(largeID);
				productSizeManagement.addProductSize(new ProductSize(0, largeSize, product));
			} 
		}


	    model.addAttribute("drinkCategories", getAllDrinkCategories());
		model.addAttribute("alldrink", getDrinkTypeFromProduct());
	    model.addAttribute("detailDrink", getProductSizeListByID(product_id));
	  return new ModelAndView("drinksinfo");

	}

	@PostMapping("/drinksinfo/deleteProduct")
	private ModelAndView deleteProduct(Model model,
		@RequestParam("delProductID") int delProductID ) {

		ProductManagement productManagement = new ProductManagement();
		productManagement.disableProduct(delProductID);

		model.addAttribute("drinkCategories", getAllDrinkCategories());
		model.addAttribute("alldrink", getDrinkTypeFromProduct());
	    model.addAttribute("detailDrink", getEmptyProductSizeList());
	  return new ModelAndView("drinksinfo");

	}


	// MARK: Private function

	private ProductSizeList getProductSizeListByID(int product_id) {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByProductID(product_id);
	}


	private List<ProductSizeList> getDrinkTypeFromProduct() {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByType("Drink")	;		
	}

	private List<Category> getAllDrinkCategories() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllDrinkCategories();
	}

	private Category getCategoryByID(int category_id) {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getCategoryById(category_id);
	}

	private Size getSizeByID(int size_id) {
		SizeManagement sizeManagement = new SizeManagement();
		return sizeManagement.getSizeById(size_id);
	}

	private ProductSizeList getEmptyProductSizeList() {
		Product emptyFood = new Product(0, null, null, null, null, null);
		ArrayList<Size> size = new ArrayList<Size>();
		return new ProductSizeList(emptyFood, size);
	}

}
