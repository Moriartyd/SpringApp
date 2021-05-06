package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

//    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        User userFromDb = userRepository.findUserByLogin(user.getLogin());
        if (userFromDb != null) {
            model.addAttribute("message", "Пользователь уже существует");
            return "user/registration";
        }
        userRepository.saveAndFlush(user);
        return "redirect:/login";
    }

    @GetMapping("{id}")
    public String getOneUser(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "user/id";
    }
}
