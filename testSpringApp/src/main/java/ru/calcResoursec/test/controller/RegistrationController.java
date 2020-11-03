package ru.calcResoursec.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.calcResoursec.test.model.User;
import ru.calcResoursec.test.repository.UserRepository;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String name, @RequestParam String surname,
                          @RequestParam String username, @RequestParam String password) {
        User userFromDb = userRepository.findByUsername(username);
        if (userFromDb != null) {
            return "registration";
        }

        User user = new User(name, surname, username, password);
        user.setActive(true);
        userRepository.save(user);

        return "redirect:/login";
    }
}
