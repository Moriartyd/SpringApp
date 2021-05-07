package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.service.CustomUserDetailService;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomUserDetailService userService;

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("message", "Пользователь уже существует");
            return "user/registration";
        }
        return "redirect:/login";
    }

    @GetMapping("{id}")
    public String getOneUser(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "user/id";
    }
}
