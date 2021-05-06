package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.persistence.domain.User;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

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
}
