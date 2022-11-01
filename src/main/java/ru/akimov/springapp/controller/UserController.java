package ru.akimov.springapp.controller;

import org.hibernate.event.spi.EventType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akimov.springapp.persistence.domain.user.User;
import ru.akimov.springapp.enums.Role;
import ru.akimov.springapp.service.ContractorService;
import ru.akimov.springapp.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ContractorService contractorService;

    public UserController(UserService userService,
                          ContractorService contractorService) {
        this.userService = userService;
        this.contractorService = contractorService;
    }

    @GetMapping
    public String main(Model model) {
        model.addAttribute("title", "Пользователи");
        return "common";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "user/registration";
    }

    @PostMapping("/registration")
    public String createUser(User user,
                             @RequestParam Map<String, String> form,
                             Model model) {
        if (!userService.createUser(user, form)) {
            model.addAttribute("message", "Пользователь уже существует");
            return "user/registration";
        }
        return "redirect:/login";
    }

    @GetMapping("{id}")
    public String getOneUser(@PathVariable("id") User user,
                             Model model,
                             Authentication auth) {
        model.addAttribute("user", user);
       if (Role.valueOf(user.getRole()) == Role.USER) {
            model.addAttribute("calcs_cnt", userService.getCalculations(user).size());
           model.addAttribute("contracts_cnt", userService.getContracts(user).size());
        }
        if (Role.valueOf(user.getRole()) == Role.OUTSTAFF) {
            model.addAllAttributes(contractorService.getResolvedTechMap(user));
            model.addAttribute("contracts_cnt", userService.getContracts(user).size());
        }
        if (((User)auth.getPrincipal()).getRole().equals(Role.OUTSTAFF)) {
            model.addAttribute("rated", contractorService.getRate((User) auth.getPrincipal(), user));
        } else {
            model.addAttribute("rated", contractorService.getRate(user, (User) auth.getPrincipal()));
        }
        model.addAttribute("con", contractorService.getContractorFromUser(user));
        return "user/id";
    }

    @GetMapping("{id}/contracts")
    public String showContracts(@PathVariable("id") User user,
                           Model model) {
        model.addAttribute("isContracts", true);
        model.addAttribute("contracts", userService.getContracts(user));
        model.addAttribute("user", user);
        return "user/cards";
    }

    @GetMapping("{id}/calcs")
    public String showFollowers(@PathVariable("id") User user,
                           Model model) {
        model.addAttribute("isCalcs", true);
        model.addAttribute("calcs", userService.getCalculations(user));
        model.addAttribute("user", user);
        return "user/cards";
    }

    @GetMapping("{id}/events")
    public String showCalcs(@PathVariable("id") User user,
                                Model model) {
        model.addAttribute("isForUser", true);
        model.addAttribute("calculations", userService.getCalculations(user));
        model.addAttribute("user", user);
        return "events/cards";
    }

//    @GetMapping("{id}/managed_events")
//    public String showManagedEvents(@PathVariable("id") User user,
//                             Model model) {
//        model.addAttribute("isByUser", true);
//        model.addAttribute("events", userService.getEventList(user.getEventList()));
//        model.addAttribute("user", user);
//        return "events/cards";
//    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("keywords", EventType.values());
        return "user/edit";
    }

//    @PostMapping("/{id}/follow")
//    public String addFollower(@PathVariable("id") User obj,
//                              Authentication auth) {
//        userService.followUser(obj, (User) auth.getPrincipal());
//        return "redirect:/user/" + obj.getId();
//    }

//    @PostMapping("/{id}/unFollow")
//    public String removeFollower(@PathVariable("id") User obj,
//                              Authentication auth) {
//        userService.unFollowUser(obj, (User) auth.getPrincipal());
//        return "redirect:/user/" + obj.getId();
//    }

    @PostMapping("/{id}/edit")
    public String editYourself(@PathVariable("id") User user,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam int age,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam Map<String, String> form) {
        userService.editUser(user, name, surname, age, password, email, form);
        return "redirect:/user/" + user.getId();
    }
}
