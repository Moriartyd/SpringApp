package ru.galeev.springapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.configs.HiddenAnnotationExclusionStrategy;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.MatrixRepository;
import ru.galeev.springapp.persistence.repository.PlaceRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.service.MatrixService;
import ru.galeev.springapp.service.UserService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserService userService;
    @Autowired
    MatrixRepository matrixRepository;
    @Autowired
    MatrixService mService;

    @GetMapping("/")
    public String greetings() {
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
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

    @GetMapping("/activate_mail/{code}")
    public String activateUserMail(@PathVariable("code")String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "Пользователь успешно активирован");
        } else {
            model.addAttribute("message", "Код активации не найден");
        }
        return "activate";
    }
}
