package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.TableManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Table;



@Controller
public class TableSelectionController {
    @GetMapping("/tableselection")	
	public ModelAndView test(Model model) {

		model.addAttribute("allTable", showAllTable());
		model.addAttribute("tableAttr", getEmptyTable());
		return new ModelAndView("table_selection");
	}

	@GetMapping("/tableselection/displayTableNumAndStatus")	
	public ModelAndView handleDataFromView(@RequestParam ("table_id") int table_id, Model model) {

		
		TableManagement tableManagement = new TableManagement();
		Table table = tableManagement.getTableById(table_id);
		
		model.addAttribute("allTable", showAllTable());
		model.addAttribute("tableAttr", table);
		return new ModelAndView("table_selection");
	}


	private List<Table> showAllTable() {
		TableManagement tableManagement = new TableManagement();
		return tableManagement.getAllTables();
	}

	private Table getEmptyTable() {
		return new Table(0, 0, "Unknown");
	}





}
