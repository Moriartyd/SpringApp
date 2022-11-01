package ru.akimov.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akimov.springapp.persistence.repository.ContractRequestRepository;
import ru.akimov.springapp.persistence.repository.CalculationRepository;
import ru.akimov.springapp.persistence.repository.UserRepository;
import ru.akimov.springapp.service.UserService;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final CalculationRepository calculationRepository;
    private final UserService userService;
    private final ContractRequestRepository contractRequestRepository;

    public MainController(UserRepository userRepository,
                          CalculationRepository calculationRepository,
                          UserService userService,
                          ContractRequestRepository contractRequestRepository) {
        this.userRepository = userRepository;
        this.calculationRepository = calculationRepository;
        this.userService = userService;
        this.contractRequestRepository = contractRequestRepository;
    }

    @GetMapping("/")
    public String greetings() {
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }

//    @GetMapping("/activate/{code}")
//    public String activateUser(@PathVariable("code")String code, Model model) {
//        boolean isActivated = userService.activateUser(code);
//        if (isActivated) {
//            model.addAttribute("message", "Пользователь успешно активирован");
//        } else {
//            model.addAttribute("message", "Код активации не найден");
//        }
//        return "activate";
//    }

//    @GetMapping("/activate_mail/{code}")
//    public String activateUserMail(@PathVariable("code")String code, Model model) {
//        boolean isActivated = userService.activateUser(code);
//        if (isActivated) {
//            model.addAttribute("message", "Пользователь успешно активирован");
//        } else {
//            model.addAttribute("message", "Код активации не найден");
//        }
//        return "activate";
//    }
}
