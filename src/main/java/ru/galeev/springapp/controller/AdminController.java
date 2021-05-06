package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Role;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/show_users")
    public String getAllUsers(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "admin/userList";
    }

    @GetMapping("user/{id}")
    public String getOneUser(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/userEdit";
    }

    @PostMapping("user/{id}")
    public String editUser(
            @RequestParam String login,
            @RequestParam Map<String, String> form,
            @PathVariable("id") User user) {
        user.setLogin(login);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.setRole(Role.valueOf(key).getAuthority());
            }
        }
        userRepository.saveAndFlush(user);
        return "redirect:/admin/show_users";
    }
}
