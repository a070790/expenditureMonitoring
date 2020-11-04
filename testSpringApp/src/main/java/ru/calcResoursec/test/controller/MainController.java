package ru.calcResoursec.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.Check;
import ru.calcResoursec.test.repository.CheckRepository;
import ru.calcResoursec.test.repository.PersonRepository;
import ru.calcResoursec.test.model.Person;
import ru.calcResoursec.test.model.Role;
import java.util.Collections;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {
	@Autowired
	private CheckRepository checkRepository;
	private PersonRepository personRepository;

	@GetMapping("/index")
	public String greeting(@RequestParam(name="name", required=false,
			defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}
	@GetMapping()
	public String main(){
		return "index";
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
	@GetMapping("/registration")
	public String registration(){
		return "registration";
	}
	@PostMapping("/registration")
	public String addPerson(Person person, Map <String, Object> model1){
		Person personFromDb = personRepository.findBylogin(person.getLogin());
				if(personFromDb!=null){
					model1.put("message", "user Exist!");
					return "registration";
				}
				person.setRoles(Collections.singleton(Role.USER));
				personRepository.save(person);
				return "redirect:lodin";
	}



}