package ru.calcResoursec.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.Check;
import ru.calcResoursec.test.model.Purchase;
import ru.calcResoursec.test.model.User;
import ru.calcResoursec.test.repository.CheckRepository;

import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;

	@GetMapping("/")
	public String getHomePage(String name, Model model) {
		return "home";
	}

	@GetMapping("/main")
	public String getMainPage(Map<String, Object> model) {
		Iterable<Check> checks = checkRepository.findAll();
		model.put("checks", checks);

		return "main";
	}

	@PostMapping("/main")
	public String addCheck(@RequestParam Long sum, @RequestParam String date,
						   @AuthenticationPrincipal User user, Map<String, Object> model) {
		Check check = new Check(sum, date, user);
		check.addPurchase();

		checkRepository.save(check);

		Iterable<Check> checks = checkRepository.findAll();
		model.put("checks", checks);

		return "main";
	}

	@PostMapping("/filter")
	public String useFilter(@RequestParam String filter, Map<String, Object> model) {
		Iterable<Check> checks;
		String dataRegex = "\\d{2}\\W?\\d{2}\\W?\\d{4}";
		String sumRegex = "\\d+";

		if (Pattern.matches(sumRegex, filter)) {
			Long sum = Long.parseLong(filter);
			checks = checkRepository.findBySum(sum);
		}
		else if (Pattern.matches(dataRegex, filter)) {
			checks = checkRepository.findByDate(filter);
		} else {
			checks = checkRepository.findAll();
		}

		model.put("checks", checks);

		return "main";
	}
}
