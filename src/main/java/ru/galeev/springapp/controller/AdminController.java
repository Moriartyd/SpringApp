package ru.galeev.springapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/userList";
    }

    @GetMapping("user/{id}")
    public String getOneUser(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/userEdit";
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable(value = "id", required = true) User user) {
        userService.deleteUser(user);
        return "redirect:/admin";
    }

    @PostMapping("user/{id}")
    public String editUser(
            @RequestParam String login,
            @RequestParam Map<String, String> form,
            @PathVariable("id") User user) {
        userService.editUser(user, login, form);
        return "redirect:/admin";
    }
}
