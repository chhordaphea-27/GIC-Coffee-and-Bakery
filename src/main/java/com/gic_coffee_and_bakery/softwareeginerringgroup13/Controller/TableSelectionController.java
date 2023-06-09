package com.gic_coffee_and_bakery.softwareeginerringgroup13.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gic_coffee_and_bakery.softwareeginerringgroup13.DBManagement.TableManagement;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.Table;
import com.gic_coffee_and_bakery.softwareeginerringgroup13.Model.User;



@Controller
public class TableSelectionController {

	User user;

    @GetMapping("/tableselection")	
	public ModelAndView test(Model model) {

		var tmpuser = (User) model.getAttribute("user");
		if (tmpuser != null) user = tmpuser;

		model.addAttribute("user", user);
		model.addAttribute("allTable", showAllTable());
		model.addAttribute("tableAttr", getEmptyTable());
		return new ModelAndView("table_selection");
	}

	@GetMapping("/tableselection/displayTableNumAndStatus")	
	public ModelAndView handleDataFromView(@RequestParam ("table_id") int table_id, Model model) {

		model.addAttribute("user", user);

		model.addAttribute("allTable", showAllTable());
		model.addAttribute("tableAttr", getTableById(table_id));
		return new ModelAndView("table_selection");
	}


	@GetMapping("/tableselection/tablesubmission")
	public ModelAndView selectAndContinurButtonPressed(Model model, RedirectAttributes redirectAttributes,
		@RequestParam("tableID") int tableID
	) {
	
		if (tableID > 0) {
			Table table = getTableById(tableID);
			if (table.getTableStatus().equals("Available")) {
				
				model.addAttribute("tableSelection", table);
				redirectAttributes.addFlashAttribute("tableSelection", table);

				model.addAttribute("user", user);
				redirectAttributes.addFlashAttribute("user", user);

				return new ModelAndView("redirect:/product_selection");
			} else {
				model.addAttribute("tableAttr", getTableById(tableID));
			}
		} else {
			model.addAttribute("tableAttr", getEmptyTable());
		}
				model.addAttribute("user", user);

		model.addAttribute("allTable", showAllTable());
		return new ModelAndView("table_selection");
		
	}

	

	private List<Table> showAllTable() {
		TableManagement tableManagement = new TableManagement();
		return tableManagement.getAllTables();
	}

	private Table getEmptyTable() {
		return new Table(0, 0, "Unknown");
	}
	private Table getTableById(int table_id) {
		TableManagement tableManagement = new TableManagement();
		return tableManagement.getTableById(table_id);
	}





}
