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
public class FoodsInfoController {
    @GetMapping("/foodsinfo")	
	public ModelAndView test(Model model) {




		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", getEmptyProductSizeList());
	    model.addAttribute("foodCategories", getAllFoodCategories());

		return new ModelAndView("foodsinfo");
	}

	@GetMapping("/foodsinfo/displayFoodInfo")
	public ModelAndView handleDataFromView(@RequestParam("product_id") int productId, 
											Model model) {

		var t = getProductSizeListByID(productId);
		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", getProductSizeListByID(productId));
	    model.addAttribute("foodCategories", getAllFoodCategories());
	  
		return new ModelAndView("foodsinfo");
	}

	@PostMapping("/foodsinfo")
	public ModelAndView addUpdateFoodHadaler(Model model,
		@RequestParam("productID") int product_id,
		@RequestParam("name") String foodName,
		@RequestParam("cateogry") int categoryId,
		@RequestParam("price") Double price,
		@RequestParam("sizeID") int sizeID,
		@RequestParam("note") String note,
		@RequestParam("imageUrl") String imageUrl
	) {
		ProductManagement productManagement = new ProductManagement();
		SizeManagement sizeManagement = new SizeManagement();
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		
		
		if (product_id == 0) { 
			// Create Mode
			Product product = new Product(product_id, foodName, note, getCategoryByID(categoryId), imageUrl);
			product_id = productManagement.addProduct(product);
			product.setId(product_id);

			Size size = getSizeByID(sizeID);
			size.setPrice(price);

			sizeID = sizeManagement.addSize(size);
			size.setId(sizeID);


			ProductSize productSize = new ProductSize(size, product);
			var t = productSizeManagement.addProductSize(productSize);

			System.out.println();
		} else {
			// Update Mode
			Product product = new Product(product_id, foodName, note, getCategoryByID(categoryId), imageUrl);
			productManagement.updateProduct(product);

			Size size = new Size(sizeID, "Medium", price);
			sizeManagement.updateSize(size);

		}

	    model.addAttribute("foodCategories", getAllFoodCategories());
		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", getProductSizeListByID(product_id));
	  return new ModelAndView("foodsinfo");

	}

	@PostMapping("/foodsinfo/deleteProduct")
	private ModelAndView deleteProduct(Model model,
		@RequestParam("productID") int productID ) {

		ProductManagement productManagement = new ProductManagement();
		productManagement.disableProduct(productID);

		model.addAttribute("foodCategories", getAllFoodCategories());
		model.addAttribute("allFood", getFoodTypeFromProduct());
	    model.addAttribute("detailFood", getEmptyProductSizeList());
	  return new ModelAndView("foodsinfo");

	}


	private List<ProductSizeList> getFoodTypeFromProduct() {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByType("Food")	;		
	}

	private ProductSizeList getProductSizeListByID(int product_id) {
		ProductSizeListManagement productSizeListManagement = new ProductSizeListManagement();
		return productSizeListManagement.getProductSizeListByProductID(product_id);
	}

	private List<Category> getAllFoodCategories() {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getAllFoodCategories();
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
