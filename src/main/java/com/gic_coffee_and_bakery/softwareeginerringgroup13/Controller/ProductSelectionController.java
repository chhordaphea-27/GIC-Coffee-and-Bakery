package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.*;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.*;


@Controller
public class ProductSelectionController {
	
	UserManagement userManagement = new UserManagement();
	TableManagement tableManagement = new TableManagement();

	User user = getUserById(12);
	Table tableSelected = getTableById(12);

	private OrderAndItemList orderAndItemList = null;

	private Category selectedCate;
	
	

	@GetMapping("/product_selection")	
	public ModelAndView displayProductPage(Model model) {
		
		Table table = (Table) model.getAttribute("tableSelection");
		if (table != null) {
			tableSelected = table;
		}

		var tmpuser = (User) model.getAttribute("user");
		if (tmpuser != null) user = tmpuser;
		
		if (orderAndItemList == null) {
			orderAndItemList = new OrderAndItemList();
			orderAndItemList.setOrder(new Order(0, user, null, "unpaid", tableSelected, 0));
			orderAndItemList.setOrderItemList(new ArrayList<OrderItem>());
			System.out.println(orderAndItemList);
		}
		model.addAttribute("orderAndItemList", orderAndItemList);
		model.addAttribute("tableSelection", tableSelected);
		model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
		model.addAttribute("foodCategories", getAllCategoriesOfFood());
		model.addAttribute("selectedCate", getAllProduct());		
		model.addAttribute("user", user);

		
		return new ModelAndView("product_selection");
	}
	
	@GetMapping("/product_selection/displayProductFromSelectedCategory")	
	public ModelAndView Drink(@RequestParam("category_id") int category_id,Model model) {
		
		selectedCate = getCategoryByID(category_id);

		model.addAttribute("orderAndItemList", orderAndItemList);
		model.addAttribute("tableSelection", tableSelected);
		model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
		model.addAttribute("foodCategories", getAllCategoriesOfFood());
		model.addAttribute("selectedCate", getProductByCategory(selectedCate.getId()));
		model.addAttribute("user", user);

		
		return new ModelAndView("product_selection");
	}
	
	
	
	@PostMapping("/product_selection")
	public ModelAndView addOrder(Model model,
		@RequestParam("product_id") int product_id,
		@RequestParam("sizeSelection") int size_ID,
		@RequestParam("sugar") String sugar,
		@RequestParam("addCream") Boolean addCream
	) {
		System.out.println("addCream: " + addCream);

		OrderItemManagement orderItemManagement = new OrderItemManagement();
		
		if (orderAndItemList.getOrder().getId() == 0) {
			Order order = createOrder();
			int orderID = addOrderToDatabase(createOrder());
			order.setId(orderID);
			orderAndItemList.setOrder(order);			
		}
		OrderItem orderItem = new OrderItem(0, orderAndItemList.getOrder(), getProductSizeByProductAndSize(product_id, size_ID), 1, addCream, sugar, null);
		var otid = orderItemManagement.addOrderItem(orderItem);
		orderItem.setId(otid);
		orderAndItemList.addOrderItemList(orderItem);	

		
		orderAndItemList = updateTotalPrice(orderAndItemList);

		if (selectedCate == null) {
			model.addAttribute("selectedCate", getAllProduct());
		} else {
			model.addAttribute("selectedCate", getProductByCategory(selectedCate.getId()));
		}
		
		model.addAttribute("orderAndItemList", orderAndItemList);
		model.addAttribute("tableSelection", tableSelected);
		model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
		model.addAttribute("foodCategories", getAllCategoriesOfFood());
		model.addAttribute("user", user);
		return new ModelAndView("product_selection");
	}
	
	@GetMapping("/product_selection/deleteorderitem")
	public ModelAndView deleteOrderItem(Model model,
		@RequestParam("orderItem_id") int orderItemID) {
			
			OrderItemManagement orderItemManagement = new OrderItemManagement();
			orderItemManagement.deleteOrderItem(orderItemID);
			
			for (int i = 0; i < orderAndItemList.getOrderItemList().size(); i++) {
				if (orderAndItemList.getOrderItemList().get(i).getId() == orderItemID) {
					orderAndItemList.getOrderItemList().remove(i);
				}
			}
			orderAndItemList = updateTotalPrice(orderAndItemList);

		if (selectedCate == null) {
			model.addAttribute("selectedCate", getAllProduct());
		} else {
			model.addAttribute("selectedCate", getProductByCategory(selectedCate.getId()));
		}
		model.addAttribute("orderAndItemList", orderAndItemList);
		model.addAttribute("tableSelection", tableSelected);
		model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
		model.addAttribute("foodCategories", getAllCategoriesOfFood());
		model.addAttribute("user", user);
		return new ModelAndView("product_selection");
	}

	@PostMapping("/product_selection/confirm")
	public ModelAndView finishOrderAndGotTORecipt(Model model, RedirectAttributes redirectAttributes
	) {

		model.addAttribute("data-orderItemList", orderAndItemList);
		redirectAttributes.addFlashAttribute("data-orderItemList", orderAndItemList);
		return new ModelAndView("redirect:/reciepts");
	}

	@GetMapping("/product_selection/cancelOrder")
	public ModelAndView cancelOrderAndView(Model model) {
		
		OrderItemListManagement orderItemListManagement = new OrderItemListManagement();
		orderItemListManagement.deleteAllDataOrderItemList(orderAndItemList);
		orderAndItemList= null;


	if (selectedCate == null) {
		model.addAttribute("selectedCate", getAllProduct());
	} else {
		model.addAttribute("selectedCate", getProductByCategory(selectedCate.getId()));
	}
	model.addAttribute("tableSelection", tableSelected);
	model.addAttribute("drinkCategories", getAllCategoriesOfDrink());
	model.addAttribute("foodCategories", getAllCategoriesOfFood());
	model.addAttribute("user", user);
	return new ModelAndView("redirect:/product_selection");
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

	private Product getProductByID(int product_id) {
		ProductManagement productManagement = new ProductManagement();
		return productManagement.getProductById(product_id);
	}

	private Table getTableById(int tableID) {
		TableManagement tableManagement = new TableManagement();
		return tableManagement.getTableById(tableID);
	}
	private Category getCategoryByID(int category_id) {
		CategoryManagement categoryManagement = new CategoryManagement();
		return categoryManagement.getCategoryById(category_id);
	}
	private Order createOrder() {
		LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp dateCreate =  Timestamp.valueOf(currentDateTime);
		return new Order(0, user, dateCreate, "unpaid", tableSelected, 0);
	}
	private ProductSize getProductSizeByProductAndSize(int product_id, int size_id) {
		ProductSizeManagement productSizeManagement = new ProductSizeManagement();
		return productSizeManagement.getProductSizeByProductAndSizeID(product_id, size_id);
	}
	private int addOrderToDatabase(Order order) {
		OrderManagement orderManagement = new OrderManagement();
		return orderManagement.addOrder(order);
	}
	private OrderItem getOrderItemByID(int orderItem_ID) {
		OrderItemManagement orderItemManagement = new OrderItemManagement();
		return orderItemManagement.getOrderItemById(orderItem_ID);
	}
	private OrderAndItemList updateTotalPrice(OrderAndItemList orderAndItemList) {

		var total_price = 0.0;
		for (OrderItem orderItem2 : orderAndItemList.getOrderItemList()) {
			total_price += (orderItem2.getQuantity() * orderItem2.getProductSize().getSize().getPrice());
		}
		orderAndItemList.getOrder().setTotalPrice(total_price);

		return orderAndItemList;

	}

	private User getUserById(int table_id) {
		UserManagement userManagement = new UserManagement();
		return userManagement.getUserById(table_id);
	}

}
