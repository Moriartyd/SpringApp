package ru.galeev.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(name = "login", required = false, defaultValue = "World")String login, Model model) {
        model.addAttribute("login", login);
        return "hello";
    }
}
