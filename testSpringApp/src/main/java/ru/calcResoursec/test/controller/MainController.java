package ru.calcResoursec.test.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.Check;
import ru.calcResoursec.test.repository.CheckRepository;

<<<<<<< Updated upstream
import java.util.Date;
=======
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes
import java.util.Map;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false,
			defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/add-Check")
	public String getCheckForm(Map<String, Object> model) {
		return "checkInp";
	}

	@PostMapping("/add-Check")
	public String addNewCheck (@RequestParam String shopList
			, @RequestParam Long sum, @RequestParam String date, Map<String, Object> model) {
		Check check = new Check();
		check.setShopList(shopList);
		check.setSum(sum);
		check.setDate(date);
		checkRepository.save(check);

		return "checkInp";
	}


}

