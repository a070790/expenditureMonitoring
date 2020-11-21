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
import ru.calcResoursec.test.service.Appropriator;

import java.util.Map;
import java.util.regex.Pattern;

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
		model.put("chId", -1);
		model.put("sum_", 0);
		model.put("addPurchase", false);
		model.put("wasSaved", false);
		return "checkinput";
	}

	@PostMapping("/add_check")
	public String addCheck(@AuthenticationPrincipal User user,
						   @RequestParam Integer checkId,     @RequestParam String date,
						   @RequestParam String name, 		  @RequestParam String category,
						   @RequestParam Long price,  		  @RequestParam Integer quantity,
						   @RequestParam Boolean addPurchase, @RequestParam Boolean wasSaved,
						   Map<String, Object> model) {
		if (wasSaved) {
			model.put("chId", checkId);
			return "redirect:main";
		}

		if (addPurchase) {
			Check check = checkRepository.findOneById(checkId);

			if (check == null) {
				check = new Check(user, date);
				check.addPurchase(new Purchase(name, category, price, quantity));
				Long sum = price * quantity;
				check.setSum(sum);
				model.put("sum_", sum);

				checkRepository.save(check);

				check = checkRepository.findOneByUserAndDate(user, date);
				checkId = check.getId();
				model.put("chId", checkId);
			} else {
				check.addPurchase(new Purchase(name, category, price, quantity));
				Long sum = check.getSum() + price * quantity;
				check.setSum(sum);
				model.put("sum_", sum);

				checkRepository.save(check);

				model.put("chId", checkId);
			}
		}

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
