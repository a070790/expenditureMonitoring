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
import ru.calcResoursec.test.repository.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	private ArrayList<Check> checks = new ArrayList<>();

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/main")
	public String getMainPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		Iterable<Check> checks = checkRepository.findAllByUser(user);
		model.put("checks", checks);

		return "main";
	}

//	@PostMapping("/main")
//	public String addPurchase(Map<String, Object> model) {
//		Iterable<Check> checkUp = checkRepository.findAll();
//		model.put("checks", checkUp);
//
//		return "main";
//	}

	@GetMapping("/add_check")
	public String getCheckInputPage(Map<String, Object> model) {
		model.put("sum", 0);
		model.put("date", "");
		model.put("checkIndex", -1);

		return "checkinput";
	}

	@PostMapping("/add_check")
	public String addCheck(@AuthenticationPrincipal User user,
						   @RequestParam String date,
						   @RequestParam Integer checkIndex,

						   @RequestParam String purchaseName,
						   @RequestParam String category,
						   @RequestParam Integer quantity,
						   @RequestParam Long price,

						   @RequestParam Boolean shouldBeSaved,
						   Map<String, Object> model) {
		Check check = null;
		long sum = 0;

		if (checkIndex != -1) {
			check = checks.get(checkIndex);
		}

		if (date != "" && !shouldBeSaved) {
			if (checkIndex == -1) {
				check = new Check(user, date, sum);
				check.addPurchase(new Purchase(purchaseName, category, price, quantity));
				sum = check.getSum() + price * quantity;
				check.setSum(sum);

				checks.add(check);

				checkIndex = checks.indexOf(check);
			} else {
				check.addPurchase(new Purchase(purchaseName, category, price, quantity));
				sum = check.getSum() + price * quantity;
				check.setSum(sum);

				checks.add(check);
			}

			Iterable<Purchase> purchases = check.getPurchases();

			model.put("purchases", purchases);
			model.put("sum", sum);
			model.put("date", date);
			model.put("checkIndex", checkIndex);
			model.put("id", -1);

			return "checkinput";
		} else if (date != "" && (shouldBeSaved & check.isNotEmpty())) {
			check = checks.get(checkIndex);
			checkRepository.save(check);
			checks.remove(check);

			return "redirect:main";
		}

		model.put("sum", 0);
		model.put("date", "");
		model.put("checkIndex", -1);

		return "checkinput";
	}

	@GetMapping("/edit_check")
	public String getCheckEditPage(@RequestParam Integer checkIndexFromArray,
								   @RequestParam Integer checkIdFromDB,

								   @RequestParam Boolean requestCameFromInputPage,
								   @RequestParam Boolean requestCameFromMainPage,
								   Map<String, Object> model) {
		Check check = null;

		if (requestCameFromInputPage) {
			check = checks.get(checkIndexFromArray);
		} else if (requestCameFromMainPage) {
			check = checkRepository.findOneById(checkIdFromDB);
		}

		Iterable<Purchase> purchases = check.getPurchases();
		model.put("purchases", purchases);

		model.put("date", check.getDate());
		model.put("sum", check.getSum());
		model.put("checkIndexFromArray", checkIndexFromArray);
		model.put("checkIdFromDB", checkIdFromDB);
		model.put("requestCameFromInputPage", requestCameFromInputPage);
		model.put("requestCameFromMainPage", requestCameFromMainPage);

		return "checkedit";
	}

	@GetMapping("/edit_purchase")
	public String getPurchaseEditPage(@RequestParam Integer checkIndexFromArray,
									  @RequestParam Integer checkIdFromDB,

									  @RequestParam String purchaseName,

									  @RequestParam Boolean requestCameFromInputPage,
									  @RequestParam Boolean requestCameFromMainPage,
									  Map<String, Object> model) {
		Check check = null;
		Purchase purchase = null;
		Integer purchaseIndex = -1;

		if (requestCameFromInputPage) {
			check = checks.get(checkIndexFromArray);
			purchaseIndex = check.searchPurchase(purchaseName);
			purchase = check.getPurchase(purchaseIndex);

			model.put("checkIndexFromArray", checkIndexFromArray);
			model.put("checkIdFromDB", -1);
			model.put("purchaseIndex", purchaseIndex);
		} else if (requestCameFromMainPage) {
			check = checkRepository.findOneById(checkIdFromDB);
			purchaseIndex = check.searchPurchase(purchaseName);
			purchase = check.getPurchase(purchaseIndex);

			model.put("checkIndexFromArray", -1);
			model.put("checkIdFromDB", checkIdFromDB);
			model.put("purchaseIndex", purchaseIndex);
		}

		model.put("category", purchase.getCategory());
		model.put("price", purchase.getPrice());
		model.put("quantity", purchase.getQuantity());

		model.put("requestCameFromInputPage", requestCameFromInputPage);
		model.put("requestCameFromMainPage", requestCameFromMainPage);

		return "purchaseedit";
	}

	@PostMapping("/edit_purchase")
	public String editPurchase(@RequestParam Integer purchaseIndex,
							   @RequestParam Integer checkIndexFromArray,
							   @RequestParam Integer checkIdFromDB,

							   @RequestParam String purchaseName,
							   @RequestParam String category,
							   @RequestParam Long price,
							   @RequestParam Integer quantity,

							   @RequestParam Boolean requestCameFromInputPage,
							   @RequestParam Boolean requestCameFromMainPage,
							   Map<String, Object> model) {
		Check check = null; Purchase purchase = null;

		if (requestCameFromInputPage) {
			check = checks.get(checkIndexFromArray);
			purchase = check.getPurchase(purchaseIndex);
		} else if (requestCameFromMainPage) {
			check = checkRepository.findOneById(checkIdFromDB);
			purchase = check.getPurchase(purchaseIndex);
		}

		if (!purchaseName.equals("")) {
			purchase.setName(purchaseName);
		}
		if (!category.equals("")) {
			purchase.setCategory(category);
		}
		if (!price.equals("")) {
			purchase.setPrice(price);
		}
		if (!quantity.equals("")) {
			purchase.setQuantity(quantity);
		}

		if (requestCameFromMainPage) {
			purchaseRepository.save(purchase);
		}

		model.put("checkIndexFromArray", checkIndexFromArray);
		model.put("checkIdFromDB", checkIdFromDB);
		model.put("purchaseIndex", purchaseIndex);
		model.put("purchaseName", purchase.getName());
		model.put("category", purchase.getCategory());
		model.put("price", purchase.getPrice());
		model.put("quantity", purchase.getQuantity());
		model.put("checkIndexFromArray", checkIndexFromArray);
		model.put("checkIdFromDB", checkIdFromDB);
		model.put("requestCameFromInputPage", requestCameFromInputPage);
		model.put("requestCameFromMainPage", requestCameFromMainPage);

		return "purchaseedit";
	}

//	@PostMapping("/remove")
//	public String deleteElement() {
//		if (requestCameFromInputPage) {
//			checks.remove(checkIndexFromArray);
//
//		} else if (requestCameFromMainPage) {
//			checkRepository.deleteById(checkIdFromDB);
//		}
//
//		if (requestCameFromInputPage) {
//			check = checks.get(checkIndex);
//			check.removePurchase(purchaseIndex);
//		} else if (requestCameFromMainPage) {
//			purchaseRepository.deletePurchaseByNameAndCheck_Id(purchaseName, checkId);
//		}
//	}

	@PostMapping("/filter")
	public String useFilter(@RequestParam String filter, Map<String, Object> model) {
		Iterable<Check> checkUp;
		String dataRegex = "\\d{2}\\W?\\d{2}\\W?\\d{4}";

		if (Pattern.matches(dataRegex, filter)) {
			checkUp = checkRepository.findAllByDate(filter);
		} else {
			checkUp = checkRepository.findAll();
		}

		model.put("checks", checkUp);

		return "main";
	}
}
