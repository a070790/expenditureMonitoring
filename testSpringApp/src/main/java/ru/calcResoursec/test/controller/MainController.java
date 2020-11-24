package ru.calcResoursec.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.Check;
import ru.calcResoursec.test.model.Purchase;
import ru.calcResoursec.test.model.User;
import ru.calcResoursec.test.repository.CheckRepository;

import java.util.Map;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;

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
	public String addPurchase(Map<String, Object> model) {
		Iterable<Check> checkUp = checkRepository.findAll();
		model.put("checks", checkUp);

		return "main";
	}

	@GetMapping("/add_check")
	public String getCheckInputPage(Map<String, Object> model) {
		model.put("sum_", 0);
		model.put("date_", "");

		return "checkinput";
	}

	@PostMapping("/add_check")
	public String addCheck(@AuthenticationPrincipal User user,
						   @RequestParam String date,
						   @RequestParam String purchaseName,
						   @RequestParam String category,  		@RequestParam Long price,
						   @RequestParam Integer quantity, @RequestParam Boolean isSaved,
						   Map<String, Object> model) {
		Check check = null;

		if (date != "") {
			if (check == null) {
				long sum = 0;
				check = new Check(user, date, sum);
			}

			check.addPurchase(new Purchase(purchaseName, category, price, quantity));

			Long sum = check.getSum() + price * quantity;
			check.setSum(sum);

			model.put("sum_", sum);
			model.put("date_", date);

			return "checkinput";
		}

		if (date != "" && isSaved) {
			checkRepository.save(check);

			return "redirect:main";
		}

		model.put("sum_", 0);
		model.put("date_", "");

		return "checkinput";
	}

//	@PostMapping("/filter")
//	public String useFilter(@RequestParam String filter, Map<String, Object> model) {
//		Iterable<Check> checkUp;
//		String dataRegex = "\\d{2}\\W?\\d{2}\\W?\\d{4}";
//		String numRegex = "\\d+";
//
//		if (Pattern.matches(numRegex, filter)) {
//			Integer num = Integer.parseInt(filter);
//			checkUp = checkRepository.findByCheckNum(num);
//		}
//		else if (Pattern.matches(dataRegex, filter)) {
//			checkUp = checkRepository.findByDate(filter);
//		} else {
//			checkUp = checkRepository.findAll();
//		}
//
//		model.put("checks", checkUp);
//
//		return "main";
//	}
}
