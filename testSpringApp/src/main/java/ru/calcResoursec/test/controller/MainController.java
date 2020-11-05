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
import ru.calcResoursec.test.repository.PurchaseRepository;

import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;

<<<<<<< HEAD
	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/main")
	public String getMainPage(Map<String, Object> model) {
		Iterable<Check> checkUp = checkRepository.findAll();
		model.put("checks", checkUp);

		return "main";
	}

	@PostMapping("/main")
	public String addPurchase(@AuthenticationPrincipal User user,
							  @RequestParam Long sum, @RequestParam Integer checkNum,
							  @RequestParam String date, @RequestParam String name,
							  @RequestParam String category,
							  Map<String, Object> model) {

		Check check = checkRepository.findOneByCheckNum(checkNum);

		if (check == null) {
			check = new Check(sum, checkNum, date, user);
			check.addPurchase(new Purchase(name, category));
		} else {
			check.addPurchase(new Purchase(name, category));
		}

		checkRepository.save(check);

		Iterable<Check> checkUp = checkRepository.findAll();
		model.put("checks", checkUp);

		return "main";
=======
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
>>>>>>> 085dfb237fa2e800d5bdf056f0b15e981fef6519
	}

	@PostMapping("/filter")
	public String useFilter(@RequestParam String filter, Map<String, Object> model) {
		Iterable<Check> checkUp;
		String dataRegex = "\\d{2}\\W?\\d{2}\\W?\\d{4}";
		String numRegex = "\\d+";

		if (Pattern.matches(numRegex, filter)) {
			Integer num = Integer.parseInt(filter);
			checkUp = checkRepository.findByCheckNum(num);
		}
		else if (Pattern.matches(dataRegex, filter)) {
			checkUp = checkRepository.findByDate(filter);
		} else {
			checkUp = checkRepository.findAll();
		}

<<<<<<< HEAD
		model.put("checks", checkUp);

		return "main";
	}
}
=======
}
>>>>>>> 085dfb237fa2e800d5bdf056f0b15e981fef6519
