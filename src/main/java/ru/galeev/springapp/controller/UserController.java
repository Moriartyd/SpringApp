package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.service.UserService;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

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
    public String getOneUser(@PathVariable("id") User user,
                             Model model,
                             Authentication auth) {
        model.addAttribute("user", user);
        model.addAttribute("event_cnt", user.getUserRegisteredEvents().size());
        model.addAttribute("followers_cnt", user.getFollowers().size());
        model.addAttribute("subscriptions_cnt", user.getSubscriptions().size());
        model.addAttribute("isSubscription", user.getFollowers().contains((User) auth.getPrincipal()));
        if (Role.valueOf(user.getRole()) == Role.MANAGER) {
            model.addAttribute("managed_events_cnt", user.getEventList().size());
        }
        return "user/id";
    }

    @GetMapping("{id}/subs")
    public String showSubs(@PathVariable("id") User user,
                           Model model) {
        model.addAttribute("isSubs", true);
        model.addAttribute("users", userService.getUserList(user.getSubscriptions()));
        model.addAttribute("user", user);
        return "user/cards";
    }

    @GetMapping("{id}/followers")
    public String showFollowers(@PathVariable("id") User user,
                           Model model) {
        model.addAttribute("isFollowers", true);
        model.addAttribute("users", userService.getUserList(user.getFollowers()));
        model.addAttribute("user", user);
        return "user/cards";
    }

    @GetMapping("{id}/events")
    public String showEvents(@PathVariable("id") User user,
                                Model model) {
        model.addAttribute("isForUser", true);
        model.addAttribute("events", userService.getEventList(user.getUserRegisteredEvents()));
        model.addAttribute("user", user);
        return "events/cards";
    }

    @GetMapping("{id}/managed_events")
    public String showManagedEvents(@PathVariable("id") User user,
                             Model model) {
        model.addAttribute("isByUser", true);
        model.addAttribute("events", userService.getEventList(user.getEventList()));
        model.addAttribute("user", user);
        return "events/cards";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/{id}/follow")
    public String addFollower(@PathVariable("id") User obj,
                              Authentication auth) {
        userService.followUser(obj, (User) auth.getPrincipal());
        return "redirect:/user/" + obj.getId();
    }

    @PostMapping("/{id}/unFollow")
    public String removeFollower(@PathVariable("id") User obj,
                              Authentication auth) {
        userService.unFollowUser(obj, (User) auth.getPrincipal());
        return "redirect:/user/" + obj.getId();
    }

    @PostMapping("/{id}/edit")
    public String editYourself(@PathVariable("id") User user,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam int age,
                               @RequestParam String password,
                               @RequestParam String email) {
        userService.editUser(user, name, surname, age, password, email);
        return "redirect:/user/" + user.getId();
    }
}
