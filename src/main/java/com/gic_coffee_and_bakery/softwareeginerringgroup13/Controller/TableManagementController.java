package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.TableManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Table;

@Controller
public class TableManagementController {
    @GetMapping("/tablemanagement")	
	public ModelAndView test(Model model) {

		model.addAttribute("allTable", displayTable());

		return new ModelAndView("table_management");
	}

	@PostMapping("/tablemanagement")
	public ModelAndView login(@RequestParam("table_id") int table_id, Model model){
		
		TableManagement tableManagement = new TableManagement();
		tableManagement.disableTable(table_id);

		model.addAttribute("allTable", displayTable());
		return new ModelAndView("table_management");
	
	}

	@GetMapping("/tablemanagement/addtable")
	public ModelAndView handleDataFromView(Model model){
		
		TableManagement tableManagement = new TableManagement();
		Table table = new Table(0, tableManagement.getMaxNumberOfNumber() + 1, "Available");
		tableManagement.addTable(table);

		model.addAttribute("allTable", displayTable());
		return new ModelAndView("redirect:/tablemanagement");
	
	}



	private List<Table> displayTable(){
		TableManagement tableManagement = new TableManagement();
		return tableManagement.getAllTables();
	}

	
}

