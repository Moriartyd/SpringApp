package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.service.CustomUserDetailService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomUserDetailService userService;

    @GetMapping("/")
    public String greetings() {
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "main";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable("code")String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "Пользователь успешно активирован");
        } else {
            model.addAttribute("message", "Код активации не найден");
        }
        return "activate";
    }
}
