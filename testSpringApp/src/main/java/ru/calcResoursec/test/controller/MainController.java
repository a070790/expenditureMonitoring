package ru.calcResoursec.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.Check;
import ru.calcResoursec.test.repository.CheckRepository;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;

	@GetMapping("/add-Check")
	public String getCheckForm(Map<String, Object> model) {
		Iterable<Check> check = checkRepository.findAll();
		model.put("check", check);
		return "checkInp";
	}

	@PostMapping("/add-Check")
	public String addNewCheck (@RequestParam String shopList,
			@RequestParam Long sum, @RequestParam String date, Map<String, Object> model) {
		Check check = new Check(shopList, sum, date);
		checkRepository.save(check);
		
		Iterable<Check> checkUp = checkRepository.findAll();
		model.put("check", checkUp);
		return "checkInp";
	}


}