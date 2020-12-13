package ru.calcResoursec.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.calcResoursec.test.model.Role;
import ru.calcResoursec.test.model.User;
import ru.calcResoursec.test.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "userList";
    }
    @GetMapping("{user}")
    public String UserEditForm(@PathVariable User user, Model model){
        model.addAttribute(user);
        model.addAttribute("roles" , Role.values());
        if(user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("checkAdmin", new String ("checked"));
        }
        if(!user.getRoles().contains(Role.ADMIN)){
            model.addAttribute("checkAdmin", new String (""));
        }
        if(user.getRoles().contains(Role.USER)){
            model.addAttribute("checkUser", new String ("checked"));
        }
        if(!user.getRoles().contains(Role.USER)){
            model.addAttribute("checkUser", new String (""));
        }
        return "/userEdit";
    }
    @PostMapping
    public String userSave(@RequestParam ("userid") User user,
                           @RequestParam Map<String, String>form,
                           @RequestParam String username,
                           @RequestParam String surname,
                           @RequestParam String name,
                           @RequestParam String password ){
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.getRoles().clear();
        for(String key: form.keySet()){
            if(key.equals("userCheck")){
                user.getRoles().add(Role.USER);
            }
            if(key.equals("adminCheck")){
                user.getRoles().add(Role.ADMIN);
            }
        }

        userRepository.save(user);
        return "redirect:/user";
    }
}

