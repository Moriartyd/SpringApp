package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.service.UserService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String greetings() {
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<Event> eventList = eventRepository.findAll();
        eventList.sort(Event.COMPARE_BY_NAME);
        model.addAttribute("events", eventList);
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
